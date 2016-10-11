/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.event.gson.factory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import io.weba.eventor.core.event.Id;
import io.weba.eventor.core.event.Payload;
import io.weba.eventor.core.event.Type;
import io.weba.eventor.core.event.session.Session;
import io.weba.eventor.core.event.visitor.Visitor;
import io.weba.processor.flink.event.gson.adapter.*;

import java.util.Map;

public class EventFactory implements Factory {
    public Gson create() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Id.class, new IdSerializer());
        gsonBuilder.registerTypeAdapter(Type.class, new TypeSerializer());
        gsonBuilder.registerTypeAdapter(Visitor.class, new VisitorSerializer());
        gsonBuilder.registerTypeAdapter(Session.class, new SessionSerializer());
        gsonBuilder.registerTypeAdapter(Map.class, new MapSerializer());
        gsonBuilder.registerTypeAdapter(Payload.class, new MapSerializer());
        gsonBuilder.serializeNulls();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        gsonBuilder.enableComplexMapKeySerialization();

        return gsonBuilder.create();
    }
}
