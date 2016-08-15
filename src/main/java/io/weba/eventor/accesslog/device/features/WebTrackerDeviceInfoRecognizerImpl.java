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
import io.weba.eventor.accesslog.parameters.Input;
import io.weba.eventor.core.exception.DropEventException;

import java.util.List;
import java.util.Map;

public class WebTrackerDeviceInfoRecognizerImpl implements Recognizer {
    private List<String> featuresList;

    public WebTrackerDeviceInfoRecognizerImpl(List<String> featuresList) {
        this.featuresList = featuresList;
    }

    @Override
    public void recognize(AccessLog accessLog, Map<String, Object> features) throws DropEventException {
        String deviceString = (String) accessLog.parameters.getOrDefault(Input.DEVICE_INFO.name, "0");
        if(deviceString.isEmpty()) {
            deviceString = "0";
        }
        if (!deviceString.startsWith("0x")) {
            deviceString = "0x" + deviceString;
        }
        deviceString = Long.toBinaryString(Long.decode(deviceString)).substring(1);

        for (int i = 0; i < featuresList.size(); i++) {
            String featureName = featuresList.get(i);
            if (i >= deviceString.length()) {
                features.put(
                        featureName,
                        false
                );
                continue;
            }

            features.put(
                    featureName,
                    Boolean.parseBoolean(String.valueOf(deviceString.charAt(i)))
            );
        }
    }
}
