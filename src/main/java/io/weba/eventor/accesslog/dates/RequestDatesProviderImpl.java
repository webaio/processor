/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.dates;

import io.weba.eventor.accesslog.AccessLog;
import io.weba.eventor.accesslog.parameters.Input;
import io.weba.eventor.core.exception.DropEventException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestDatesProviderImpl implements RequestDatesProvider {
    private static Logger logger = LoggerFactory.getLogger(RequestDatesProviderImpl.class);
    private Pattern pattern = Pattern.compile(
            "(?<firstVisitDate>\\d+).(?<sessionStartDate>\\d+).(?<sessionEndDate>\\d+).(?<requestClientDate>\\d+)"
    );

    @Override
    public RequestDates provideRequestDates(AccessLog accessLog) throws DropEventException {
        Date requestServerDate = new DateTime((String) accessLog.readAtPath("request.date")).toDate();
        Date requestClientDate = requestServerDate;
        Date sessionStartDate = requestServerDate;
        Date sessionEndDate = new DateTime(requestServerDate.getTime()).plusMinutes(30).toDate();
        Date firstVisitDate = requestServerDate;

        Matcher matcher = pattern.matcher(
                (String) accessLog.parameters.getOrDefault(Input.VISITOR_TIMESTAMPS.name, "")
        );

        if (matcher.find()) {
            firstVisitDate = getDateFromTimestampString(matcher.group("firstVisitDate"), requestServerDate);
            sessionStartDate = getDateFromTimestampString(matcher.group("sessionStartDate"), requestServerDate);
            sessionEndDate = getDateFromTimestampString(matcher.group("sessionEndDate"), requestServerDate);
            requestClientDate = getDateFromTimestampString(matcher.group("requestClientDate"), requestServerDate);
        }

        if (sessionStartDate.compareTo(sessionEndDate) == 1) {
            throw new DropEventException("Session start date should be less than end date.");
        }

        return new RequestDates(
                firstVisitDate,
                sessionStartDate,
                sessionEndDate,
                requestClientDate,
                requestServerDate
        );
    }

    private Date getDateFromTimestampString(String timestamp, Date requestServerDate) {
        Date date = requestServerDate;
        try {
            date = new DateTime((long) Long.parseLong(timestamp)).toDate();
        } catch (Exception ex) {
            logger.info(String.format("Incoming date %s is invalid, %s.", timestamp, ex.getMessage()));
        }

        return date;
    }
}
