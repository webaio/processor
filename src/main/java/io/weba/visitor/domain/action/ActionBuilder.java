package io.weba.visitor.domain.action;

import io.weba.eventor.domain.event.Event;
import io.weba.visitor.domain.visit.Visit;

public class ActionBuilder {
    public Action buildForVisit(Visit visit, Event event) {
        return new Action(
                new ActionId(ActionId.generate()),
                visit.id,
                event.payload.getUrl(),
                event.dates.browserDate,
                event.dates.serverDate
        );
    }
}
