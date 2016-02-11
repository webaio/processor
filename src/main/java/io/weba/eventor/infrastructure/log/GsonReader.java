package io.weba.eventor.infrastructure.log;

import com.google.gson.Gson;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.HttpLog;
import io.weba.eventor.domain.log.Reader;

public class GsonReader implements Reader {
    private Gson gson;

    public GsonReader(Gson gson) {
        this.gson = gson;
    }

    public HttpLog read(String log) throws EventorException {
        return gson.fromJson(log, HttpLog.class);
    }
}
