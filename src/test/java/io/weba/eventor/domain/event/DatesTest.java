package io.weba.eventor.domain.event;

import org.junit.Test;

import java.util.Date;

public class DatesTest {
    @Test(expected = NullPointerException.class)
    public void itThrowExceptionIfAnyArgumentIsNull() {
        new Dates(null, new Date());
    }
}