package io.weba.eventor.application.eventor;

import io.weba.eventor.domain.event.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationEventorTest {
    @Test
    public void itExploitEventFromRichAccessLog() throws Exception {
        String accessLog = "{\"request\":{\"id\" : \"7754e3f0-9320-48cc-ae95-fcc8049aec5b\",\"uri\" : \"http:\\/\\/webio.tracker/?t=518de582-e8f1-44e7-8050-cb25cfcb22db&u=http%3A%2F%2Fexample.com%2Fmypage%3Fparam%3Dtrue%23page1&w=1280&h=1024&d1=1&e=1&rd=2004-02-12T15:19:21+00:00\", \"method\":\"GET\",\"headers\":\"R0VUIC8/dD01MThkZTU4Mi1lOGYxLTQ0ZTctODA1MC1jYjI1Y2ZjYjIyZGImdT1odHRwJTNBJTJGJTJGZXhhbXBsZS5jb20lMkZteXBhZ2UlM0ZwYXJhbSUzRHRydWUlMjNwYWdlMSZ3PTEyODAmaD0xMDI0JmQxPTEgSFRUUC8xLjENCkhvc3Q6IHdlYmlvLnRyYWNrZXINCkNvbm5lY3Rpb246IGtlZXAtYWxpdmUNClByYWdtYTogbm8tY2FjaGUNCkNhY2hlLUNvbnRyb2w6IG5vLWNhY2hlDQpBY2NlcHQ6IHRleHQvaHRtbCxhcHBsaWNhdGlvbi94aHRtbCt4bWwsYXBwbGljYXRpb24veG1sO3E9MC45LGltYWdlL3dlYnAsKi8qO3E9MC44DQpVcGdyYWRlLUluc2VjdXJlLVJlcXVlc3RzOiAxDQpVc2VyLUFnZW50OiBNb3ppbGxhLzUuMCAoTWFjaW50b3NoOyBJbnRlbCBNYWMgT1MgWCAxMF8xMV8zKSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvNDguMC4yNTY0LjExNiBTYWZhcmkvNTM3LjM2DQpBY2NlcHQtRW5jb2Rpbmc6IGd6aXAsIGRlZmxhdGUsIHNkY2gNCkFjY2VwdC1MYW5ndWFnZTogcGwtUEwscGw7cT0wLjgsZW4tVVM7cT0wLjYsZW47cT0wLjQNCkNvb2tpZTogdmlzaXRvcl9pZGVudGl0eT1iN2IyMWUzYi00ZGE1LTQzZGMtYTBiZi1lZjMyMTZhYjVlZTUNCg0K\",\"content\":\"\",\"date\":\"2016-03-05T21:02:40+00:00\",\"remote_addr\":\"100.0.0.1\",\"x_forwarded_for\":\"-\"},\"response\":{\"status\":\"200\", \"headers\":\"c2V0LWNvb2tpZTp2aXNpdG9yX2lkZW50aXR5PWI3YjIxZTNiLTRkYTUtNDNkYy1hMGJmLWVmMzIxNmFiNWVlNTsgRXhwaXJlcz1UdWUsIDAzLU1hci0yNiAyMTowMjo0MCBHTVQ7IE1heC1BZ2U9MzE1MzYwMDAwOyBEb21haW49LndlYmlvLnRyYWNrZXI7IFBhdGg9Lw0KdHJhbnNmZXItZW5jb2Rpbmc6Y2h1bmtlZA0KY29udGVudC10eXBlOmFwcGxpY2F0aW9uL2pzb24NCmNvbm5lY3Rpb246Y2xvc2UNCg==\"}, \"localization\" : {\"country\" : \"USA\", \"region\" : \"MA\", \"city\" : \"Northborough\", \"postal_code\" : \"01532\", \"continent\" : \"NA\", \"latitude\" : \"42.3218\", \"longitude\" : \"-71.6350\"}}";
        ApplicationEventor eventor = new ApplicationEventor();
        Event event = eventor.exploitFromAccessLog(accessLog);

        assertEquals("7754e3f0-9320-48cc-ae95-fcc8049aec5b", event.id.toString());
        assertEquals(Type.PAGE_VIEW, event.type);
        assertEquals(new TrackerId("518de582-e8f1-44e7-8050-cb25cfcb22db"), event.payload.getTrackerId());
        assertEquals(new URL("http://example.com/mypage?param=true#page1"), event.payload.getUrl());
        assertEquals(new UserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36"), event.payload.getUserAgent());
        assertEquals(new VisitorIdentity("b7b21e3b-4da5-43dc-a0bf-ef3216ab5ee5"), event.payload.getVisitorIdentity());
        assertNotEquals(event.dates.browserDate, event.dates.serverDate);

        assertEquals("USA", event.localization.country);
        assertEquals("MA", event.localization.region);
        assertEquals("Northborough", event.localization.city);
        assertEquals("01532", event.localization.postalCode);
        assertEquals("NA", event.localization.continent);
        assertEquals("42.3218", event.localization.latitude);
        assertEquals("-71.6350", event.localization.longitude);
    }

    @Test
    public void itExploitEventFromPixelAccessLog() throws Exception {
        String accessLog = "{\"request\":{\"id\" : \"8effe8e5-cbf6-4bd7-8b19-e58b377f06c8\",\"uri\" : \"http:\\/\\/webio.tracker/?t=518de582-e8f1-44e7-8050-cb25cfcb22db\", \"method\":\"GET\",\"headers\":\"R0VUIC8gSFRUUC8xLjENCkhvc3Q6IHdlYmlvLnRyYWNrZXINCkNvbm5lY3Rpb246IGtlZXAtYWxpdmUNCkNhY2hlLUNvbnRyb2w6IG1heC1hZ2U9MA0KQWNjZXB0OiBpbWFnZS93ZWJwLGltYWdlLyosKi8qO3E9MC44DQpVc2VyLUFnZW50OiBNb3ppbGxhLzUuMCAoTWFjaW50b3NoOyBJbnRlbCBNYWMgT1MgWCAxMF8xMV8zKSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvNDguMC4yNTY0LjExNiBTYWZhcmkvNTM3LjM2DQpSZWZlcmVyOiBodHRwOi8vY29va2llcy5zZWxsZXI6ODAwNC9ub3NjcmlwdC5odG1sDQpBY2NlcHQtRW5jb2Rpbmc6IGd6aXAsIGRlZmxhdGUsIHNkY2gNCkFjY2VwdC1MYW5ndWFnZTogcGwtUEwscGw7cT0wLjgsZW4tVVM7cT0wLjYsZW47cT0wLjQNCkNvb2tpZTogdmlzaXRvcl9pZGVudGl0eT04NDRiM2VkMy04YTVlLTQ1NjEtYWY3OS1jNTc2YTczYzAyODgNCg0K\",\"content\":\"\",\"date\":\"2016-03-06T13:50:31+00:00\",\"remote_addr\":\"100.0.0.1\",\"x_forwarded_for\":\"-\"},\"response\":{\"status\":\"200\", \"headers\":\"c2V0LWNvb2tpZTp2aXNpdG9yX2lkZW50aXR5PTg0NGIzZWQzLThhNWUtNDU2MS1hZjc5LWM1NzZhNzNjMDI4ODsgRXhwaXJlcz1XZWQsIDA0LU1hci0yNiAxMzo1MDozMSBHTVQ7IE1heC1BZ2U9MzE1MzYwMDAwOyBEb21haW49LndlYmlvLnRyYWNrZXI7IFBhdGg9Lw0KdHJhbnNmZXItZW5jb2Rpbmc6Y2h1bmtlZA0KY29udGVudC10eXBlOmFwcGxpY2F0aW9uL2pzb24NCmNvbm5lY3Rpb246Y2xvc2UNCg==\"}, \"localization\" : {\"country\" : \"USA\", \"region\" : \"MA\", \"city\" : \"Northborough\", \"postal_code\" : \"01532\", \"continent\" : \"NA\", \"latitude\" : \"42.3218\", \"longitude\" : \"-71.6350\"}}";
        ApplicationEventor eventor = new ApplicationEventor();
        Event event = eventor.exploitFromAccessLog(accessLog);

        assertEquals("8effe8e5-cbf6-4bd7-8b19-e58b377f06c8", event.id.toString());
        assertEquals(Type.PAGE_VIEW, event.type);
        assertEquals(new TrackerId("518de582-e8f1-44e7-8050-cb25cfcb22db"), event.payload.getTrackerId());
        assertEquals(new URL("http://cookies.seller:8004/noscript.html"), event.payload.getUrl());
        assertEquals(new UserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36"), event.payload.getUserAgent());
        assertEquals(new VisitorIdentity("844b3ed3-8a5e-4561-af79-c576a73c0288"), event.payload.getVisitorIdentity());
        assertEquals(event.dates.browserDate, event.dates.serverDate);

        assertEquals("USA", event.localization.country);
        assertEquals("MA", event.localization.region);
        assertEquals("Northborough", event.localization.city);
        assertEquals("01532", event.localization.postalCode);
        assertEquals("NA", event.localization.continent);
        assertEquals("42.3218", event.localization.latitude);
        assertEquals("-71.6350", event.localization.longitude);
    }
}