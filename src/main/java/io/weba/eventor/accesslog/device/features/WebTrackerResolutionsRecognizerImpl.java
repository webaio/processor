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
import io.weba.eventor.accesslog.parameters.Output;
import io.weba.eventor.core.exception.DropEventException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebTrackerResolutionsRecognizerImpl implements Recognizer {
    private Pattern pattern = Pattern.compile("(?<width>\\d+)x(?<height>\\d+)");

    @Override
    public void recognize(AccessLog accessLog, Map<String, Object> features) throws DropEventException {
        recognizeWidthAndHeight(accessLog, features);
        recognizeAvailableWidthAndHeight(accessLog, features);
        recognizeColorDepth(accessLog, features);
    }

    private void recognizeColorDepth(AccessLog accessLog, Map<String, Object> features) {
        features.put(
                Output.COLOR_DEPTH.name,
                Integer.parseInt((String)accessLog.parameters.getOrDefault(Input.COLOR_DEPTH.name, "8"))
        );
    }

    private void recognizeAvailableWidthAndHeight(AccessLog accessLog, Map<String, Object> features) {
        WidthAndHeight availableWidthAndHeight = extractWidthAndHeight(
                (String) accessLog.parameters.getOrDefault(Input.AVAILABLE_WIDTH_AND_HEIGHT.name, "")
        );

        features.put(Output.AVAILABLE_WIDTH.name, availableWidthAndHeight.width);
        features.put(Output.AVAILABLE_HEIGHT.name, availableWidthAndHeight.height);
    }

    private void recognizeWidthAndHeight(AccessLog accessLog, Map<String, Object> features) {
        WidthAndHeight widthAndHeight = extractWidthAndHeight(
                (String) accessLog.parameters.getOrDefault(Input.WIDTH_AND_HEIGHT.name, "")
        );

        features.put(Output.WIDTH.name, widthAndHeight.width);
        features.put(Output.HEIGHT.name, widthAndHeight.height);
    }

    private WidthAndHeight extractWidthAndHeight(String widthAndHeight) {
        Matcher matcher = pattern.matcher(widthAndHeight);

        int width = -1;
        int height = -1;
        if (matcher.find()) {
            width = Integer.parseInt(matcher.group("width"));
            height = Integer.parseInt(matcher.group("height"));
        }

        return new WidthAndHeight(width, height);
    }

    private class WidthAndHeight {
        public final int width;
        public final int height;

        public WidthAndHeight(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
