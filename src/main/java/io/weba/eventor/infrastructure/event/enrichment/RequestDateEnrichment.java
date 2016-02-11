package io.weba.eventor.infrastructure.event.enrichment;

import io.weba.eventor.domain.event.Dates;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.resolver.HttpContext;
import org.joda.time.DateTime;

import java.util.Date;

public class RequestDateEnrichment implements Enrichment {
    public void enrich(HttpContext httpContext) throws EventorException {
        Date serverDate = new DateTime(httpContext.httpLog.request.date).toDate();
        Date browserDate = serverDate;

        if (httpContext.httpLog.parameters.getRequestDate() != null) {
            browserDate = new DateTime(httpContext.httpLog.parameters.getRequestDate()).toDate();
        }

        httpContext.eventBuilder.dates = new Dates(
                browserDate,
                serverDate
        );
    }
}
