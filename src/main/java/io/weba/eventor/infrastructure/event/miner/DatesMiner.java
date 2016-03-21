package io.weba.eventor.infrastructure.event.miner;

import io.weba.eventor.domain.event.Dates;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;
import org.joda.time.DateTime;

import java.util.Date;

public class DatesMiner implements Miner {
    public void enrich(HttpContext httpContext) throws EventorException {
        Date serverDate = new DateTime(httpContext.entry.request.date).toDate();
        Date browserDate = serverDate;

        if (httpContext.entry.parameters.getRequestDate() != null) {
            browserDate = new DateTime(httpContext.entry.parameters.getRequestDate()).toDate();
        }

        httpContext.eventBuilder.dates = new Dates(
                browserDate,
                serverDate
        );
    }
}
