/*
 *     Copyright 2015-2017 Austin Keener & Michael Ritter & Florian Spieß
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dv8tion.jda.core.entities.impl;

import com.neovisionaries.ws.client.WebSocketFactory;
import gnu.trove.map.TLongObjectMap;
import net.dv8tion.jda.bot.JDABot;
import net.dv8tion.jda.bot.entities.impl.JDABotImpl;
import net.dv8tion.jda.client.JDAClient;
import net.dv8tion.jda.client.entities.impl.JDAClientImpl;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.ShardedRateLimiter;
import net.dv8tion.jda.core.audio.AudioWebSocket;
import net.dv8tion.jda.core.audio.factory.DefaultSendFactory;
import net.dv8tion.jda.core.audio.factory.IAudioSendFactory;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.StatusChangeEvent;
import net.dv8tion.jda.core.exceptions.AccountTypeException;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.handle.EventCache;
import net.dv8tion.jda.core.hooks.IEventManager;
import net.dv8tion.jda.core.hooks.InterfacedEventManager;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.managers.Presence;
import net.dv8tion.jda.core.managers.impl.PresenceImpl;
import net.dv8tion.jda.core.requests.*;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.core.requests.restaction.GuildAction;
import net.dv8tion.jda.core.utils.Checks;
import net.dv8tion.jda.core.utils.MiscUtil;
import net.dv8tion.jda.core.utils.SimpleLog;
import net.dv8tion.jda.core.utils.cache.CacheView;
import net.dv8tion.jda.core.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.core.utils.cache.impl.AbstractCacheView;
import net.dv8tion.jda.core.utils.cache.impl.SnowflakeCacheViewImpl;
import okhttp3.OkHttpClient;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JDAImpl implements JDA
{
    public static final SimpleLog LOG = SimpleLog.getLog(JDA.class);

    public final ScheduledThreadPoolExecutor pool;

    protected final SnowflakeCacheViewImpl<User> userCache = new SnowflakeCacheViewImpl<>(User::getName);
    protected final SnowflakeCacheViewImpl<Guild> guildCache = new SnowflakeCacheViewImpl<>(Guild::getName);
    protected final SnowflakeCacheViewImpl<Category> categories = new SnowflakeCacheViewImpl<>(Channel::getName);
    protected final SnowflakeCacheViewImpl<TextChannel> textChannelCache = new SnowflakeCacheViewImpl<>(Channel::getName);
    protected final SnowflakeCacheViewImpl<VoiceChannel> voiceChannelCache = new SnowflakeCacheViewImpl<>(Channel::getName);
    protected final SnowflakeCacheViewImpl<PrivateChannel> privateChannelCache = new SnowflakeCacheViewImpl<>(MessageChannel::getName);

    protected final TLongObjectMap<User> fakeUsers = MiscUtil.newLongMap();
    protected final TLongObjectMap<PrivateChannel> fakePrivateChannels = MiscUtil.newLongMap();

    protected final AbstractCacheView<AudioManager> audioManagers = new CacheView.SimpleCacheView<>(m -> m.getGuild().getName());

    protected final OkHttpClient.Builder httpClientBuilder;
    protected final WebSocketFactory wsFactory;
    protected final AccountType accountType;
    protected final PresenceImpl presence;
    protected final JDAClient jdaClient;
    protected final JDABot jdaBot;
    protected final int maxReconnectDelay;
    protected final Thread shutdownHook;
    protected final EntityBuilder entityBuilder = new EntityBuilder(this);
    protected final EventCache eventCache = new EventCache();
    protected final GuildLock guildLock = new GuildLock(this);
    protected final Object akapLock = new Object();

    protected WebSocketClient client;
    protected Requester requester;
    protected IEventManager eventManager = new InterfacedEventManager();
    protected IAudioSendFactory audioSendFactory = new DefaultSendFactory();
    protected ScheduledThreadPoolExecutor audioKeepAlivePool;
    protected Status status = Status.INITIALIZING;
    protected SelfUser selfUser;
    protected ShardInfo shardInfo;
    protected String token = null;
    protected boolean audioEnabled;
    protected boolean bulkDeleteSplittingEnabled;
    protected boolean autoReconnect;
    protected long responseTotal;
    protected long ping = -1;

    public JDAImpl(AccountType accountType, OkHttpClient.Builder httpClientBuilder, WebSocketFactory wsFactory, ShardedRateLimiter rateLimiter,boolean autoReconnect, boolean audioEnabled,
            boolean useShutdownHook, boolean bulkDeleteSplittingEnabled,boolean retryOnTimeout, int corePoolSize, int maxReconnectDelay)
    {
        this.accountType = accountType;
        this.httpClientBuilder = httpClientBuilder;
        this.wsFactory = wsFactory;
        this.autoReconnect = autoReconnect;
        this.audioEnabled = audioEnabled;
        this.shutdownHook = useShutdownHook ? new Thread(this::shutdown, "JDA Shutdown Hook") : null;
        this.bulkDeleteSplittingEnabled = bulkDeleteSplittingEnabled;
        this.pool = new ScheduledThreadPoolExecutor(corePoolSize, new JDAThreadFactory());
        this.maxReconnectDelay = maxReconnectDelay;

        this.presence = new PresenceImpl(this);
        this.requester = new Requester(this, rateLimiter);
        this.requester.setRetryOnTimeout(retryOnTimeout);

        this.jdaClient = accountType == AccountType.CLIENT ? new JDAClientImpl(this) : null;
        this.jdaBot = accountType == AccountType.BOT ? new JDABotImpl(this) : null;
    }

    public void login(String token, ShardInfo shardInfo, SessionReconnectQueue reconnectQueue) throws LoginException, RateLimitedException
    {
        setStatus(Status.LOGGING_IN);
        if (token == null || token.isEmpty())
            throw new LoginException("Provided token was null or empty!");

        setToken(token);
        verifyToken();
        this.shardInfo = shardInfo;
        LOG.info("Login Successful!");

        client = new WebSocketClient(this, reconnectQueue);

        if (shutdownHook != null)
        {
            Runtime.getRuntime().addShutdownHook(shutdownHook);
        }
    }

    public void setStatus(Status status)
    {
        synchronized (this.status)
        {
            Status oldStatus = this.status;
            this.status = status;

            eventManager.handle(new StatusChangeEvent(this, status, oldStatus));
        }
    }

    public void setToken(String token)
    {
        if (getAccountType() == AccountType.BOT)
            this.token = "Bot " + token;
        else
            this.token = token;
    }

    public void verifyToken() throws LoginException, RateLimitedException
    {
        RestAction<JSONObject> login = new RestAction<JSONObject>(this, Route.Self.GET_SELF.compile())
        {
            @Override
            protected void handleResponse(Response response, Request<JSONObject> request)
            {
                if (response.isOk())
                    request.onSuccess(response.getObject());
                else if (response.isRateLimit())
                    request.onFailure(new RateLimitedException(request.getRoute(), response.retryAfter));
                else if (response.code == 401)
                    request.onSuccess(null);
                else
                    request.onFailure(new LoginException("When verifying the authenticity of the provided token, Discord returned an unknown response:\n" +
                        response.toString()));
            }
        };

        JSONObject userResponse;
        try
        {
            userResponse = login.complete(false);
        }
        catch (RuntimeException e)
        {
            //We check if the LoginException is masked inside of a ExecutionException which is masked inside of the RuntimeException
            Throwable ex = e.getCause() != null ? e.getCause().getCause() : null;
            if (ex instanceof LoginException)
                throw (LoginException) ex;
            else
                throw e;
        }

        if (userResponse != null)
        {
            verifyToken(userResponse);
        }
        else
        {
            //If we received a null return for userResponse, then that means we hit a 401.
            // 401 occurs we attempt to access the users/@me endpoint with the wrong token prefix.
            // e.g: If we use a Client token and prefix it with "Bot ", or use a bot token and don't prefix it.
            // It also occurs when we attempt to access the endpoint with an invalid token.
            //The code below already knows that something is wrong with the token. We want to determine if it is invalid
            // or if the developer attempted to login with a token using the wrong AccountType.

            //If we attempted to login as a Bot, remove the "Bot " prefix and set the Requester to be a client.
            if (getAccountType() == AccountType.BOT)
            {
                token = token.replace("Bot ", "");
                requester = new Requester(this, AccountType.CLIENT, null);
            }
            else    //If we attempted to login as a Client, prepend the "Bot " prefix and set the Requester to be a Bot
            {
                token = "Bot " + token;
                requester = new Requester(this, AccountType.BOT, null);
            }

            try
            {
                //Now that we have reversed the AccountTypes, attempt to get User info again.
                userResponse = login.complete(false);
            }
            catch (RuntimeException e)
            {
                //We check if the LoginException is masked inside of a ExecutionException which is masked inside of the RuntimeException
                Throwable ex = e.getCause() != null ? e.getCause().getCause() : null;
                if (ex instanceof LoginException)
                    throw (LoginException) ex;
                else
                    throw e;
            }

            //If the response isn't null (thus it didn't 401) send it to the secondary verify method to determine
            // which account type the developer wrongly attempted to login as
            if (userResponse != null)
                verifyToken(userResponse);
            else    //We 401'd again. This is an invalid token
                throw new LoginException("The provided token is invalid!");
        }
    }

    private void verifyToken(JSONObject userResponse)
    {
        if (getAccountType() == AccountType.BOT)
        {
            if (!userResponse.has("bot") || !userResponse.getBoolean("bot"))
                throw new AccountTypeException(AccountType.BOT, "Attempted to login as a BOT with a CLIENT token!");
        }
        else
        {
            if (userResponse.has("bot") && userResponse.getBoolean("bot"))
                throw new AccountTypeException(AccountType.CLIENT, "Attempted to login as a CLIENT with a BOT token!");
        }
    }

    @Override
    public String getToken()
    {
        return token;
    }

    @Override
    public boolean isAudioEnabled()
    {
        return audioEnabled;
    }

    @Override
    public boolean isBulkDeleteSplittingEnabled()
    {
        return bulkDeleteSplittingEnabled;
    }

    @Override
    public void setAutoReconnect(boolean autoReconnect)
    {
        this.autoReconnect = autoReconnect;
        if (client != null)
        {
            client.setAutoReconnect(autoReconnect);
        }
    }

    @Override
    public void setRequestTimeoutRetry(boolean retryOnTimeout)
    {
        requester.setRetryOnTimeout(retryOnTimeout);
    }

    @Override
    public boolean isAutoReconnect()
    {
        return autoReconnect;
    }

    @Override
    public Status getStatus()
    {
        return status;
    }

    @Override
    public long getPing()
    {
        return ping;
    }

    @Override
    public List<String> getCloudflareRays()
    {
        return Collections.unmodifiableList(new LinkedList<>(client.getCfRays()));
    }

    @Override
    public List<String> getWebSocketTrace()
    {
        return Collections.unmodifiableList(new LinkedList<>(client.getTraces()));
    }

    @Override
    public List<Guild> getMutualGuilds(User... users)
    {
        Checks.notNull(users, "users");
        return getMutualGuilds(Arrays.asList(users));
    }

    @Override
    public List<Guild> getMutualGuilds(Collection<User> users)
    {
        Checks.notNull(users, "users");
        for(User u : users)
        {
            Checks.notNull(u, "All users");
        }
        return Collections.unmodifiableList(getGuilds().stream()
                .filter(guild -> users.stream().allMatch(guild::isMember))
                .collect(Collectors.toList()));
    }

    @Override
    public RestAction<User> retrieveUserById(String id)
    {
        return retrieveUserById(MiscUtil.parseSnowflake(id));
    }

    @Override
    public RestAction<User> retrieveUserById(long id)
    {
        if (accountType != AccountType.BOT)
            throw new AccountTypeException(AccountType.BOT);

        // check cache
        User user = this.getUserById(id);
        if (user != null)
            return new RestAction.EmptyRestAction<>(this, user);

        Route.CompiledRoute route = Route.Users.GET_USER.compile(Long.toUnsignedString(id));
        return new RestAction<User>(this, route)
        {
            @Override
            protected void handleResponse(Response response, Request<User> request)
            {
                if (!response.isOk())
                {
                    request.onFailure(response);
                    return;
                }
                JSONObject user = response.getObject();
                request.onSuccess(getEntityBuilder().createFakeUser(user, false));
            }
        };
    }

    @Override
    public CacheView<AudioManager> getAudioManagerCache()
    {
        return audioManagers;
    }

    @Override
    public SnowflakeCacheView<Guild> getGuildCache()
    {
        return guildCache;
    }

    @Override
    public SnowflakeCacheView<Role> getRoleCache()
    {
        return CacheView.allSnowflakes(() -> guildCache.stream().map(Guild::getRoleCache));
    }

    @Override
    public SnowflakeCacheView<Emote> getEmoteCache()
    {
        return CacheView.allSnowflakes(() -> guildCache.stream().map(Guild::getEmoteCache));
    }

    @Override
    public SnowflakeCacheView<Category> getCategoryCache()
    {
        return categories;
    }

    @Override
    public SnowflakeCacheView<TextChannel> getTextChannelCache()
    {
        return textChannelCache;
    }

    @Override
    public SnowflakeCacheView<VoiceChannel> getVoiceChannelCache()
    {
        return voiceChannelCache;
    }

    @Override
    public SnowflakeCacheView<PrivateChannel> getPrivateChannelCache()
    {
        return privateChannelCache;
    }

    @Override
    public SnowflakeCacheView<User> getUserCache()
    {
        return userCache;
    }

    public SelfUser getSelfUser()
    {
        return selfUser;
    }

    @Override
    public void shutdownNow()
    {
        shutdown();

        pool.shutdownNow();
        getRequester().shutdownNow();
    }

    @Override
    public void shutdown()
    {
        if (status == Status.SHUTDOWN || status == Status.SHUTTING_DOWN)
            return;

        setStatus(Status.SHUTTING_DOWN);
        audioManagers.forEach(AudioManager::closeAudioConnection);
        audioManagers.clear();

        if (audioKeepAlivePool != null)
            audioKeepAlivePool.shutdownNow();

        getClient().shutdown();

        final long time = 5L;
        final TimeUnit unit = TimeUnit.SECONDS;
        getRequester().shutdown(time, unit);
        pool.setKeepAliveTime(time, unit);
        pool.allowCoreThreadTimeOut(true);

        if (shutdownHook != null)
        {
            try
            {
                Runtime.getRuntime().removeShutdownHook(shutdownHook);
            }
            catch (Exception ignored) {}
        }

        setStatus(Status.SHUTDOWN);
    }

    @Override
    public JDAClient asClient()
    {
        if (getAccountType() != AccountType.CLIENT)
            throw new AccountTypeException(AccountType.CLIENT);

        return jdaClient;
    }

    @Override
    public JDABot asBot()
    {
        if (getAccountType() != AccountType.BOT)
            throw new AccountTypeException(AccountType.BOT);

        return jdaBot;
    }

    @Override
    public long getResponseTotal()
    {
        return responseTotal;
    }

    @Override
    public int getMaxReconnectDelay()
    {
        return maxReconnectDelay;
    }

    @Override
    public ShardInfo getShardInfo()
    {
        return shardInfo;
    }

    @Override
    public Presence getPresence()
    {
        return presence;
    }

    //@Override
    //public AuditableRestAction<Void> installAuxiliaryCable(int port) throws UnsupportedOperationException
    //{
    //    return new AuditableRestAction.FailedRestAction<>(new UnsupportedOperationException("nice try but next time think first :)"));
    //}

    @Override
    public AccountType getAccountType()
    {
        return accountType;
    }

    @Override
    public void setEventManager(IEventManager eventManager)
    {
        this.eventManager = eventManager;
    }

    @Override
    public void addEventListener(Object... listeners)
    {
        for (Object listener: listeners)
            eventManager.register(listener);
    }

    @Override
    public void removeEventListener(Object... listeners)
    {
        for (Object listener: listeners)
            eventManager.unregister(listener);
    }

    @Override
    public List<Object> getRegisteredListeners()
    {
        return Collections.unmodifiableList(eventManager.getRegisteredListeners());
    }

    @Override
    public GuildAction createGuild(String name)
    {
        switch (accountType)
        {
            case BOT:
                if (guildCache.size() >= 10)
                    throw new IllegalStateException("Cannot create a Guild with a Bot in more than 10 guilds!");
                break;
            case CLIENT:
                if (guildCache.size() >= 100)
                    throw new IllegalStateException("Cannot be in more than 100 guilds with AccountType.CLIENT!");
        }
        return new GuildAction(this, name);
    }

    public EntityBuilder getEntityBuilder()
    {
        return entityBuilder;
    }

    public GuildLock getGuildLock()
    {
        return this.guildLock;
    }

    public IAudioSendFactory getAudioSendFactory()
    {
        return audioSendFactory;
    }

    public void setAudioSendFactory(IAudioSendFactory factory)
    {
        Checks.notNull(factory, "Provided IAudioSendFactory");
        this.audioSendFactory = factory;
    }

    public void setPing(long ping)
    {
        this.ping = ping;
    }

    public Requester getRequester()
    {
        return requester;
    }

    public IEventManager getEventManager()
    {
        return eventManager;
    }

    public WebSocketFactory getWebSocketFactory()
    {
        return wsFactory;
    }

    public WebSocketClient getClient()
    {
        return client;
    }

    public TLongObjectMap<User> getUserMap()
    {
        return userCache.getMap();
    }

    public TLongObjectMap<Guild> getGuildMap()
    {
        return guildCache.getMap();
    }

    public TLongObjectMap<Category> getCategoryMap()
    {
        return categories.getMap();
    }

    public TLongObjectMap<TextChannel> getTextChannelMap()
    {
        return textChannelCache.getMap();
    }

    public TLongObjectMap<VoiceChannel> getVoiceChannelMap()
    {
        return voiceChannelCache.getMap();
    }

    public TLongObjectMap<PrivateChannel> getPrivateChannelMap()
    {
        return privateChannelCache.getMap();
    }

    public TLongObjectMap<User> getFakeUserMap()
    {
        return fakeUsers;
    }

    public TLongObjectMap<PrivateChannel> getFakePrivateChannelMap()
    {
        return fakePrivateChannels;
    }

    public TLongObjectMap<AudioManager> getAudioManagerMap()
    {
        return audioManagers.getMap();
    }

    public void setSelfUser(SelfUser selfUser)
    {
        this.selfUser = selfUser;
    }

    public void setResponseTotal(int responseTotal)
    {
        this.responseTotal = responseTotal;
    }

    public String getIdentifierString()
    {
        if (shardInfo != null)
            return "JDA " + shardInfo.getShardString();
        else
            return "JDA";
    }

    public EventCache getEventCache()
    {
        return eventCache;
    }

    public OkHttpClient.Builder getHttpClientBuilder()
    {
        return httpClientBuilder;
    }

    private class JDAThreadFactory implements ThreadFactory
    {
        @Override
        public Thread newThread(Runnable r)
        {
            final Thread thread = new Thread(r, "JDA-Thread " + getIdentifierString());
            thread.setDaemon(true);
            return thread;
        }
    }

    public ScheduledThreadPoolExecutor getAudioKeepAlivePool()
    {
        ScheduledThreadPoolExecutor akap = audioKeepAlivePool;
        if (akap == null)
        {
            synchronized (akapLock)
            {
                akap = audioKeepAlivePool;
                if (akap == null)
                    akap = audioKeepAlivePool = new ScheduledThreadPoolExecutor(1, new AudioWebSocket.KeepAliveThreadFactory(this));
            }
        }
        return akap;
    }
}
