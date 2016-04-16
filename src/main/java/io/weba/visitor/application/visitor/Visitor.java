package io.weba.visitor.application.visitor;

import io.weba.eventor.domain.event.Event;
import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringFetchingObject;
import io.weba.visitor.domain.action.ActionBuilder;
import io.weba.visitor.domain.action.ActionRegistry;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitBuilder;
import io.weba.visitor.domain.visit.VisitRegistry;
import org.apache.log4j.Logger;

public class Visitor {
    private final VisitRegistry redisVisitRegistry;
    private final VisitRegistry elasticsearchVisitRegistry;
    private final ActionRegistry actionRegistry;
    private static final Logger logger = Logger.getLogger(Visitor.class);

    public Visitor(
            VisitRegistry redisVisitRegistry,
            VisitRegistry elasticsearchVisitRegistry,
            ActionRegistry actionRegistry
    ) {
        this.redisVisitRegistry = redisVisitRegistry;
        this.elasticsearchVisitRegistry = elasticsearchVisitRegistry;
        this.actionRegistry = actionRegistry;
    }

    public void resolveVisit(Event event) throws VisitorException {
        try {
            Visit visit = this.redisVisitRegistry.find(event.payload.getVisitorIdentity().toString());

            if (visit == null) {
                logger.info("New visit");
                this.redisVisitRegistry.add(new VisitBuilder().buildFromEvent(event));
            } else {
                logger.info("Returned visit #id:"+visit.id.toString());
                this.redisVisitRegistry.update(visit, event.dates.browserDate, event.dates.serverDate);
            }

            this.redisVisitRegistry.apply();

            visit = this.redisVisitRegistry.find(event.payload.getVisitorIdentity().toString());

            this.elasticsearchVisitRegistry.add(visit);
            this.actionRegistry.add(new ActionBuilder().buildForVisit(visit, event));
            this.elasticsearchVisitRegistry.apply();

        } catch (ErrorDuringCommitting | ErrorDuringFetchingObject e) {
            throw new VisitorException(e);
        }
    }
}
