package io.weba.visitor.application.visitor;

class VisitorException extends Exception {
    public VisitorException(Exception previous) {
        super("Visitor exception", previous);
    }
}
