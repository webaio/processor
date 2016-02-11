package io.weba.eventor.domain.event;

import io.weba.eventor.domain.exception.EventorException;

import java.util.UUID;

public class VisitorIdentity {

    public final UUID visitorIdentity;
    public static final String IDENTITY_PATTERN = "visitor_identity=([a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})";

    public VisitorIdentity(String visitorIdentity) throws EventorException {
        this.visitorIdentity = UUID.fromString(visitorIdentity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitorIdentity)) return false;

        VisitorIdentity that = (VisitorIdentity) o;

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
