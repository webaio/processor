package io.weba.eventor.infrastructure.device;

import io.weba.eventor.domain.device.BaseDetector;
import io.weba.eventor.domain.device.features.Browser;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.features.OperatingSystem;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.HttpLog;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.IOException;

public class UAParserDetector extends BaseDetector {
    private Parser uaParser;

    public UAParserDetector() throws EventorException {
        try {
            uaParser = new Parser();
        } catch (IOException e) {
            throw new EventorException("Cannot initialize UAParser library: ".concat(e.toString()));
        }
    }

    protected Features detectFeatures(HttpLog log) {
        Features features = super.detectFeatures(log);
        String userAgent = getUserAgent(log);

        Client client = uaParser.parse(userAgent);
        features.browser = new Browser(
                client.userAgent.family,
                client.userAgent.major + "." + client.userAgent.minor + "." + client.userAgent.patch
        );

        features.operatingSystem = new OperatingSystem(
                client.os.family,
                client.os.major + "." + client.os.minor + "." + client.os.patch
        );

        return features;
    }

    protected String getUserAgent(HttpLog log) {
        String userAgent = "";
        if (log.request.headers.bag.containsKey("user-agent")) {
            userAgent = log.request.headers.bag.get("user-agent").value;
        }

        return userAgent;
    }
}
