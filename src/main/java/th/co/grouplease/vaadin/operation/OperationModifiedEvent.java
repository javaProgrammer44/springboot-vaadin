package th.co.grouplease.vaadin.operation;


import th.co.grouplease.model.Operation;

import java.io.Serializable;

public class OperationModifiedEvent implements Serializable {

    private final Operation operation;

    public OperationModifiedEvent(Operation p) {
        this.operation = p;
    }

    public Operation getOperation() {
        return operation;
    }
    
}
