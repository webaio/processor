package io.weba.eventor.infrastructure.log.gson.reader;

import com.google.gson.Gson;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.Entry;
import io.weba.eventor.domain.log.reader.Reader;

public class GsonReader implements Reader {
    private Gson gson;

    public GsonReader(Gson gson) {
        this.gson = gson;
    }

    public Entry read(String log) throws EventorException {
        return gson.fromJson(log, Entry.class);
    }
}
