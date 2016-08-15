/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.event;

import io.weba.eventor.core.event.session.Session;
import io.weba.eventor.core.event.visitor.Visitor;

import java.util.Objects;

public class Event {
    public final Id id;
    public final Type type;
    public final Visitor visitor;
    public final Session session;
    public final Enriched enriched;
    public final Payload payload;
    public final Dates dates;

    public Event(Id id, Type type, Visitor visitor, Session session, Enriched enriched, Payload payload, Dates dates) {
        this.id = Objects.requireNonNull(id);
        this.type = Objects.requireNonNull(type);
        this.visitor = Objects.requireNonNull(visitor);
        this.session = Objects.requireNonNull(session);
        this.enriched = Objects.requireNonNull(enriched);
        this.payload = Objects.requireNonNull(payload);
        this.dates = Objects.requireNonNull(dates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        return id.equals(event.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
