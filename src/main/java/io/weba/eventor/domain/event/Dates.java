package io.weba.eventor.domain.event;

import java.util.Date;
import java.util.Objects;

public class Dates {
    public final Date browserDate;
    public final Date serverDate;

    public Dates(Date browserDate, Date serverDate) {
        this.browserDate = Objects.requireNonNull(browserDate);
        this.serverDate = Objects.requireNonNull(serverDate);
    }
}
