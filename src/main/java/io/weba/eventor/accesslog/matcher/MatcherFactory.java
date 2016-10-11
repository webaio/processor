/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.matcher;

import io.weba.eventor.accesslog.dates.RequestDatesProviderImpl;
import io.weba.eventor.accesslog.device.features.*;

import java.io.IOException;
import java.util.LinkedHashSet;

public class MatcherFactory {
    public Matcher create() throws IOException {
        LinkedHashSet<Matcher> matchers = new LinkedHashSet<>();

        matchers.add(new TrackerIdMatcherImpl());
        matchers.add(new RemoteAddrMatcherImpl());
        matchers.add(new ResponseStatusCodeMatcherImpl());
        matchers.add(new RequestIdMatcherImpl());
        matchers.add(new UrlMatcherImpl());
        matchers.add(new EventTypeMatcherImpl());
        matchers.add(new VisitorSessionMatcherImpl(
                new RequestDatesProviderImpl()
        ));
        matchers.add(new UrlMatcherImpl());
        matchers.add(new UserAgentMatcherImpl());
        matchers.add(new LocalizationMatcherImpl());
        matchers.add(new DeviceMatcherImpl(new RecognizerLoopImpl(getRecognizers())));
        matchers.add(new UserLanguageMatcherImpl());
        matchers.add(new DocumentEncodingMatcherImpl());
        matchers.add(new DocumentTitleMatcherImpl());

        return new MatcherLoopImpl(matchers);
    }

    private LinkedHashSet<Recognizer> getRecognizers() throws IOException {
        LinkedHashSet<Recognizer> recognizers = new LinkedHashSet<>();
        recognizers.add(new DeviceDetectorRecognizerFactory().create());
        recognizers.add(new WebTrackerDeviceInfoRecognizerFactory().create());
        recognizers.add(new WebTrackerResolutionsRecognizerImpl());

        return recognizers;
    }
}
