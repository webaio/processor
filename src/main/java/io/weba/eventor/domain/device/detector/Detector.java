package io.weba.eventor.domain.device.detector;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.Entry;

public interface Detector {
    Device detect(Entry log) throws EventorException;
}
