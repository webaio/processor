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
import io.weba.eventor.accesslog.parameters.Input;
import io.weba.eventor.accesslog.parameters.Output;
import io.weba.eventor.core.exception.EventorException;

public class UserLanguageMatcherImpl implements Matcher {
    @Override
    public void match(AccessLog accessLog) throws EventorException {
        accessLog.builder.payload.put(
                Output.USER_LANGUAGE.name,
                (String)accessLog.parameters.getOrDefault(Input.LANGUAGE.name, null)
        );
    }
}
