package io.weba.eventor.domain.device;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.HttpLog;

public interface Detector {
    Device detect(HttpLog log) throws EventorException;
}
