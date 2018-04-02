package th.co.grouplease.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

import com.vaadin.ui.VerticalLayout;
import th.co.grouplease.repo.OperationAreaRepository;
import th.co.grouplease.repo.OperationRepository;
import org.vaadin.spring.events.EventBus;
import th.co.grouplease.vaadin.operation.OperationForm;
import th.co.grouplease.vaadin.operation.OperationMainPage;
import th.co.grouplease.vaadin.operation.area.OperationAreaForm;
import th.co.grouplease.vaadin.operation.area.OperationAreaMainPage;

@Title("Operation CRUD")
@Theme("valo")
@SpringUI
public class MainUI extends UI {

    private static final long serialVersionUID = 1L;

    OperationRepository repo;
    OperationForm operationForm;
    OperationAreaRepository operationAreaRepository;
    OperationAreaForm operationAreaForm;
    EventBus.UIEventBus eventBus;




    public MainUI(OperationRepository r, OperationForm f,OperationAreaRepository operationAreaRepository, OperationAreaForm operationAreaForm, EventBus.UIEventBus b) {
        this.repo = r;
        this.operationForm = f;
        this.eventBus = b;
        this.operationAreaForm = operationAreaForm;
        this.operationAreaRepository = operationAreaRepository;
    }

    @Override
    protected void init(VaadinRequest request) {
        TabSheet tabsheet = new TabSheet();
        VerticalLayout components = new VerticalLayout();
        components.addComponent(tabsheet);


        setContent(components);

        // Create the first tab
        VerticalLayout tab1 = new VerticalLayout();
        tab1.addComponent(new OperationMainPage(this.repo, this.operationForm, this.eventBus).createOperationPage());
        tabsheet.addTab(tab1, "Operation");



        // Create the first tab
        VerticalLayout tab2 = new VerticalLayout();
        tab2.addComponent(new OperationAreaMainPage(this.operationAreaRepository, this.operationAreaForm, this.eventBus).createOperationPage());
        tabsheet.addTab(tab2, "Operation Area");

    }


}
