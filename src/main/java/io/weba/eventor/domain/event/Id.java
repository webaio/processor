package io.weba.eventor.domain.event;

import io.weba.eventor.domain.exception.EventorException;

import java.util.UUID;

public class Id {
    public final UUID id;

    public Id(String id) throws EventorException {
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
