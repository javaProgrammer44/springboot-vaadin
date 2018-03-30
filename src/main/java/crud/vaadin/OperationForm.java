package crud.vaadin;

import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import crud.model.Operation;
import crud.repo.OperationRepository;
import org.vaadin.spring.events.EventBus;
import org.vaadin.teemu.switchui.Switch;
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

    TextField name = new MTextField("Name");
    TextField email = new MTextField("Email");
    TextField phoneNumber = new MTextField("Phone");
    DateField birthDay = new DateField("Birthday");
    Switch colleague = new Switch("Colleague");

    OperationForm(OperationRepository r, EventBus.UIEventBus b) {
        super(Operation.class);
        this.repo = r;
        this.eventBus = b;

        // On save & cancel, publish events that other parts of the UI can listen
        setSavedHandler(person -> {
            // persist changes
            repo.save(person);
            // send the event for other parts of the application
            eventBus.publish(this, new OperationModifiedEvent(person));
        });
        setResetHandler(p -> eventBus.publish(this, new OperationModifiedEvent(p)));

        setSizeUndefined();
    }

    @Override
    protected void bind() {
        // DateField in Vaadin 8 uses LocalDate by default, the backend
        // uses plain old java.util.Date, thus we need a converter, using
        // built in helper here
        getBinder()
                .forMemberField(birthDay)
                .withConverter(new LocalDateToDateConverter());
        super.bind();
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new MFormLayout(
                        name,
                        email,
                        phoneNumber,
                        birthDay,
                        colleague
                ).withWidth(""),
                getToolbar()
        ).withWidth("");
    }

}
