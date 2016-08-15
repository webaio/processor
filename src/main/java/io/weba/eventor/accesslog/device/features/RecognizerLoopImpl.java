/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.device.features;

import io.weba.eventor.accesslog.AccessLog;
import io.weba.eventor.core.exception.DropEventException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

public class RecognizerLoopImpl implements Recognizer {
    private static Logger logger = LoggerFactory.getLogger(RecognizerLoopImpl.class);
    private final Set<Recognizer> recognizers;

    public RecognizerLoopImpl(Set<Recognizer> recognizers) {
        this.recognizers = recognizers;
    }

    @Override
    public void recognize(AccessLog accessLog, Map<String, Object> features) throws DropEventException {
        for (Recognizer recognizer : recognizers) {
            try {
                recognizer.recognize(accessLog, features);
            } catch (Exception ex) {
                logger.info(String.format("Cannot recognize device features from %s recognizer: %s.", recognizer.getClass().getName(), ex.getMessage()));
            }
        }
    }
}
