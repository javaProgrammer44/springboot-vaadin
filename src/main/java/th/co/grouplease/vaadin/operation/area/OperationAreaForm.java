package th.co.grouplease.vaadin.operation.area;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.DoubleField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import th.co.grouplease.model.OperationArea;
import th.co.grouplease.repo.OperationAreaRepository;

@UIScope
@SpringComponent
public class OperationAreaForm extends AbstractForm<OperationArea> {

    private static final long serialVersionUID = 1L;

    EventBus.UIEventBus eventBus;
    OperationAreaRepository repo;

    TextField provinceArea = new MTextField("Province Area");
    DoubleField dltCharge = new DoubleField("DLT Charge");
    DoubleField wage = new DoubleField("Wage");


    OperationAreaForm(OperationAreaRepository r, EventBus.UIEventBus b) {
        super(OperationArea.class);

        this.repo = r;
        this.eventBus = b;

        // On save & cancel, publish events that other parts of the UI can listen
        setSavedHandler(operation -> {
            // persist changes
            repo.save(operation);
            // send the event for other parts of the application
            eventBus.publish(this, new OperationAreaModifiedEvent(operation));
            this.closePopup();
        });
        setResetHandler(p -> eventBus.publish(this, new OperationAreaModifiedEvent(p)));

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
                        provinceArea,
                        dltCharge,
                        wage
                ).withWidth(""),
                getToolbar()
        ).withWidth("");
    }

}
