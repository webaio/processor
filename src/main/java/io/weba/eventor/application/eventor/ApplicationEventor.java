package io.weba.eventor.application.eventor;

import io.weba.eventor.domain.eventor.Eventor;
import io.weba.eventor.infrastructure.device.fingerprint.ComponentsGenerator;
import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.event.EventBuilder;
import io.weba.eventor.domain.event.Type;
import io.weba.eventor.domain.event.payload.Factory;
import io.weba.eventor.domain.event.resolver.ContextWrapper;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.device.UAParserDetector;
import io.weba.eventor.infrastructure.event.payload.MapFactoryGuesser;
import io.weba.eventor.infrastructure.event.payload.PageViewFactory;
import io.weba.eventor.infrastructure.event.enrichment.*;
import io.weba.eventor.infrastructure.event.resolver.HttpContext;
import io.weba.eventor.infrastructure.event.resolver.HttpResolver;
import io.weba.eventor.infrastructure.event.utils.UrlProvider;
import io.weba.eventor.infrastructure.event.utils.VisitorIdentityProvider;
import io.weba.eventor.infrastructure.log.GsonReader;
import io.weba.eventor.infrastructure.log.gson.GsonFactory;

import java.util.HashMap;

public class ApplicationEventor implements Eventor {
    private HttpResolver httpResolver;
    private GsonReader jsonLogReader;
    private EventBuilder eventBuilder;

    public ApplicationEventor() throws EventorException {
        setUpResolver();
        eventBuilder = new EventBuilder();
        jsonLogReader = new GsonReader(GsonFactory.create());
    }

    private void setUpResolver() throws EventorException {
        EnrichmentChainBuilder processorEventChainBuilder = new EnrichmentChainBuilder();
        processorEventChainBuilder.addEnrichment(new IdEnrichment());
        processorEventChainBuilder.addEnrichment(new RequestDateEnrichment());
        processorEventChainBuilder.addEnrichment(new EventTypeEnrichment());
        processorEventChainBuilder.addEnrichment(new LocalizationEnrichment());

        UAParserDetector detector = new UAParserDetector();
        detector.setGenerator(new ComponentsGenerator());

        processorEventChainBuilder.addEnrichment(new DeviceEnrichment(detector));

        HashMap<Type, Factory> map = new HashMap<Type, Factory>();
        map.put(Type.PAGE_VIEW, new PageViewFactory(new UrlProvider(), new VisitorIdentityProvider()));

        processorEventChainBuilder.addEnrichment(new PayloadEnrichment(new MapFactoryGuesser(map)));

        httpResolver = new HttpResolver(processorEventChainBuilder.buildChain());
    }

    public Event exploitFromAccessLog(String accessLog) throws EventorException {
        eventBuilder.clean();

        return httpResolver.resolve(new ContextWrapper<HttpContext>(new HttpContext(
                eventBuilder,
                jsonLogReader.read(accessLog))
        ));
    }
}
