package unit.eventor.accesslog.device.features;

import io.weba.eventor.accesslog.AccessLog;
import io.weba.eventor.accesslog.device.features.WebTrackerDeviceInfoRecognizerFactory;
import io.weba.eventor.accesslog.device.features.WebTrackerDeviceInfoRecognizerImpl;
import io.weba.eventor.core.event.EventBuilder;
import io.weba.eventor.core.exception.DropEventException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class WebTrackerDeviceInfoRecognizerImplTest {
    private WebTrackerDeviceInfoRecognizerImpl recognizer;

    @Before
    public void setUp() throws Exception {
        recognizer = new WebTrackerDeviceInfoRecognizerFactory().create();
    }

    @Test
    public void itShouldRecognizeDeviceFeatures() throws DropEventException {
        AccessLog log = createAccessLog("http://weba.io?di=0x1db7");

        HashMap<String, Object> features = new HashMap<>();
        recognizer.recognize(log, features);

        assertFalse(features.isEmpty());
    }

    @Test
    public void itShouldRecognizeDeviceFromEmptyParameter() throws DropEventException {
        AccessLog log = createAccessLog("http://weba.io?di=");

        HashMap<String, Object> features = new HashMap<>();
        recognizer.recognize(log, features);

        assertFalse(features.isEmpty());
    }

    @Test
    public void itShouldRecognizeDeviceIfDeviceParameterDoesNotExists() throws DropEventException {
        AccessLog log = createAccessLog("http://weba.io");

        HashMap<String, Object> features = new HashMap<>();
        recognizer.recognize(log, features);

        assertFalse(features.isEmpty());
    }

    private AccessLog createAccessLog(String url) throws DropEventException {
        HashMap<String, Object> log = new HashMap<>();
        log.put("request", new HashMap<String, Object>() {{
            put("uri", url);
            put("headers", "");
        }});
        log.put("response", new HashMap<String, Object>() {{
            put("headers", "");
        }});

        return new AccessLog(log, new EventBuilder());
    }
}