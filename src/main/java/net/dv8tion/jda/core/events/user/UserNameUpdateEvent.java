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
package net.dv8tion.jda.core.events.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.User;

/**
 * <b><u>UserNameUpdateEvent</u></b><br>
 * Fired if the username of a {@link net.dv8tion.jda.core.entities.User User} changes. (Not Nickname)<br>
 * <br>
 * Use: Retrieve the User who's username changed and their previous username.
 */
public class UserNameUpdateEvent extends GenericUserEvent
{
    private final String oldName;
    private final String oldDiscriminator;

    public UserNameUpdateEvent(JDA api, long responseNumber, User user, String oldName, String oldDiscriminator)
    {
        super(api, responseNumber, user);
        this.oldName = oldName;
        this.oldDiscriminator = oldDiscriminator;
    }

    public String getOldName()
    {
        return oldName;
    }

    public String getOldDiscriminator()
    {
        return oldDiscriminator;
    }
}
