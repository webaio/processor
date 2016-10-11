/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.event.visitor;

import io.weba.eventor.core.exception.EventorException;

import java.util.UUID;

public class Id {
    public final UUID visitorIdentity;

    public Id(String visitorIdentity) throws EventorException {
        this.visitorIdentity = UUID.fromString(visitorIdentity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Id)) return false;

        Id that = (Id) o;

        return visitorIdentity.equals(that.visitorIdentity);

    }

    @Override
    public int hashCode() {
        return visitorIdentity.hashCode();
    }

    @Override
    public String toString() {
        return visitorIdentity.toString();
    }
}
