/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.matcher;

import io.weba.eventor.accesslog.AccessLog;
import io.weba.eventor.accesslog.dates.RequestDates;
import io.weba.eventor.accesslog.dates.RequestDatesProvider;
import io.weba.eventor.accesslog.parameters.Input;
import io.weba.eventor.core.event.session.Session;
import io.weba.eventor.core.event.visitor.Visitor;
import io.weba.eventor.core.exception.EventorException;

import java.util.UUID;

public class VisitorSessionMatcherImpl implements Matcher {
    private RequestDatesProvider requestDatesProvider;

    public VisitorSessionMatcherImpl(RequestDatesProvider requestDatesProvider) {
        this.requestDatesProvider = requestDatesProvider;
    }

    @Override
    public void match(AccessLog accessLog) throws EventorException {
        RequestDates requestDates = requestDatesProvider.provideRequestDates(accessLog);

        accessLog.builder.visitor = new Visitor(
                new io.weba.eventor.core.event.visitor.Id(getIdentity(Input.VISITOR_IDENTITY, accessLog)),
                requestDates.firstVisitDate
        );

        accessLog.builder.session = new Session(
                new io.weba.eventor.core.event.session.Id(getIdentity(Input.SESSION_IDENTITY, accessLog)),
                requestDates.sessionStartDate,
                requestDates.sessionEndDate
        );

        accessLog.builder.dates = new io.weba.eventor.core.event.Dates(
                requestDates.requestClientDate,
                requestDates.requestServerDate
        );
    }

    private String getIdentity(Input input, AccessLog accessLog) {
        return (String) accessLog.parameters.getOrDefault(
                input.name,
                UUID.randomUUID().toString()
        );
    }
}
