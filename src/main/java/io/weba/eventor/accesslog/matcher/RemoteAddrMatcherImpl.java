/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.matcher;

import com.google.common.net.InetAddresses;
import io.weba.eventor.accesslog.AccessLog;
import io.weba.eventor.accesslog.parameters.Output;
import io.weba.eventor.core.exception.DropEventException;
import io.weba.eventor.core.exception.EventorException;

public class RemoteAddrMatcherImpl implements Matcher {
    @Override
    public void match(AccessLog accessLog) throws EventorException {
        String remoteAddr = (String)accessLog.readAtPath("request.x_forwarded_for");
        if (!InetAddresses.isInetAddress(remoteAddr)) {
            remoteAddr = (String)accessLog.readAtPath("request.remote_addr");
        }

        if (!InetAddresses.isInetAddress(remoteAddr)) {
            throw new DropEventException(String.format("Invalid remote address (IP) %s.", remoteAddr));
        }

        accessLog.builder.payload.put(Output.REMOTE_ADDR.name, remoteAddr);
    }
}
