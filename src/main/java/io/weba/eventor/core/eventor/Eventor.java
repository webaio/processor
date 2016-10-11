/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.eventor;

import io.weba.eventor.core.event.Event;
import io.weba.eventor.core.exception.EventorException;

public interface Eventor {
    Event exploitEvent(String rawEvent) throws EventorException;
}
