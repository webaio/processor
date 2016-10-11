/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.event.gson.adapter;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.weba.eventor.core.event.session.Session;

public class SessionSerializer implements JsonSerializer<Session> {
    @Override
    public JsonObject serialize(Session session, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        JsonObject sessionJsonObject = new JsonObject();
        sessionJsonObject.add(
                "id",
                new JsonPrimitive(session.id.id.toString())
        );
        sessionJsonObject.add(
                "start_date",
                context.serialize(session.startDate)
        );
        sessionJsonObject.add(
                "end_date",
                context.serialize(session.endDate)
        );

        return sessionJsonObject;
    }
}
