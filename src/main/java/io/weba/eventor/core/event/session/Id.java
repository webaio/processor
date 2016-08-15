/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.event.session;

import io.weba.eventor.core.exception.EventorException;

import java.util.UUID;

public class Id {
    public final UUID id;

    public Id(String id) throws EventorException {
        try {
            this.id = UUID.fromString(id);
        } catch (Exception e) {
            throw new EventorException(String.format("Cannot create Id from %s.", id), e);
        }
    }
}
