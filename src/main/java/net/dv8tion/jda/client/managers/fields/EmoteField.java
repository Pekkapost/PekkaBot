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

package net.dv8tion.jda.client.managers.fields;

import net.dv8tion.jda.client.managers.EmoteManagerUpdatable;
import net.dv8tion.jda.core.managers.fields.Field;

import java.util.function.Supplier;

/**
 * Specification AMF for
 * an {@link net.dv8tion.jda.client.managers.EmoteManagerUpdatable EmoteManagerUpdatable}
 * field.
 *
 * <p><b>This class is abstract and requires an implementation
 * for {@link #checkValue(Object)}</b>
 *
 * @param  <T>
 *         The Field-Type for this EmoteField
 *
 * @since  3.0
 */
public abstract class EmoteField<T> extends Field<T, EmoteManagerUpdatable>
{
    public EmoteField(EmoteManagerUpdatable manager, Supplier<T> originalValue)
    {
        super(manager, originalValue);
    }
}
