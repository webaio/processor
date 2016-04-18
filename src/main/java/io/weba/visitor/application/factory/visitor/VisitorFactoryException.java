package io.weba.visitor.application.factory.visitor;

public class VisitorFactoryException extends Exception {
    public VisitorFactoryException(Exception previous) {
        super("Visitor factory exception", previous);
    }
}
