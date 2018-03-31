package th.co.grouplease.vaadin;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import th.co.grouplease.model.Operation;
import th.co.grouplease.repo.OperationRepository;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.DoubleField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScope
@SpringComponent
public class OperationForm extends AbstractForm<Operation> {

    private static final long serialVersionUID = 1L;

    EventBus.UIEventBus eventBus;
    OperationRepository repo;

    TextField operationCode = new MTextField("Operation Code");
    TextField operationDescription = new MTextField("Operation Description");
    DoubleField price = new DoubleField("price");
    DoubleField defaultDltCharge = new DoubleField("defaultDltCharge");
    DoubleField defaultWage = new DoubleField("defaultWage");

    OperationForm(OperationRepository r, EventBus.UIEventBus b) {
        super(Operation.class);

        this.repo = r;
        this.eventBus = b;

        // On save & cancel, publish events that other parts of the UI can listen
        setSavedHandler(operation -> {
            // persist changes
            repo.save(operation);
            // send the event for other parts of the application
            eventBus.publish(this, new OperationModifiedEvent(operation));
            this.closePopup();
        });
        setResetHandler(p -> eventBus.publish(this, new OperationModifiedEvent(p)));

        setSizeUndefined();
    }

    @Override
    protected void bind() {
        super.bind();
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new MFormLayout(
                        operationCode,
                        operationDescription,
                        price,
                        defaultDltCharge,
                        defaultWage
                ).withWidth(""),
                getToolbar()
        ).withWidth("");
    }

}
