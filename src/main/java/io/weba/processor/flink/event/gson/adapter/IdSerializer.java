/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.event.gson.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.weba.eventor.core.event.Id;

public class IdSerializer implements JsonSerializer<Id> {
    @Override
    public JsonElement serialize(Id id, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(id.toString());
    }
}
