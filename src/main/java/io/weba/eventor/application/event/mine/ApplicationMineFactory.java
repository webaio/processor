package io.weba.eventor.application.event.mine;

import fiftyone.mobile.detection.Provider;
import fiftyone.mobile.detection.factories.StreamFactory;
import io.weba.eventor.domain.event.Type;
import io.weba.eventor.domain.event.mine.MineFactory;
import io.weba.eventor.domain.event.mine.Mine;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.device.detector.Degress51EntryDetector;
import io.weba.eventor.infrastructure.device.fingerprint.ComponentsGenerator;
import io.weba.eventor.infrastructure.event.enrichment.*;
import io.weba.eventor.infrastructure.event.mine.LogEntryMine;
import io.weba.eventor.infrastructure.event.payload.MapFactoryGuesser;
import io.weba.eventor.infrastructure.event.payload.PageViewFactory;
import io.weba.eventor.infrastructure.event.utils.UrlProvider;
import io.weba.eventor.infrastructure.event.utils.VisitorIdentityProvider;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class ApplicationMineFactory implements MineFactory {
    @Override
    public Mine create() throws EventorException, IOException {
        EnrichmentChainBuilder processorEventChainBuilder = new EnrichmentChainBuilder();
        processorEventChainBuilder.addEnrichment(new IdEnrichment());
        processorEventChainBuilder.addEnrichment(new DatesEnrichment());
        processorEventChainBuilder.addEnrichment(new EventTypeEnrichment());
        processorEventChainBuilder.addEnrichment(new LocalizationEnrichment());

        URL url = ApplicationMineFactory.class.getClassLoader().getResource("51Degrees.dat");
        Objects.requireNonNull(url);
        Provider provider = new Provider(StreamFactory.create(url.getPath(), false));

        Degress51EntryDetector detector = new Degress51EntryDetector(provider);
        detector.setGenerator(new ComponentsGenerator());

        processorEventChainBuilder.addEnrichment(new DeviceEnrichment(detector));

        HashMap<Type, io.weba.eventor.domain.event.payload.Factory> map = new HashMap<Type, io.weba.eventor.domain.event.payload.Factory>();
        map.put(Type.PAGE_VIEW, new PageViewFactory(new UrlProvider(), new VisitorIdentityProvider()));

        processorEventChainBuilder.addEnrichment(new PayloadEnrichment(new MapFactoryGuesser(map)));

        return new LogEntryMine(processorEventChainBuilder.buildChain());
    }
}
