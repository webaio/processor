package unit.eventor.accesslog.dates;

import io.weba.eventor.accesslog.AccessLog;
import io.weba.eventor.accesslog.dates.RequestDates;
import io.weba.eventor.accesslog.dates.RequestDatesProviderImpl;
import io.weba.eventor.core.event.EventBuilder;
import io.weba.eventor.core.exception.DropEventException;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RequestDatesProviderImplTest {
    private RequestDatesProviderImpl provider = new RequestDatesProviderImpl();

    @Test(expected = IllegalArgumentException.class)
    public void itThrowExceptionIfRequestDoesNotContainsServerRequestDate() throws Exception {
        String requestDateString = "";
        AccessLog accessLog = createAccessLog(requestDateString, "http://weba.io?st=1471107270.1471107970.1471107670.1471107970");

        provider.provideRequestDates(accessLog);
    }

    @Test(expected = DropEventException.class)
    public void itThrowExceptionIfSessionStartDateIsInvalid() throws Exception {
        String requestDateString = "2016-08-13T18:01:10+00:00";
        AccessLog accessLog = createAccessLog(requestDateString, "http://weba.io?st=1471107270.1471107970.1471107670.1471107970");

        provider.provideRequestDates(accessLog);
    }

    @Test
    public void itProvidesDatesFromValidTimestamps() throws Exception {
        String requestDateString = "2016-08-13T18:01:10+00:00";
        AccessLog accessLog = createAccessLog(requestDateString, "http://weba.io?st=1471107270000.1471107670000.1471109470000.1471107970000");

        RequestDates requestDates = provider.provideRequestDates(accessLog);

        DateTime requestDateTime = new DateTime(requestDateString);
        assertEquals(new DateTime(Long.decode("1471107970000")).toDate(), requestDates.requestClientDate);
        assertEquals(requestDateTime.toDate(), requestDates.requestServerDate);
        assertEquals(new DateTime(Long.decode("1471107270000")).toDate(), requestDates.firstVisitDate);
        assertEquals(new DateTime(Long.decode("1471107670000")).toDate(), requestDates.sessionStartDate);
        assertEquals(new DateTime(Long.decode("1471109470000")).toDate(), requestDates.sessionEndDate);
    }

    @Test
    public void itProvidesDatesEvenIfRequestContainsInvalidTimestamps() throws Exception {
        String requestDateString = "2016-08-13T18:01:10+00:00";
        AccessLog accessLog = createAccessLog(requestDateString, "http://weba.io?st=invalid");

        RequestDates requestDates = provider.provideRequestDates(accessLog);

        DateTime requestDateTime = new DateTime(requestDateString);
        assertEquals(requestDateTime.toDate(), requestDates.requestClientDate);
        assertEquals(requestDateTime.toDate(), requestDates.requestServerDate);
        assertEquals(requestDateTime.toDate(), requestDates.firstVisitDate);
        assertEquals(requestDateTime.toDate(), requestDates.sessionStartDate);
        assertEquals(new DateTime(requestDateTime.plusMinutes(30)).toDate(), requestDates.sessionEndDate);
    }

    @Test
    public void itProvidesDatesEvenIfRequestDoesNotContainsTimestamps() throws Exception {
        String requestDateString = "2016-08-13T18:01:10+00:00";
        AccessLog accessLog = createAccessLog(requestDateString, "http://weba.io");

        RequestDates requestDates = provider.provideRequestDates(accessLog);

        DateTime requestDateTime = new DateTime(requestDateString);
        assertEquals(requestDateTime.toDate(), requestDates.requestClientDate);
        assertEquals(requestDateTime.toDate(), requestDates.requestServerDate);
        assertEquals(requestDateTime.toDate(), requestDates.firstVisitDate);
        assertEquals(requestDateTime.toDate(), requestDates.sessionStartDate);
        assertEquals(new DateTime(requestDateTime.plusMinutes(30)).toDate(), requestDates.sessionEndDate);
    }

    private AccessLog createAccessLog(String requestDate, String uri) throws DropEventException {
        HashMap<String, Object> log = new HashMap<>();
        log.put("request", new HashMap<String, Object>() {{
            put("date", requestDate);
            put("uri", uri);
            put("headers", "");
        }});
        log.put("response", new HashMap<String, Object>() {{
            put("headers", "");
        }});

        return new AccessLog(log, new EventBuilder());
    }
}