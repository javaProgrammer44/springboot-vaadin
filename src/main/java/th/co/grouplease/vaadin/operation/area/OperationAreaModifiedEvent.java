package th.co.grouplease.vaadin.operation.area;


import th.co.grouplease.model.OperationArea;

import java.io.Serializable;

public class OperationAreaModifiedEvent implements Serializable {

    private final OperationArea operation;

    public OperationAreaModifiedEvent(OperationArea p) {
        this.operation = p;
    }

    public OperationArea getOperation() {
        return operation;
    }

}
