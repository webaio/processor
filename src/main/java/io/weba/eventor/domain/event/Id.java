package io.weba.eventor.domain.event;

import java.util.UUID;

public class ID {
    public final UUID id;

    public ID(String id) {
        this.id = UUID.fromString(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ID)) return false;

        ID id1 = (ID) o;

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
