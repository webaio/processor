package io.weba.eventor.domain.event.mine;

public class ContextWrapper<T> {
    public final T context;

    public ContextWrapper(T context) {
        this.context = context;
    }
}
