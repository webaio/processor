package integration.eventor.accesslog;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.weba.eventor.accesslog.AccessLogEventorImpl;
import io.weba.eventor.core.enriched.device.Device;
import io.weba.eventor.core.enriched.localization.Localization;
import io.weba.eventor.core.event.Event;
import io.weba.eventor.core.event.Type;
import io.weba.eventor.core.exception.EventorException;
import io.weba.processor.flink.event.gson.factory.EventFactory;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AccessLogEventorImplTest {
    final private AccessLogEventorImpl eventor;
    final private Gson gson;
    final private JsonParser parser = new JsonParser();

    public AccessLogEventorImplTest() throws IOException {
        eventor = AccessLogEventorImpl.newInstance();
        gson = new EventFactory().create();
    }

    @Test
    public void itShouldExploitPageViewAccessLog() throws Exception {
        assertEventsEquals("accesslog/pageView.json", "events/pageView.json");
    }

    @Test
    public void itShouldExploitPageViewPixelFallbackAccessLog() throws Exception {
        Event event = eventor.exploitEvent(readJsonResource("accesslog/pageViewPixelFallback.json"));

        Date date = new DateTime("2016-08-21T10:22:27+00:00").toDate();
        assertEquals("471c963b-d279-4ef7-9cf0-80621f5ea0b8", event.id.toString());
        assertEquals(Type.PAGE_VIEW, event.type);

        assertFalse(event.visitor.id.toString().isEmpty());
        assertEquals(date, event.visitor.firstVisitDate);

        assertFalse(event.session.id.toString().isEmpty());
        assertEquals(date, event.session.startDate);
        assertEquals(
                new DateTime(event.session.startDate).plusMinutes(30).toDate(),
                event.session.endDate
        );
        assertTrue(event.enriched.get("localization") instanceof Localization);
        assertTrue(event.enriched.get("device") instanceof Device);
        assertEquals("https://mail.google.com/mail/u/0/#inbox/", event.payload.get("urlFull").toString());

        assertEquals(date, event.dates.client);
        assertEquals(date, event.dates.server);
    }

    @Test
    public void itShouldDropEventDueToLackOfTrackerId() throws FileNotFoundException {
        try {
            assertEventsEquals("accesslog/pageViewWithoutTrackerId.json", "events/pageView.json");
        } catch (EventorException e) {
            assertEquals("Tracker ID is required in rich event and in pixel fallback.", e.getMessage());
        }
    }

    private String readJsonResource(String name) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(String.format(name)).getFile());

        return new Scanner(file, "UTF8").useDelimiter("\\Z").next();
    }

    private void assertEventsEquals(String accessLogPath, String eventJsonPath) throws FileNotFoundException, EventorException {
        Event event = eventor.exploitEvent(readJsonResource(accessLogPath));

        JsonElement json1 = parser.parse(gson.toJson(event));
        JsonElement json2 = parser.parse(readJsonResource(eventJsonPath));
        assertEquals(json1, json2);
    }
}