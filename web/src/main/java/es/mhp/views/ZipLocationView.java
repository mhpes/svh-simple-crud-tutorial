package es.mhp.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import es.mhp.browser.IBrowser;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static es.mhp.browser.utils.FormBrowserUtils.EDIT_MODE;
import static es.mhp.browser.utils.FormBrowserUtils.NEW_MODE;

/*
 * Created by Edu on 23/02/2016.
*/
@SpringView(name = ZipLocationView.VIEW_NAME)
public class ZipLocationView extends AbstractView<ZipLocationDTO> {
    public static final String VIEW_NAME = "ZipLocation";
    public static final String ZIPCODE = "Zipcode";
    public static final String CITY = "City";
    public static final String STATE = "State";

    private VerticalLayout zipLayout;
    private VerticalLayout zipTable;

    @Autowired
    private IZipLocationService iZipLocationService;

    public ZipLocationView(){
        setSizeFull();
        zipLayout = new VerticalLayout();
        zipTable = new VerticalLayout();
        this.addStyleName("zip-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        createMainLayout();
    }

    @Override
    void addComponentsToView() {

    }

    //    @Override
    protected void createMainLayout() {
        setTableSyle(zipLayout);
        createFilter();
        zipLayout.addComponent(zipTable);
        this.addComponent(zipLayout);
    }

//    @Override
    protected Layout createForm(ZipLocationDTO zipLocationDTO, String mode) {
        FormLayout form = new FormLayout();
        setFormStyle(form);

        PropertysetItem item = new PropertysetItem();
        bindForm(zipLocationDTO, form, item, mode);

        return form;
    }

    private Button createDeleteButton(ZipLocationDTO zipLocationDTO){
        final Button deleteButton = new Button("Delete entry", FontAwesome.TRASH_O);

        deleteButton.addClickListener((Button.ClickListener) event ->
                iZipLocationService.delete(zipLocationDTO));

        return deleteButton;
    }

    private void setTableSyle(VerticalLayout layout) {
        layout.setSizeFull();
        layout.setMargin(true);
    }

    private void createFilter() {
        zipLayout.removeAllComponents();
        TextField filter = new TextField();
        zipLayout.addComponent(filter);

        filter.setInputPrompt("Filter Zip...");
        fillZipTable(iZipLocationService.findAllZipLocations());
        filter.addTextChangeListener(e ->
                fillZipTable(iZipLocationService.findAnyZipLocations(e.getText())));
    }

    private void fillZipTable(Set<ZipLocationDTO> zipLocationDTOs) {
        zipTable.removeAllComponents();

        BeanItemContainer<ZipLocationDTO> zipLocationDTOBeanItemContainer = new BeanItemContainer<>(ZipLocationDTO.class, zipLocationDTOs);
        Grid grid = new Grid(zipLocationDTOBeanItemContainer);
        grid.setColumnOrder("city", "state");

        grid.setWidth("60%");
        VerticalLayout formContainer = createZipForm(zipLocationDTOBeanItemContainer, grid);

        zipTable.addComponent(grid);
        zipTable.addComponent(formContainer);
        zipTable.setExpandRatio(grid, 1);

        setNewZip(zipTable);
    }

    private VerticalLayout createZipForm(BeanItemContainer<ZipLocationDTO> zipLocationDTOBeanItemContainer, Grid grid) {
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<ZipLocationDTO> zipLocationDTOBeanItem = zipLocationDTOBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(zipLocationDTOBeanItem.getBean(), EDIT_MODE));
            }
        });

        return formContainer;
    }

    public void setNewZip(VerticalLayout verticalLayout) {
        VerticalLayout createNewZipLayout = new VerticalLayout();

        Button createButton = new Button("New Zip");

        createButton.addClickListener((Button.ClickListener) event -> {
            createNewZipLayout.removeAllComponents();
            createNewZipLayout.addComponent(
                    createForm(new ZipLocationDTO(), NEW_MODE)
            );
        });

        verticalLayout.addComponent(createButton);
        verticalLayout.addComponent(createNewZipLayout);
    }

    private void setNewForm(PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        form.addComponent(binder.buildAndBind(ZIPCODE));
        form.addComponent(binder.buildAndBind(CITY));
        form.addComponent(binder.buildAndBind(STATE));

        form.addComponent(createCancelButton(form, binder));
        form.addComponent(createSaveButton(binder));
    }

    private void setEditForm(ZipLocationDTO zipLocationDTO, PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(zipLocationDTO, item);

        binder.buildAndBind(ZIPCODE);
        form.addComponent(binder.buildAndBind(CITY));
        form.addComponent(binder.buildAndBind(STATE));

        form.addComponent(createDeleteButton(zipLocationDTO));
        form.addComponent(createSaveButton(binder));
    }

    private void setItemPropertyEdit(PropertysetItem item) {
        item.addItemProperty(ZIPCODE, new ObjectProperty<>(""));
        item.addItemProperty(CITY, new ObjectProperty<>(""));
        item.addItemProperty(STATE, new ObjectProperty<>(""));
    }

    private void setItemPropertyEdit(ZipLocationDTO zipLocationDTO, PropertysetItem item) {
        item.addItemProperty(ZIPCODE, new ObjectProperty<>(zipLocationDTO.getZipCodeId()));
        item.addItemProperty(CITY, new ObjectProperty<>(zipLocationDTO.getCity()));
        item.addItemProperty(STATE, new ObjectProperty<>(zipLocationDTO.getState()));
    }

    private Component createCancelButton(FormLayout form, FieldGroup binder) {
        Button cancelButton = new Button("Cancel", FontAwesome.TRASH_O);
        binder.discard();

        cancelButton.addClickListener((Button.ClickListener) event ->
                form.removeAllComponents()
        );

        return cancelButton;
    }

    private Button createSaveButton(FieldGroup zipFieldGroup) {
        final Button saveButton = new Button("Save", FontAwesome.SAVE);

        saveButton.addClickListener((Button.ClickListener) event ->
                trySaveZip(zipFieldGroup));

        return saveButton;
    }

    public void trySaveZip(FieldGroup zipFieldGroup) {
        int ziplocationId = Integer.parseInt(zipFieldGroup.getField(ZIPCODE).getValue().toString());
        String name = zipFieldGroup.getField(CITY).getValue().toString();
        String description = zipFieldGroup.getField(STATE).getValue().toString();

        try{
            ZipLocationDTO zipLocationDTO = new ZipLocationDTO(ziplocationId, name, description);
            iZipLocationService.save(zipLocationDTO);
            Notification.show("New Zip added!", Notification.Type.TRAY_NOTIFICATION);
        } catch (Exception err){ //I can't handle the correct Exception ConstraintViolationException
            Notification.show("Error adding new Zip: " + err.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
    }

    private void bindForm(ZipLocationDTO zipLocationDTO, FormLayout form, PropertysetItem item, String mode) {
        form.addComponent(new Label(mode));
        FieldGroup binder = new FieldGroup(item);

        if (EDIT_MODE.equals(mode)){
            setEditForm(zipLocationDTO, item, form, binder);
        } else if (NEW_MODE.equals(mode)){
            setNewForm(item, form, binder);
        }
    }

    private void setFormStyle(FormLayout form) {
        form.setImmediate(true);
        form.addStyleName("zip-view-form-container");
    }

    @Override
    void configureComponents() {

    }

    @Override
    IBrowser getBrowser() {
        return null;
    }
}