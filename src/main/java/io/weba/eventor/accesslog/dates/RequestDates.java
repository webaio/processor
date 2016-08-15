/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.dates;

import java.util.Date;

public class RequestDates {
    public final Date firstVisitDate;
    public final Date sessionStartDate;
    public final Date sessionEndDate;
    public final Date requestClientDate;
    public final Date requestServerDate;

    public RequestDates(Date firstVisitDate, Date sessionStartDate, Date sessionEndDate, Date requestClientDate, Date requestServerDate) {
        this.firstVisitDate = firstVisitDate;
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
        this.requestClientDate = requestClientDate;
        this.requestServerDate = requestServerDate;
    }
}
