package io.weba.eventor.application.eventor;

import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.event.EventBuilder;
import io.weba.eventor.domain.event.mine.ContextWrapper;
import io.weba.eventor.domain.event.mine.Mine;
import io.weba.eventor.domain.eventor.Eventor;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.http.voter.Voter;
import io.weba.eventor.domain.log.Entry;
import io.weba.eventor.domain.log.reader.Reader;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public class ApplicationEventor implements Eventor {
    private Mine mine;
    private Reader reader;
    private EventBuilder eventBuilder;
    private Voter voter;

    public ApplicationEventor(Mine mine, Reader reader, EventBuilder eventBuilder, Voter voter) {
        this.mine = mine;
        this.reader = reader;
        this.eventBuilder = eventBuilder;
        this.voter = voter;
    }

    public Event exploitFromLog(String log) throws EventorException {
        try {
            return doExploitFromLog(log);
        } catch (NullPointerException e) {
            throw new EventorException("NullPointerException: ", e);
        }
    }

    private Event doExploitFromLog(String log) throws EventorException {
        eventBuilder.clean();

        Entry entry = reader.read(log);
        voter.vote(entry);

        return mine.exploit(new ContextWrapper<HttpContext>(new HttpContext(
                eventBuilder,
                entry)
        ));
    }


}
