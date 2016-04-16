package io.weba.visitor.domain.visit;

import io.weba.eventor.domain.event.Event;
import io.weba.visitor.domain.visit.visitor.Visitor;
import io.weba.visitor.domain.visit.visitor.VisitorId;

public class VisitBuilder {
    public Visit buildFromEvent(Event event) {
        return new Visit(
                new VisitId(VisitId.generate()),
                Visitor.createNewVisitorForVisit(
                        new VisitorId(event.payload.getVisitorIdentity().visitorIdentity),
                        event.dates.browserDate,
                        event.dates.serverDate
                ),
                event.device,
                event.localization
        );
    }
}
