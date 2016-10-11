/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.event.gson.adapter;

import com.google.common.base.CaseFormat;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.weba.eventor.core.event.Type;

public class TypeSerializer implements JsonSerializer<Type> {
    @Override
    public JsonElement serialize(Type type, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        String eventValue = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, type.toString());

        return new JsonPrimitive(eventValue);
    }
}
