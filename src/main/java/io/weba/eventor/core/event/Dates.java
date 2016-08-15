/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.event;

import java.util.Date;
import java.util.Objects;

public class Dates {
    public final Date client;
    public final Date server;

    public Dates(Date client, Date server) {
        this.client = Objects.requireNonNull(client);
        this.server = Objects.requireNonNull(server);
    }
}
