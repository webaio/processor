package io.weba.eventor.domain.event;

import io.weba.eventor.domain.exception.EventorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPTest {
    @Test(expected = EventorException.class)
    public void itAcceptOnlyValidIP() throws EventorException {
        new IP("IP");
    }

    @Test
    public void onlyObjectWithSameIPShouldBeEquals() throws EventorException {
        IP ip1 = new IP("100.0.1.129");
        IP ip2 = new IP("100.0.1.129");
        IP ip3 = new IP("100.0.1.130");

        assertEquals(ip1, ip2);
        assertNotEquals(ip1, ip3);
    }
}