package io.weba.collector.infrastructure.application.serializer;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class GsonSerializerImplTest {

    @Test
    public void testSerialize() throws Exception {
        GsonSerializerImpl gsonSerializer = new GsonSerializerImpl();
        String date = gsonSerializer.serialize(new Date());

        assertNotNull(date);
    }
}
