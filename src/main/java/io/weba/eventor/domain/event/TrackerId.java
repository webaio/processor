package io.weba.eventor.domain.event;

import io.weba.eventor.domain.exception.EventorException;

import java.util.UUID;

public class TrackerId {

    public final UUID trackerId;

    public TrackerId(String trackerId) throws EventorException {
        this.trackerId = UUID.fromString(trackerId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackerId)) return false;

        TrackerId trackerId1 = (TrackerId) o;

        return trackerId.equals(trackerId1.trackerId);

    }

    @Override
    public int hashCode() {
        return trackerId.hashCode();
    }

    @Override
    public String toString() {
        return trackerId.toString();
    }


}
