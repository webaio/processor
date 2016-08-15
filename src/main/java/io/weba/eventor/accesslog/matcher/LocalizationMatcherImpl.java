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
import io.weba.eventor.core.enriched.localization.Localization;
import io.weba.eventor.core.exception.DropEventException;
import io.weba.eventor.core.exception.EventorException;

public class LocalizationMatcherImpl implements Matcher {
    @Override
    public void match(AccessLog accessLog) throws EventorException {
        Localization localization = new Localization(
                (String) accessLog.readAtPath("localization.country"),
                (String) accessLog.readAtPath("localization.region"),
                (String) accessLog.readAtPath("localization.city"),
                (String) accessLog.readAtPath("localization.postal_code"),
                (String) accessLog.readAtPath("localization.continent"),
                parseCoordinates("localization.latitude", accessLog),
                parseCoordinates("localization.longitude", accessLog)
        );

        accessLog.builder.enriched.put("localization", localization);
    }

    private double parseCoordinates(String coordinate, AccessLog accessLog) throws DropEventException {
        Double returnCoordinate = 0.00;
        try {
            returnCoordinate = Double.parseDouble((String) accessLog.readAtPath(coordinate));
        } catch (NullPointerException | IllegalArgumentException exception) {
        }

        return returnCoordinate;
    }
}
