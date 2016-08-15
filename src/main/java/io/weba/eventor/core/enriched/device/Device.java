/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.enriched.device;

import io.weba.eventor.core.enriched.device.fingerprint.Fingerprint;

import java.util.Map;

public class Device {
    public final Fingerprint fingerprint;
    public final Map<String, Object> features;

    public Device(Fingerprint fingerprint, Map<String, Object> features) {
        this.fingerprint = fingerprint;
        this.features = features;
    }
}
