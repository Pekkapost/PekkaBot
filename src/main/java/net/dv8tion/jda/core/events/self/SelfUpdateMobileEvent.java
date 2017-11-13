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

package net.dv8tion.jda.core.events.self;

import net.dv8tion.jda.core.JDA;

/**
 * <b><u>SelfUpdateMobileEvent</u></b><br>
 * Fired if you login to your discord account with a mobile device for the first time.<br>
 */
public class SelfUpdateMobileEvent extends GenericSelfUpdateEvent
{
    private final boolean wasMobile;

    public SelfUpdateMobileEvent(JDA api, long responseNumber, boolean wasMobile)
    {
        super(api, responseNumber);
        this.wasMobile = wasMobile;
    }

    /**
     * Returns the old mobile status. <i>Should</i> always be {@code false}.
     *
     * @return The mobile status.
     */
    public boolean wasMobile()
    {
        return wasMobile;
    }
}
