package io.weba.visitor.infrastructure.application.storage.elasticsearch;

import io.weba.eventor.domain.event.URL;
import io.weba.visitor.domain.action.Action;
import io.weba.visitor.domain.action.ActionId;
import io.weba.visitor.domain.visit.VisitId;
import io.weba.visitor.infrastructure.application.serializer.GsonSerializerImpl;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ActionMapperTest {
    @Test
    public void itMapsObject() {
        ActionMapper mapper = new ActionMapper(new GsonSerializerImpl());
        Action action = new Action(
                new ActionId(ActionId.fromString("591943a7-0664-4248-b2a3-6e0fac2e459d")),
                new VisitId(VisitId.generate()),
                new URL("http://weba.io"),
                new Date(),
                new Date()
        );

        Map<String, String> mapped = mapper.mapFields(action);

        mapper.getExpiresAt(action);
        mapper.reconstituteFromData(mapped);
        mapper.getFields();
        mapper.getIncrementField();
        mapper.getExpiresAt(action);

        assertEquals("591943a7-0664-4248-b2a3-6e0fac2e459d", mapper.getIdentifier(action));
        assertTrue(mapped.containsKey("object"));
    }
}
