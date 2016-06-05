package io.weba.eventor.application.eventor;

import io.weba.eventor.application.event.mine.ApplicationMineFactory;
import io.weba.eventor.application.http.voter.VoterFactory;
import io.weba.eventor.domain.event.EventBuilder;
import io.weba.eventor.domain.eventor.Eventor;
import io.weba.eventor.domain.eventor.EventorFactory;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.log.gson.GsonFactory;
import io.weba.eventor.infrastructure.log.gson.reader.GsonReader;

import java.io.IOException;

public class ApplicationEventorFactory implements EventorFactory {
    private ApplicationMineFactory mineFactory = new ApplicationMineFactory();
    private VoterFactory voterFactory = new VoterFactory();

    @Override
    public Eventor create(String deviceDetectorDbPath) throws IOException, EventorException {
        return new ApplicationEventor(
                mineFactory.create(deviceDetectorDbPath),
                new GsonReader(GsonFactory.create()),
                new EventBuilder(),
                voterFactory.create()
        );
    }
}
