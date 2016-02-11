package io.weba.eventor.domain.event;

import io.weba.eventor.domain.http.Header;

import java.util.Objects;

public class UserAgent {
    public final String userAgent;

    public UserAgent(String userAgent) {
        this.userAgent = Objects.requireNonNull(userAgent);
    }

    public UserAgent(Header header) {
        this.userAgent = Objects.requireNonNull(header.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAgent)) return false;

        UserAgent userAgent1 = (UserAgent) o;

        return userAgent.equals(userAgent1.userAgent);

    }

    @Override
    public int hashCode() {
        return userAgent.hashCode();
    }

    @Override
    public String toString() {
        return userAgent;
    }
}
