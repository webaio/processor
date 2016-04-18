package io.weba.visitor.domain.action;

import io.weba.eventor.domain.event.URL;
import io.weba.visitor.domain.common.AggregateRoot;
import io.weba.visitor.domain.visit.VisitId;

import java.util.Date;

public class Action implements AggregateRoot {
    public final ActionId id;
    public final VisitId visitId;
    public final URL url;
    public final Date actionDate;
    public final Date serverActionDate;

    public Action(ActionId id, VisitId visitId, URL url, Date actionDate, Date serverActionDate) {
        this.id = id;
        this.visitId = visitId;
        this.url = url;
        this.actionDate = actionDate;
        this.serverActionDate = serverActionDate;
    }
}
