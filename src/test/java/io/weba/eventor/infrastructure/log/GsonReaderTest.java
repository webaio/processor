package io.weba.eventor.infrastructure.log;

import io.weba.eventor.domain.http.Method;
import io.weba.eventor.domain.log.HttpLog;
import io.weba.eventor.infrastructure.log.gson.GsonFactory;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GsonReaderTest {
    @Test
    public void itShouldReadRequestFromValidJsonLog() throws Exception {
        String accessLog = "{\"request\":{\"id\" : \"7754e3f0-9320-48cc-ae95-fcc8049aec5b\",\"uri\" : \"http:\\/\\/webio.tracker/?t=518de582-e8f1-44e7-8050-cb25cfcb22db&u=http%3A%2F%2Fexample.com%2Fmypage%3Fparam%3Dtrue%23page1&w=1280&h=1024&d1=1&e=1\", \"method\":\"GET\",\"headers\":\"R0VUIC8/dD01MThkZTU4Mi1lOGYxLTQ0ZTctODA1MC1jYjI1Y2ZjYjIyZGImdT1odHRwJTNBJTJGJTJGZXhhbXBsZS5jb20lMkZteXBhZ2UlM0ZwYXJhbSUzRHRydWUlMjNwYWdlMSZ3PTEyODAmaD0xMDI0JmQxPTEgSFRUUC8xLjENCkhvc3Q6IHdlYmlvLnRyYWNrZXINCkNvbm5lY3Rpb246IGtlZXAtYWxpdmUNClByYWdtYTogbm8tY2FjaGUNCkNhY2hlLUNvbnRyb2w6IG5vLWNhY2hlDQpBY2NlcHQ6IHRleHQvaHRtbCxhcHBsaWNhdGlvbi94aHRtbCt4bWwsYXBwbGljYXRpb24veG1sO3E9MC45LGltYWdlL3dlYnAsKi8qO3E9MC44DQpVcGdyYWRlLUluc2VjdXJlLVJlcXVlc3RzOiAxDQpVc2VyLUFnZW50OiBNb3ppbGxhLzUuMCAoTWFjaW50b3NoOyBJbnRlbCBNYWMgT1MgWCAxMF8xMV8zKSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvNDguMC4yNTY0LjExNiBTYWZhcmkvNTM3LjM2DQpBY2NlcHQtRW5jb2Rpbmc6IGd6aXAsIGRlZmxhdGUsIHNkY2gNCkFjY2VwdC1MYW5ndWFnZTogcGwtUEwscGw7cT0wLjgsZW4tVVM7cT0wLjYsZW47cT0wLjQNCkNvb2tpZTogdmlzaXRvcl9pZGVudGl0eT1iN2IyMWUzYi00ZGE1LTQzZGMtYTBiZi1lZjMyMTZhYjVlZTUNCg0K\",\"content\":\"\",\"date\":\"2016-03-05T21:02:40+00:00\",\"remote_addr\":\"100.0.0.1\",\"x_forwarded_for\":\"-\"},\"response\":{\"status\":\"200\", \"headers\":\"c2V0LWNvb2tpZTp2aXNpdG9yX2lkZW50aXR5PWI3YjIxZTNiLTRkYTUtNDNkYy1hMGJmLWVmMzIxNmFiNWVlNTsgRXhwaXJlcz1UdWUsIDAzLU1hci0yNiAyMTowMjo0MCBHTVQ7IE1heC1BZ2U9MzE1MzYwMDAwOyBEb21haW49LndlYmlvLnRyYWNrZXI7IFBhdGg9Lw0KdHJhbnNmZXItZW5jb2Rpbmc6Y2h1bmtlZA0KY29udGVudC10eXBlOmFwcGxpY2F0aW9uL2pzb24NCmNvbm5lY3Rpb246Y2xvc2UNCg==\"}, \"localization\" : {\"country\" : \"USA\", \"region\" : \"MA\", \"city\" : \"Northborough\", \"postal_code\" : \"01532\", \"continent\" : \"NA\", \"latitude\" : \"42.3218\", \"longitude\" : \"-71.6350\"}}";
        GsonReader reader = new GsonReader(GsonFactory.create());

        HttpLog httpLog = reader.read(accessLog);

        assertEquals("7754e3f0-9320-48cc-ae95-fcc8049aec5b", httpLog.request.id);
        assertEquals("100.0.0.1", httpLog.request.IP);
        assertEquals(Method.GET, httpLog.request.method);
        assertEquals(200, httpLog.response.statusCode);
        assertTrue(httpLog.request.headers.bag.size() > 0);
        assertTrue(httpLog.response.headers.bag.size() > 0);
        assertEquals(new DateTime("2016-03-05T21:02:40+00:00").toDate(), httpLog.request.date);
        assertEquals(
                new String(Base64.getDecoder().decode("")),
                httpLog.request.content
        );
        assertEquals("USA", httpLog.localization.country);
        assertEquals("MA", httpLog.localization.region);
        assertEquals("Northborough", httpLog.localization.city);
        assertEquals("01532", httpLog.localization.postalCode);
        assertEquals("NA", httpLog.localization.continent);
        assertEquals("42.3218", httpLog.localization.latitude);
        assertEquals("-71.6350", httpLog.localization.longitude);
    }

    @Test(expected= NullPointerException.class)
    public void itShouldNotReadRequestFromInvalidJsonLog() throws Exception {
        GsonReader reader = new GsonReader(GsonFactory.create());

        String accessLog = "{\"localization\" : {}}";
        HttpLog httpLog = reader.read(accessLog);
    }
}