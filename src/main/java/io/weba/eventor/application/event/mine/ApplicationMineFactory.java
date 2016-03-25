package io.weba.eventor.application.event.mine;

import fiftyone.mobile.detection.Provider;
import fiftyone.mobile.detection.factories.StreamFactory;
import io.weba.eventor.domain.event.Type;
import io.weba.eventor.domain.event.mine.Mine;
import io.weba.eventor.domain.event.mine.MineFactory;
import io.weba.eventor.domain.event.payload.PayloadFactory;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.device.detector.Degress51Detector;
import io.weba.eventor.infrastructure.device.fingerprint.ComponentsGenerator;
import io.weba.eventor.infrastructure.event.mine.MineWithMiners;
import io.weba.eventor.infrastructure.event.miner.*;
import io.weba.eventor.infrastructure.event.payload.MapFactoryGuesser;
import io.weba.eventor.infrastructure.event.payload.PageViewPayloadFactory;
import io.weba.eventor.infrastructure.event.utils.UrlProvider;
import io.weba.eventor.infrastructure.event.utils.VisitorIdentityProvider;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class ApplicationMineFactory implements MineFactory {
    @Override
    public Mine create() throws EventorException {
        MinersChainBuilder processorEventChainBuilder = new MinersChainBuilder();
        processorEventChainBuilder.addMiner(new IdMiner());
        processorEventChainBuilder.addMiner(new DatesMiner());
        processorEventChainBuilder.addMiner(new EventTypeMiner());
        processorEventChainBuilder.addMiner(new LocalizationMiner());
        processorEventChainBuilder.addMiner(new DeviceMiner(prepareDeviceDetector()));
        processorEventChainBuilder.addMiner(preparePayloadMiner());

        return new MineWithMiners(processorEventChainBuilder.buildChain());
    }

    private Degress51Detector prepareDeviceDetector() throws EventorException {
        URL url = ApplicationMineFactory.class.getClassLoader().getResource("51Degrees.dat");
        if(url == null) {
            throw new EventorException("Cannot find 51Degrees.dat in resources.");
        }

        Provider provider = null;
        try {
            provider = new Provider(StreamFactory.create(url.getPath(), false));
        } catch (IOException e) {
            throw new EventorException("DeviceDetector creating stream resource exception:", e);
        }

        Degress51Detector detector = new Degress51Detector(provider);
        detector.setGenerator(new ComponentsGenerator());

        return detector;
    }

    private PayloadMiner preparePayloadMiner() {
        HashMap<Type, PayloadFactory> map = new HashMap<Type, PayloadFactory>();
        map.put(Type.PAGE_VIEW, new PageViewPayloadFactory(new UrlProvider(), new VisitorIdentityProvider()));

        return new PayloadMiner(new MapFactoryGuesser(map));
    }
}
