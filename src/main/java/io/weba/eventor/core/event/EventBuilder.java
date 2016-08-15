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

public class EventBuilder {
    public Id id;
    public Type type = Type.PAGE_VIEW;
    public Payload payload = new Payload();
    public Enriched enriched = new Enriched();
    public Visitor visitor;
    public Dates dates;
    public Session session;

    public Event build() {
        return new Event(id,  type, visitor, session, enriched, payload, dates);
    }

    public void clean() {
        id = null;
        type = Type.PAGE_VIEW;
        payload = new Payload();
        enriched = new Enriched();
        visitor = null;
        dates = null;
        session = null;
    }

}
