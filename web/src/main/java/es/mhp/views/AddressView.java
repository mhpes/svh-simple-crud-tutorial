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

    public AddressView() {
        setSizeFull();
        this.addStyleName("address-view");
    }

    @Override
    protected Layout createTable() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addStyleName("address-view-table-container");
        verticalLayout.setMargin(true);

        Set<AddressDTO> addressDTOs = iAddressService.findAllAddresses();

        BeanItemContainer<AddressDTO> addressBeanItemContainer = new BeanItemContainer<>(AddressDTO.class, addressDTOs);
        addressBeanItemContainer.removeItem("itemCount");
        Grid grid = new Grid(addressBeanItemContainer);
        grid.removeColumn("addressId");
        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<AddressDTO> addressBeanItem = addressBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(addressBeanItem.getBean()));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(AddressDTO addressDTO) {
        FormLayout form = new FormLayout();
        form.setImmediate(true);
        form.addStyleName("address-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty(ADDRESS_ID, new ObjectProperty(addressDTO.getAddressId()));
        item.addItemProperty(MAIN_STREET, new ObjectProperty(addressDTO.getMainStreet()));
        item.addItemProperty(SECONDARY_STREET, new ObjectProperty(addressDTO.getSecondaryStreet()));
        item.addItemProperty(ZIP, new ObjectProperty(addressDTO.getZip()));
        item.addItemProperty(CITY, new ObjectProperty(addressDTO.getCity()));
        item.addItemProperty(STATE, new ObjectProperty(addressDTO.getState()));
        item.addItemProperty(LATITUDE, new ObjectProperty(addressDTO.getLatitude()));
        item.addItemProperty(LONGITUDE, new ObjectProperty(addressDTO.getLongitude()));


        FieldGroup binder = new FieldGroup(item);
        form.addComponent(binder.buildAndBind(ADDRESS_ID));
        form.addComponent(binder.buildAndBind(MAIN_STREET));
        form.addComponent(binder.buildAndBind(SECONDARY_STREET));
        form.addComponent(binder.buildAndBind(ZIP));
        form.addComponent(binder.buildAndBind(CITY));
        form.addComponent(binder.buildAndBind(STATE));
        form.addComponent(binder.buildAndBind(LATITUDE));
        form.addComponent(binder.buildAndBind(LONGITUDE));

        form.addComponent(createDeleteButton(addressDTO));
        form.addComponent(createSavebutton(binder));

        return form;
    }

    private Button createSavebutton(FieldGroup addressFieldGroup) {
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    private Button createDeleteButton(AddressDTO addressDTO){
        final Button deleteButton = new Button("Delete entry");

        deleteButton.addClickListener((Button.ClickListener) event -> iAddressService.delete(addressDTO));

        return deleteButton;
    }
}
