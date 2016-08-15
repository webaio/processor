/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.event.visitor;

import java.util.Date;

public class Visitor {
    public final Id id;
    public final Date firstVisitDate;

    public Visitor(Id id, Date firstVisitDate) {
        this.id = id;
        this.firstVisitDate = firstVisitDate;
    }
}
