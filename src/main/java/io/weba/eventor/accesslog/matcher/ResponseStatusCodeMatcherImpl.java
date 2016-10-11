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
import io.weba.eventor.core.exception.DropEventException;
import io.weba.eventor.core.exception.EventorException;

public class ResponseStatusCodeMatcherImpl implements Matcher {
    @Override
    public void match(AccessLog accessLog) throws EventorException {
        Integer status = Integer.parseInt((String) accessLog.readAtPath("response.status"));

        if (!status.equals(204)) {
            throw new DropEventException(
                    "Event will be dropped because of invalid request status code (only 204 is allowed)."
            );
        }
    }
}
