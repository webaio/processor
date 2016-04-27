package io.weba.collector.infrastructure.application.collector;

import io.weba.collector.application.collector.Collector;
import io.weba.collector.application.collector.CollectorException;
import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.application.storage.transactional.exception.ClientException;
import io.weba.collector.domain.eventaggregator.EventAggregator;
import io.weba.collector.domain.eventaggregator.EventAggregatorId;
import io.weba.collector.domain.eventaggregator.EventAggregatorRegistry;
import io.weba.collector.domain.eventsession.EventSession;
import io.weba.collector.domain.eventsession.EventSessionId;
import io.weba.collector.domain.eventsession.EventSessionRegistry;
import io.weba.collector.domain.session.Session;
import io.weba.collector.domain.session.SessionId;
import io.weba.collector.domain.session.SessionRegistry;

public class CollectorImpl implements Collector {
    private final Client client;
    private final SessionRegistry sessionRegistry;
    private final EventSessionRegistry eventSessionRegistry;
    private final EventAggregatorRegistry eventAggregatorRegistry;

    public CollectorImpl(
            Client client,
            SessionRegistry sessionRegistry,
            EventSessionRegistry eventSessionRegistry,
            EventAggregatorRegistry eventAggregatorRegistry
    ) {
        this.client = client;
        this.sessionRegistry = sessionRegistry;
        this.eventSessionRegistry = eventSessionRegistry;
        this.eventAggregatorRegistry = eventAggregatorRegistry;
    }

    @Override
    public void collect(Event event) throws CollectorException {
        try {
            this.client.startTransaction();
            VisitorIdentity visitorIdentity = new VisitorIdentity(event.payload.getVisitorIdentity().toString());
            Session session = this.sessionRegistry.findActiveSession(event.dates.browserDate, visitorIdentity);

            if (session != null) {
                this.sessionRegistry.renewActiveSession(event.dates.browserDate, visitorIdentity);
                this.eventAggregatorRegistry.update(session.sessionId, event.payload.getVisitorIdentity());
            } else {
                session = new Session(new SessionId(SessionId.generate()), event.dates.browserDate, visitorIdentity);
                this.sessionRegistry.addNewSession(session);
                this.eventAggregatorRegistry.add(new EventAggregator(
                        new EventAggregatorId(EventAggregatorId.generate()),
                        session,
                        event.payload.getVisitorIdentity()
                ));
            }

            this.eventSessionRegistry.addEvent(new EventSession(new EventSessionId(EventSessionId.generate()), session, event));

            this.client.commit();
        } catch (Exception baseException) {
            try {
                this.client.rollback();

                throw new CollectorException(baseException);
            } catch (ClientException e) {
                throw new CollectorException(e);
            }
        }
    }
}

