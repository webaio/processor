package io.weba.eventor.domain.event.resolver;

public class ContextWrapper<T> {
    public final T context;

    public ContextWrapper(T context) {
        this.context = context;
    }
}
