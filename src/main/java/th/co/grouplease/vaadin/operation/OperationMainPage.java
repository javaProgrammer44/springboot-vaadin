package th.co.grouplease.vaadin.operation;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import th.co.grouplease.model.Operation;
import th.co.grouplease.repo.OperationRepository;

import java.util.ArrayList;
import java.util.List;

public class OperationMainPage extends VerticalLayout {


    private static final long serialVersionUID = 1L;

    OperationRepository repo;
    OperationForm operationForm;
    EventBus.UIEventBus eventBus;

    private MGrid<Operation> list = new MGrid<>(Operation.class)
            .withProperties("operationCode", "operationDescription", "price", "defaultDltCharge", "defaultWage")
            .withFullWidth();

    private MTextField filterByName = new MTextField()
            .withPlaceholder("Filter by name");
    private Button addNew = new MButton(VaadinIcons.PLUS, this::add);
    private Button edit = new MButton(VaadinIcons.PENCIL, this::edit);
    private Button delete = new ConfirmButton(VaadinIcons.TRASH,
            "Are you sure you want to delete the entry?", this::remove);


    public OperationMainPage(OperationRepository r, OperationForm f, EventBus.UIEventBus b) {

        this.repo = r;
        this.operationForm = f;
        this.eventBus = b;

        listEntities();

        list.asSingleSelect().addValueChangeListener(e -> adjustActionButtonState());
        filterByName.addValueChangeListener(e -> {
            listEntities(e.getValue());
        });

        // Listen to change events emitted by PersonForm see onEvent method
        eventBus.subscribe(this);
    }

    public Component createOperationPage() {
        return new MVerticalLayout(
                new MHorizontalLayout(filterByName, addNew, edit, delete),
                list
        ).expand(list);

    }

    public void add(Button.ClickEvent clickEvent) {
        edit(new Operation());
    }

    public void edit(Button.ClickEvent e) {
        edit(list.asSingleSelect().getValue());
    }

    public void remove() {
        repo.delete(list.asSingleSelect().getValue());
        list.deselectAll();
        listEntities();
    }

    protected void edit(final Operation operation) {
        operationForm.setEntity(operation);
        operationForm.openInModalPopup();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    public void onPersonModified(OperationModifiedEvent event) {
        listEntities();
        operationForm.closePopup();
    }


    private void listEntities() {
        listEntities(filterByName.getValue());
    }

    final int PAGESIZE = 45;

    private void listEntities(String nameFilter) {
        // A dead simple in memory listing would be:
        // list.setRows(repo.findAll());

        // But we want to support filtering, first add the % marks for SQL name query
        String likeFilter = "%" + nameFilter + "%";

        // If there is a moderate amount of rows, one can just fetch everything
        //list.setRows(repo.findByNameLikeIgnoreCase(likeFilter));

        // Lazy binding for better optimized connection from the Vaadin Grid to
        // Spring Repository. This approach uses less memory and database
        // resources. Use this approach if you expect you'll have lots of data
        // in your table. There are simpler APIs if you don't need sorting.
        list.setDataProvider(
                // lazy entity fetching strategy, due to a design flaw in DataProvider API,
                // this is bit tricky with Spring Data's Pageable abstration as requests
                // by Grid may be on two pages, see https://github.com/vaadin/framework/issues/8982
                // TODO see if this could be simplified in Viritin MGrid
                (sortOrder, offset, limit) -> {
                    final int pageSize = limit;
                    final int startPage = (int) Math.floor((double) offset / pageSize);
                    final int endPage = (int) Math.floor((double) (offset + pageSize - 1) / pageSize);
                    final Sort.Direction sortDirection = sortOrder.isEmpty() || sortOrder.get(0).getDirection() == SortDirection.ASCENDING ? Sort.Direction.ASC : Sort.Direction.DESC;
                    // fall back to id as "natural order"
                    final String sortProperty = sortOrder.isEmpty() ? "id" : sortOrder.get(0).getSorted();
                    if (startPage != endPage) {
                        List<Operation> page0 = repo.findByOperationCodeLikeIgnoreCase(likeFilter, PageRequest.of(startPage, pageSize, sortDirection, sortProperty));
                        page0 = page0.subList(offset % pageSize, page0.size());
                        List<Operation> page1 = repo.findByOperationCodeLikeIgnoreCase(likeFilter, PageRequest.of(endPage, pageSize, sortDirection, sortProperty));
                        page1 = page1.subList(0, limit - page0.size());
                        List<Operation> result = new ArrayList<>(page0);
                        result.addAll(page1);
                        return result.stream();
                    } else {
                        return repo.findByOperationCodeLikeIgnoreCase(likeFilter, PageRequest.of(startPage, pageSize, sortDirection, sortProperty)).stream();
                    }
                },
                // count fetching strategy
                () -> (int) repo.countByOperationCodeLikeIgnoreCase(likeFilter)
        );
        adjustActionButtonState();

    }


    protected void adjustActionButtonState() {
        boolean hasSelection = !list.getSelectedItems().isEmpty();
        edit.setEnabled(hasSelection);
        delete.setEnabled(hasSelection);
    }

}
