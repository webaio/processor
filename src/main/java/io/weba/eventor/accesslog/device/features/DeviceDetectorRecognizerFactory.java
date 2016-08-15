/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.device.features;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

public class DeviceDetectorRecognizerFactory implements Factory{
    public DeviceDetectorRecognizerImpl create() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("eventor.recognizers.properties"));

        return new DeviceDetectorRecognizerImpl(Arrays.asList(
                ((String) properties.getProperty("eventor.deviceDetectorFeaturesList")).split(Pattern.quote(","))
        ));
    }
}
