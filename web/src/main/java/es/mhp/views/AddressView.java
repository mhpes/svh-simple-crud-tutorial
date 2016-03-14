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
import es.mhp.services.IAddressService;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.dto.ZipLocationDTO;
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
    public static final String ALL = "All";
    public static final String ANY = "Any";

    private VerticalLayout addressLayout;
    private VerticalLayout addressTable;

    @Autowired
    private IAddressService iAddressService;

    @Autowired
    private IZipLocationService iZipLocationService;
    public static String[] cityList = {"Millbrae","Palo Alto","San Francisco","Stanford"};

    public AddressView() {
        setSizeFull();
        addressLayout = new VerticalLayout();
        addressTable = new VerticalLayout();
        this.addStyleName("address-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    @Override
    protected Layout createTable() {
        setTableSyle(addressLayout);
        createFilter();
        addressLayout.addComponent(addressTable);
        return addressLayout;
    }

    private void fillAddressTable(Set<AddressDTO> addressDTOs) {
        addressTable.removeAllComponents();

        BeanItemContainer<AddressDTO> addressBeanItemContainer = new BeanItemContainer<>(AddressDTO.class, addressDTOs);
        addressBeanItemContainer.removeItem("itemCount");
        Grid grid = new Grid(addressBeanItemContainer);
        grid.setColumnOrder("mainStreet", "secondaryStreet", "city", "state", "latitude", "longitude");
        grid.removeColumn("addressId");
        grid.removeColumn("zipLocation");
        grid.setWidth("60%");

        VerticalLayout formContainer = createAddressForm(addressBeanItemContainer, grid);

        addressTable.addComponent(grid);
        addressTable.addComponent(formContainer);
        addressTable.setExpandRatio(grid, 1);

        setNewAddress(addressTable);
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

    private void setTableSyle(VerticalLayout layout) {
        layout.setSizeFull();
        layout.setMargin(true);
    }

    @Override
    protected Layout createForm(AddressDTO addressDTO, String mode) {
        FormLayout form = new FormLayout();
        setFormStyle(form);

        BeanItem item = new BeanItem(addressDTO);
        bindForm(addressDTO, form, item, mode);

        return form;
    }

    private void setFormStyle(FormLayout form) {
        form.setImmediate(true);
        form.addStyleName("address-view-form-container");
    }

    private void bindForm(AddressDTO addressDTO, FormLayout form, BeanItem item, String mode) {
        form.addComponent(new Label(mode));
        FieldGroup binder = new FieldGroup(item);

        if (EDIT_MODE.equals(mode)){
            setEditForm(addressDTO, item, form, binder);
        } else if (NEW_MODE.equals(mode)){
            setNewForm(item, form, binder);
        }
    }

    private void setNewForm(PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        Set<ZipLocationDTO> zipList = iZipLocationService.findAllZipLocations();
        ComboBox selectZip = new ComboBox("Zips");
        TextField mainStreet = new TextField(MAIN_STREET);
        mainStreet.setImmediate(true);

        BeanItemContainer<ZipLocationDTO> zipLocationContainer = new BeanItemContainer<>(ZipLocationDTO.class);

        for (ZipLocationDTO zipLocationDTO : zipList){
            zipLocationContainer.addBean(zipLocationDTO);
        }

        selectZip.setItemCaptionPropertyId("city");
        selectZip.setContainerDataSource(zipLocationContainer);
        selectZip.setRequired(true);
        selectZip.setImmediate(true);

        binder.bind(selectZip, ZIP);
        form.addComponent(selectZip);

        binder.buildAndBind(ADDRESS_ID);
        binder.bind(mainStreet, MAIN_STREET);

        form.addComponent(mainStreet);
        form.addComponent(binder.buildAndBind(SECONDARY_STREET));
        form.addComponent(binder.buildAndBind(CITY));
        form.addComponent(binder.buildAndBind(STATE));
        form.addComponent(binder.buildAndBind(LATITUDE));
        form.addComponent(binder.buildAndBind(LONGITUDE));

        form.addComponent(createCancelButton(form, binder));
        form.addComponent(createSaveButton(binder));
    }

    private Component createCancelButton(FormLayout form, FieldGroup binder) {
        Button cancelButton = new Button("Cancel", FontAwesome.TRASH_O);
        binder.discard();

        cancelButton.addClickListener((Button.ClickListener) event ->
            form.removeAllComponents()
        );

        return cancelButton;
    }

    private void setEditForm(AddressDTO addressDTO, BeanItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(addressDTO, item);

        binder.buildAndBind(ADDRESS_ID);

        ComboBox selectZip = new ComboBox("Zips");

        BeanItemContainer<ZipLocationDTO> zipLocationContainer = new BeanItemContainer<>(ZipLocationDTO.class);
        Set<ZipLocationDTO> zipList = iZipLocationService.findAllZipLocations();

        for (ZipLocationDTO zip : zipList){
            zipLocationContainer.addBean(zip);
        }

        selectZip.setContainerDataSource(zipLocationContainer);

        selectZip.setItemCaptionPropertyId("zipCodeId");
        selectZip.select(addressDTO.getZipLocation());
        selectZip.setNullSelectionAllowed(false);
        selectZip.setRequired(true);

        binder.bind(selectZip, ZIP);
        form.addComponent(selectZip);

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
        item.addItemProperty(LATITUDE, new ObjectProperty<>(new BigDecimal(0)));
        item.addItemProperty(LONGITUDE, new ObjectProperty<>(new BigDecimal(0)));
    }

    private void setItemPropertyEdit(AddressDTO addressDTO, BeanItem item) {
        item.addItemProperty(ADDRESS_ID, new ObjectProperty(addressDTO.getAddressId()));
        item.addItemProperty(MAIN_STREET, new ObjectProperty(addressDTO.getMainStreet()));
        item.addItemProperty(SECONDARY_STREET, new ObjectProperty(addressDTO.getSecondaryStreet()));
        item.addItemProperty(ZIP, new ObjectProperty(addressDTO.getZipLocation()));
        item.addItemProperty(CITY, new ObjectProperty(addressDTO.getCity()));
        item.addItemProperty(STATE, new ObjectProperty(addressDTO.getState()));
        item.addItemProperty(LATITUDE, new ObjectProperty(addressDTO.getLatitude()));
        item.addItemProperty(LONGITUDE, new ObjectProperty(addressDTO.getLongitude()));
    }

    private Button createSaveButton(FieldGroup addressFieldGroup) {
        final Button saveButton = new Button("Save", FontAwesome.SAVE);

        saveButton.addClickListener((Button.ClickListener) event ->
                trySaveAddress(addressFieldGroup));

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
            createNewAddressLayout.addComponent(
                    createForm(new AddressDTO(), NEW_MODE)
            );
        });

        verticalLayout.addComponent(createButton);
        verticalLayout.addComponent(createNewAddressLayout);
    }

    private void createFilter() {
        addressLayout.removeAllComponents();
        FormLayout formBrowser = createFormBrowser();
        addressLayout.addComponent(formBrowser);
    }

    private FormLayout createFormBrowser() {
        FormLayout formBrowser = new FormLayout();

        TextField mainStreet = new TextField(MAIN_STREET);
        TextField secondaryStreet = new TextField(SECONDARY_STREET);
        ComboBox city = new ComboBox(CITY);
        city.addItems(cityList);
        city.select("Stanford");
        city.setNullSelectionAllowed(false);

        ComboBox state = new ComboBox(STATE);
        state.addItems(iAddressService.stateList());

        OptionGroup browserWay = new OptionGroup();
        browserWay.addItems("All", "Any");
        browserWay.select("All");

        Button browserButton = new Button("Search Addresses!");
        fillAddressTable(iAddressService.findAllAddresses());

        browserButton.addClickListener(e -> {
            AddressDTO addressDTO;
            if (state.getValue() == null)
                addressDTO = new AddressDTO(mainStreet.getValue().toString(), secondaryStreet.getValue().toString(), city.getValue().toString());
            else
                addressDTO = new AddressDTO(mainStreet.getValue().toString(), secondaryStreet.getValue().toString(), city.getValue().toString(), state.getValue().toString());

            String way = browserWay.getValue().toString();

            if (ALL.equals(way)){
                fillAddressTable(iAddressService.findAllAddresses(addressDTO));
            }
            else if (ANY.equals(way)){
                fillAddressTable(iAddressService.findAnyAddresses(addressDTO));
            }
        });

        formBrowser.addComponents(mainStreet, secondaryStreet, city, state, browserWay, browserButton);

        return formBrowser;
    }

    public void trySaveAddress(FieldGroup addressFieldGroup) {
        int addressId = Integer.parseInt(addressFieldGroup.getField(ADDRESS_ID).getValue().toString());
        String mainStreet = addressFieldGroup.getField(MAIN_STREET).getValue().toString();
        String secondaryStreet = addressFieldGroup.getField(SECONDARY_STREET).getValue().toString();
        ZipLocationDTO zipLocationDTO = (ZipLocationDTO) addressFieldGroup.getField(ZIP).getValue();
        String city = addressFieldGroup.getField(CITY).getValue().toString();
        String state = addressFieldGroup.getField(STATE).getValue().toString();

        BigDecimal longitude =  BigDecimal.ONE;
        BigDecimal latitude = BigDecimal.TEN;

        try{
            AddressDTO addressDTO = new AddressDTO(addressId, mainStreet, secondaryStreet, zipLocationDTO, city, state, latitude, longitude);
            iAddressService.save(addressDTO);
            Notification.show("New Address added!", Notification.Type.TRAY_NOTIFICATION);
        } catch (Exception err){ //I can't handle the correct Exception ConstraintViolationException
            Notification.show("Error adding new Address: " + err.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
    }
}

