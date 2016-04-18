package io.weba.visitor.domain.action;

import io.weba.eventor.domain.event.URL;
import io.weba.visitor.domain.visit.VisitId;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ActionTest {
    @Test
    public void itCreatesNewAction() {
        Action action =  new Action(
                new ActionId(ActionId.fromString("abf9bfbb-1944-4844-a45b-4492f910f516")),
                new VisitId(VisitId.generate()),
                new URL("http://weba.io"),
                new Date(),
                new Date()
        );

        assertEquals(action.id.toString(), "abf9bfbb-1944-4844-a45b-4492f910f516");
    }
}
