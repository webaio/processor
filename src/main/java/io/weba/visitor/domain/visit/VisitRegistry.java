package io.weba.visitor.domain.visit;

import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringFetchingObject;

import java.util.Date;

public interface VisitRegistry {
    void add(Visit visit);

    void update(Visit visit, Date browserUpdatedAt, Date serverUpdatedAt);

    Visit find(String identity) throws ErrorDuringFetchingObject;

    void apply() throws ErrorDuringCommitting;
}
