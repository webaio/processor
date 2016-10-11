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
import io.weba.eventor.core.event.visitor.Visitor;

public class VisitorSerializer implements JsonSerializer<Visitor> {
    @Override
    public JsonObject serialize(Visitor visitor, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        JsonObject visitorJsonObject = new JsonObject();
        visitorJsonObject.add(
                "id",
                new JsonPrimitive(visitor.id.visitorIdentity.toString())
        );
        visitorJsonObject.add(
                "first_visit_date",
                context.serialize(visitor.firstVisitDate)
        );

        return visitorJsonObject;
    }
}
