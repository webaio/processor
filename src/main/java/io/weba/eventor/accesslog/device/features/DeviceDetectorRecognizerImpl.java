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

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DeviceDetectorRecognizerImpl implements Recognizer {
    private List<String> featuresList;

    public DeviceDetectorRecognizerImpl(List<String> featuresList) {
        this.featuresList = featuresList;
    }

    @Override
    public void recognize(AccessLog accessLog, Map<String, Object> features) throws DropEventException {
        String[] deviceStrings = ((String) accessLog
                .readAtPath("device"))
                .split(Pattern.quote(","));

        for (int i = 0; i < featuresList.size(); i++) {
            String featureName = featuresList.get(i);
            if (deviceStrings.length < i || deviceStrings[i] == null) {
                features.put(featureName, null);
                continue;
            }

            if (featureName.contains("Is")) {
                features.put(featureName, Boolean.parseBoolean(deviceStrings[i]));
                continue;
            }

            features.put(
                    featureName,
                    deviceStrings[i].equals("NA") ? null : deviceStrings[i]
            );
        }
    }
}
