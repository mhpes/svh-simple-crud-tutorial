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
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/*
 * Created by Edu on 23/02/2016.
*/

@SpringView(name = SellerContactInfoView.VIEW_NAME)
public class SellerContactInfoView extends AbtractView<SellerContactInfoDTO> {
    public static final String VIEW_NAME = "SellerContactInfos";
    public static final String CONTACTINFO_ID = "Seller Id";
    public static final String LAST_NAME = "Last name";
    public static final String FIRST_NAME = "First name";
    public static final String EMAIL = "Email";

    VerticalLayout sellerLayout;
    VerticalLayout sellerTable;

    @Autowired
    private ISellerContactInfoService iSellerContactInfoService;

    public SellerContactInfoView(){
        setSizeFull();
        sellerLayout = new VerticalLayout();
        sellerTable = new VerticalLayout();
        this.addStyleName("seller-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    @Override
    protected Layout createTable() {
        setTableSyle(sellerLayout);
        createFilter();
        sellerLayout.addComponent(sellerTable);
        return sellerLayout;
    }

    @Override
    protected Layout createForm(SellerContactInfoDTO sellerContactInfoDTO, String mode) {
        FormLayout form = new FormLayout();
        setFormStyle(form);

        PropertysetItem item = new PropertysetItem();
        bindForm(sellerContactInfoDTO, form, item, mode);

        return form;
    }

    private Button createDeleteButton(SellerContactInfoDTO zipLocationDTO){
        final Button deleteButton = new Button("Delete entry", FontAwesome.TRASH_O);

        deleteButton.addClickListener((Button.ClickListener) event ->
                iSellerContactInfoService.delete(zipLocationDTO));

        return deleteButton;
    }

    private void setTableSyle(VerticalLayout layout) {
        layout.setSizeFull();
        layout.setMargin(true);
    }

    private void createFilter() {
        sellerLayout.removeAllComponents();
        TextField filter = new TextField();
        sellerLayout.addComponent(filter);

        filter.setInputPrompt("Filter categories...");
        fillZipTable(iSellerContactInfoService.findAllSellers());
        filter.addTextChangeListener(e ->
                fillZipTable(iSellerContactInfoService.findAnySellers(e.getText())));
    }

    private void fillZipTable(Set<SellerContactInfoDTO> zipLocationDTOs) {
        sellerTable.removeAllComponents();

        BeanItemContainer<SellerContactInfoDTO> zipLocationDTOBeanItemContainer = new BeanItemContainer<>(SellerContactInfoDTO.class, zipLocationDTOs);
        Grid grid = new Grid(zipLocationDTOBeanItemContainer);
        grid.setColumnOrder("city", "state");

        grid.setWidth("60%");
        VerticalLayout formContainer = createZipForm(zipLocationDTOBeanItemContainer, grid);

        sellerTable.addComponent(grid);
        sellerTable.addComponent(formContainer);
        sellerTable.setExpandRatio(grid, 1);

        setNewZip(sellerTable);
    }

    private VerticalLayout createZipForm(BeanItemContainer<SellerContactInfoDTO> zipLocationDTOBeanItemContainer, Grid grid) {
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<SellerContactInfoDTO> zipLocationDTOBeanItem = zipLocationDTOBeanItemContainer.getItem(grid.getSelectedRow());
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
                    createForm(new SellerContactInfoDTO(), NEW_MODE)
            );
        });

        verticalLayout.addComponent(createButton);
        verticalLayout.addComponent(createNewZipLayout);
    }

    private void setNewForm(PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        form.addComponent(binder.buildAndBind(FIRST_NAME));
        form.addComponent(binder.buildAndBind(LAST_NAME));
        form.addComponent(binder.buildAndBind(EMAIL));

        form.addComponent(createCancelButton(form, binder));
        form.addComponent(createSaveButton(binder));
    }

    private void setEditForm(SellerContactInfoDTO sellerContactInfoDTO, PropertysetItem item, FormLayout form) {
        setItemPropertyEdit(sellerContactInfoDTO, item);

        item.addItemProperty(CONTACTINFO_ID, new ObjectProperty(sellerContactInfoDTO.getContactInfoId()));
        item.addItemProperty(FIRST_NAME, new ObjectProperty(sellerContactInfoDTO.getLastName()));
        item.addItemProperty(LAST_NAME, new ObjectProperty(sellerContactInfoDTO.getFirstName()));
        item.addItemProperty(EMAIL, new ObjectProperty(sellerContactInfoDTO.getEmail()));

        FieldGroup binder = new FieldGroup(item);
        binder.buildAndBind(CONTACTINFO_ID);
        form.addComponent(binder.buildAndBind(FIRST_NAME));
        form.addComponent(binder.buildAndBind(LAST_NAME));
        form.addComponent(binder.buildAndBind(EMAIL));
    }

    private void setItemPropertyEdit(PropertysetItem item) {
        item.addItemProperty(FIRST_NAME, new ObjectProperty<>(""));
        item.addItemProperty(LAST_NAME, new ObjectProperty<>(""));
        item.addItemProperty(EMAIL, new ObjectProperty<>(""));
    }

    private void setItemPropertyEdit(SellerContactInfoDTO zipLocationDTO, PropertysetItem item) {
        item.addItemProperty(FIRST_NAME, new ObjectProperty<>(zipLocationDTO.getFirstName()));
        item.addItemProperty(LAST_NAME, new ObjectProperty<>(zipLocationDTO.getLastName()));
        item.addItemProperty(EMAIL, new ObjectProperty<>(zipLocationDTO.getEmail()));
    }

    private Component createCancelButton(FormLayout form, FieldGroup binder) {
        Button cancelButton = new Button("Cancel", FontAwesome.TRASH_O);
        binder.discard();

        cancelButton.addClickListener((Button.ClickListener) event ->
                form.removeAllComponents()
        );

        return cancelButton;
    }

    private Button createSaveButton(FieldGroup sellerFieldGroup) {
        final Button saveButton = new Button("Save", FontAwesome.SAVE);

        saveButton.addClickListener((Button.ClickListener) event ->
                trySaveSeller(sellerFieldGroup));

        return saveButton;
    }

    public void trySaveSeller(FieldGroup sellerFieldGroup) {
        int categoryId = Integer.parseInt(sellerFieldGroup.getField(CONTACTINFO_ID).getValue().toString());
        String firstName = sellerFieldGroup.getField(FIRST_NAME).getValue().toString();
        String lastName = sellerFieldGroup.getField(LAST_NAME).getValue().toString();
        String email = sellerFieldGroup.getField(EMAIL).getValue().toString();

        try{
            SellerContactInfoDTO sellerContactInfoDTO = new SellerContactInfoDTO(categoryId, firstName, lastName, email);
            iSellerContactInfoService.save(sellerContactInfoDTO);
            Notification.show("New Seller added!", Notification.Type.TRAY_NOTIFICATION);
        } catch (Exception err){ //I can't handle the correct Exception ConstraintViolationException
            Notification.show("Error adding new Seller: " + err.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
    }

    private void bindForm(SellerContactInfoDTO sellerContactInfoDTO, FormLayout form, PropertysetItem item, String mode) {
        form.addComponent(new Label(mode));
        FieldGroup binder = new FieldGroup(item);

        if (EDIT_MODE.equals(mode)){
            setEditForm(sellerContactInfoDTO, item, form);
        } else if (NEW_MODE.equals(mode)){
            setNewForm(item, form, binder);
        }
    }

    private void setFormStyle(FormLayout form) {
        form.setImmediate(true);
        form.addStyleName("zip-view-form-container");
    }
}