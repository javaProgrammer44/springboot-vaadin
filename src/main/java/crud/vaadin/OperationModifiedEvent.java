package crud.vaadin;


import crud.model.Operation;

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
