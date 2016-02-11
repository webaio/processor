package io.weba.eventor.domain.event;

import org.junit.Test;

public class IdTest {
    @Test(expected = IllegalArgumentException.class)
    public void itAcceptOnlyValidUUID() {
        new ID("UUID");
    }
}