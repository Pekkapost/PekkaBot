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

package net.dv8tion.jda.core.requests;

import net.dv8tion.jda.core.JDA;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SessionReconnectQueue
{
    private static final int RECONNECT_DELAY = WebSocketClient.IDENTIFY_DELAY * 1000;
    protected final Object lock = new Object();
    protected final BlockingQueue<WebSocketClient> reconnectQueue;
    protected volatile Thread reconnectThread;

    public SessionReconnectQueue()
    {
        this(new LinkedBlockingQueue<>());
    }

    public SessionReconnectQueue(final BlockingQueue<WebSocketClient> reconnectQueue)
    {
        this.reconnectQueue = reconnectQueue;
    }

    protected void appendSession(final WebSocketClient client)
    {
        if (!reconnectQueue.offer(client))
            throw new IllegalStateException("Queue rejected session");
        runWorker();
    }

    protected void runWorker()
    {
        synchronized (lock)
        {
            if (reconnectThread == null)
                reconnectThread = new ReconnectThread();
        }
    }

    protected final class ReconnectThread extends Thread
    {
        protected ReconnectThread()
        {
            super("JDA-ReconnectThread");
            start();
        }

        @Override
        public final void run()
        {
            boolean isFirst = true;
            while (!reconnectQueue.isEmpty())
            {
                try
                {
                    final WebSocketClient client = reconnectQueue.poll();
                    client.reconnect(true, isFirst);
                    isFirst = false;

                    if (!reconnectQueue.isEmpty())
                    {
                        // Wait for the client to actually send
                        while (client.getJDA().getStatus().ordinal() < JDA.Status.AWAITING_LOGIN_CONFIRMATION.ordinal())
                            Thread.sleep(50);
                        // Respect 5 second backoff
                        Thread.sleep(RECONNECT_DELAY);
                    }
                }
                catch (InterruptedException ex)
                {
                    throw new AssertionError(ex);
                }
            }
            synchronized (lock)
            {
                reconnectThread = null;
                if (!reconnectQueue.isEmpty())
                    runWorker(); // eliminate highly unlikely race condition
            }
        }
    }

}
