package es.mhp.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import es.mhp.dao.IZiplocationDao;
import es.mhp.entities.ZipLocation;
import es.mhp.services.IAddressService;
import es.mhp.services.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = AddressView.VIEW_NAME)
public class AddressView extends AbtractView<AddressDTO> {
    public static final String VIEW_NAME = "Addresses";
    public static final String ADDRESS_ID = "Address Id";
    public static final String MAIN_STREET = "Main Street";
    public static final String SECONDARY_STREET = "Secondary Street";
    public static final String ZIP = "Zip";
    public static final String CITY = "City";
    public static final String STATE = "State";
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Longitude";

    @Autowired
    private IAddressService iAddressService;

    @Autowired
    private IZiplocationDao iZiplocationDao;
    private VerticalLayout newAddresButton;

    public AddressView() {
        setSizeFull();
        this.addStyleName("address-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    @Override
    protected Layout createTable() {
        VerticalLayout verticalLayout = new VerticalLayout();
        setTableSyle(verticalLayout);
        fillAddressTable(verticalLayout);
        setNewAddress(verticalLayout);
        return verticalLayout;
    }

    private void fillAddressTable(VerticalLayout verticalLayout) {
        Set<AddressDTO> addressDTOs = iAddressService.findAllAddresses();

        BeanItemContainer<AddressDTO> addressBeanItemContainer = new BeanItemContainer<>(AddressDTO.class, addressDTOs);
        addressBeanItemContainer.removeItem("itemCount");
        Grid grid = new Grid(addressBeanItemContainer);
        grid.setColumnOrder("mainStreet", "secondaryStreet", "city", "state", "latitude", "longitude");
        grid.removeColumn("addressId");
        grid.removeColumn("zip");
        grid.setWidth("60%");

        VerticalLayout formContainer = createAddressForm(addressBeanItemContainer, grid);

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);
    }

    private VerticalLayout createAddressForm(BeanItemContainer<AddressDTO> addressBeanItemContainer, Grid grid) {
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<AddressDTO> addressBeanItem = addressBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(addressBeanItem.getBean(), EDIT_MODE));
            }
        });

        return formContainer;
    }

    private void setTableSyle(VerticalLayout verticalLayout) {
        verticalLayout.setSizeFull();
        verticalLayout.addStyleName("address-view-table-container");
        verticalLayout.setMargin(true);
    }

    @Override
    protected Layout createForm(AddressDTO addressDTO, String mode) {
        FormLayout form = new FormLayout();
        setFormStyle(form);

        PropertysetItem item = new PropertysetItem();
        bindForm(addressDTO, form, item, mode);

        return form;
    }

    private void setFormStyle(FormLayout form) {
        form.setImmediate(true);
        form.addStyleName("address-view-form-container");
    }

    private void bindForm(AddressDTO addressDTO, FormLayout form, PropertysetItem item, String mode) {
        form.addComponent(new Label(mode));
        FieldGroup binder = new FieldGroup(item);

        if (EDIT_MODE.equals(mode)){
            setEditForm(addressDTO, item, form, binder);
        } else if (NEW_MODE.equals(mode)){
            setNewForm(addressDTO, item, form, binder);
        }
    }

    private void setNewForm(AddressDTO addressDTO, PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        ComboBox selectZip = new ComboBox("Zips");

        binder.buildAndBind(ADDRESS_ID);

        form.addComponent(binder.buildAndBind(ZIP, selectZip, ComboBox.class));

        Set<ZipLocation> zipList = iZiplocationDao.findAll();

        for (ZipLocation zipLocation : zipList){
            selectZip.addItem(zipLocation.getCity() + " " + zipLocation.getState());
        }

        //binder.buildAndBind(ZIP);

        form.addComponent(binder.buildAndBind(MAIN_STREET));
        form.addComponent(binder.buildAndBind(SECONDARY_STREET));
        form.addComponent(binder.buildAndBind(CITY));
        form.addComponent(binder.buildAndBind(STATE));
        form.addComponent(binder.buildAndBind(LATITUDE));
        form.addComponent(binder.buildAndBind(LONGITUDE));

        form.addComponent(createCancelButton(form));
        form.addComponent(createSaveButton(binder));
    }

    private Component createCancelButton(FormLayout form) {
        Button cancelButton = new Button("Cancel");

        cancelButton.addClickListener((Button.ClickListener) event ->
            form.removeAllComponents()
        );

        return cancelButton;
    }

    private void setEditForm(AddressDTO addressDTO, PropertysetItem item, FormLayout form, FieldGroup binder) {

        setItemPropertyEdit(addressDTO, item);

        binder.buildAndBind(ADDRESS_ID);
        binder.buildAndBind(ZIP);

        form.addComponent(binder.buildAndBind(MAIN_STREET));
        form.addComponent(binder.buildAndBind(SECONDARY_STREET));
        form.addComponent(binder.buildAndBind(CITY));
        form.addComponent(binder.buildAndBind(STATE));
        form.addComponent(binder.buildAndBind(LATITUDE));
        form.addComponent(binder.buildAndBind(LONGITUDE));

        form.addComponent(createDeleteButton(addressDTO));
        form.addComponent(createSaveButton(binder));
    }

    private void setItemPropertyEdit(PropertysetItem item) {
        item.addItemProperty(ADDRESS_ID, new ObjectProperty<>(0));
        item.addItemProperty(MAIN_STREET, new ObjectProperty<>(""));
        item.addItemProperty(SECONDARY_STREET, new ObjectProperty<>(""));
        item.addItemProperty(ZIP, new ObjectProperty<>(""));
        item.addItemProperty(CITY, new ObjectProperty<>(""));
        item.addItemProperty(STATE, new ObjectProperty<>(""));
        item.addItemProperty(LATITUDE, new ObjectProperty<>(new BigDecimal(0.0)));
        item.addItemProperty(LONGITUDE, new ObjectProperty<>(new BigDecimal(0.0)));
    }

    private void setItemPropertyEdit(AddressDTO addressDTO, PropertysetItem item) {
        item.addItemProperty(ADDRESS_ID, new ObjectProperty(addressDTO.getAddressId()));
        item.addItemProperty(MAIN_STREET, new ObjectProperty(addressDTO.getMainStreet()));
        item.addItemProperty(SECONDARY_STREET, new ObjectProperty(addressDTO.getSecondaryStreet()));
        item.addItemProperty(ZIP, new ObjectProperty(addressDTO.getZip()));
        item.addItemProperty(CITY, new ObjectProperty(addressDTO.getCity()));
        item.addItemProperty(STATE, new ObjectProperty(addressDTO.getState()));
        item.addItemProperty(LATITUDE, new ObjectProperty(addressDTO.getLatitude()));
        item.addItemProperty(LONGITUDE, new ObjectProperty(addressDTO.getLongitude()));
    }

    private Button createSaveButton(FieldGroup addressFieldGroup) {
        final Button saveButton = new Button("Save");

        saveButton.addClickListener((Button.ClickListener) event -> {
            int addressId = Integer.parseInt(addressFieldGroup.getField(ADDRESS_ID).getValue().toString());
            String mainStreet = addressFieldGroup.getField(MAIN_STREET).getValue().toString();
            String secondaryStreet = addressFieldGroup.getField(SECONDARY_STREET).getValue().toString();
            int zip = Integer.parseInt(addressFieldGroup.getField(ZIP).getValue().toString());
            String city = addressFieldGroup.getField(CITY).getValue().toString();
            String state = addressFieldGroup.getField(STATE).getValue().toString();

            BigDecimal longitude =  BigDecimal.ONE;
            BigDecimal latitude = BigDecimal.TEN;

            AddressDTO addressDTO = new AddressDTO(addressId, mainStreet, secondaryStreet,zip ,city, state, latitude, longitude);
            iAddressService.save(addressDTO);
        });

        return saveButton;
    }

    private Button createDeleteButton(AddressDTO addressDTO){
        final Button deleteButton = new Button("Delete entry");

        deleteButton.addClickListener((Button.ClickListener) event ->
                iAddressService.delete(addressDTO));

        return deleteButton;
    }

    public void setNewAddress(VerticalLayout verticalLayout) {
        VerticalLayout createNewAddressLayout = new VerticalLayout();

        Button createButton = new Button("New Address");

        createButton.addClickListener((Button.ClickListener) event -> {
            createNewAddressLayout.removeAllComponents();
            createNewAddressLayout.addComponent(createForm
                    (new AddressDTO(), NEW_MODE)
            );
        });

        verticalLayout.addComponent(createButton);
        verticalLayout.addComponent(createNewAddressLayout);
    }
}
