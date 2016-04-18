package io.weba.visitor.domain.visit;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.localization.Localization;
import io.weba.visitor.domain.common.AggregateRoot;
import io.weba.visitor.domain.visit.visitor.Visitor;

public final class Visit implements AggregateRoot {
    public static final Integer objectLifetimeInMilliseconds = 1000 * 60 * 30;
    public final VisitId id;
    public final Visitor visitor;
    public final Device device;
    public final Localization localization;

    public Visit(VisitId id, Visitor visitor, Device device, Localization localization) {
        this.id = id;
        this.visitor = visitor;
        this.device = device;
        this.localization = localization;
    }
}
