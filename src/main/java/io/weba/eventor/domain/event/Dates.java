package io.weba.eventor.domain.event;

import java.util.Date;

public class Dates {
    public final Date browserDate;
    public final Date serverDate;

    public Dates(Date browserDate, Date serverDate) {
        this.browserDate = browserDate;
        this.serverDate = serverDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dates)) return false;

        Dates dates = (Dates) o;

        if (!browserDate.equals(dates.browserDate)) return false;
        return serverDate.equals(dates.serverDate);

    }

    @Override
    public int hashCode() {
        int result = browserDate.hashCode();
        result = 31 * result + serverDate.hashCode();
        return result;
    }
}
