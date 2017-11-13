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

package net.dv8tion.jda.core.events.role.update;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Role;

import java.awt.Color;

public class RoleUpdateColorEvent extends GenericRoleUpdateEvent
{
    private final Color oldColor;

    public RoleUpdateColorEvent(JDA api, long responseNumber, Role role, Color oldColor)
    {
        super(api, responseNumber, role);
        this.oldColor = oldColor;
    }

    public Color getOldColor()
    {
        return oldColor;
    }
}
