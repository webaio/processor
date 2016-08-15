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
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.util.Map;

public class MapSerializer implements JsonSerializer<Map<String, Object>> {
    @Override
    public JsonObject serialize(Map<String, Object> map, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jsonObject.add(
                    CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()),
                    context.serialize(entry.getValue())
            );
        }

        return jsonObject;
    }
}
