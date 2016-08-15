/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.event;

import java.util.UUID;

public class Id {
    public final UUID id;

    public Id(String id) {
        this.id = UUID.fromString(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Id)) return false;

        Id id1 = (Id) o;

        return id.equals(id1.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
