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

import java.util.Set;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = AddressView.VIEW_NAME)
public class AddressView extends AbtractView<AddressDTO> {
    public static final String VIEW_NAME = "Addresses";

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
        form.addStyleName("address-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty("Address Id", new ObjectProperty(addressDTO.getAddressId()));
        item.addItemProperty("Main Street", new ObjectProperty(addressDTO.getMainStreet()));
        item.addItemProperty("Secondary Street", new ObjectProperty(addressDTO.getSecondaryStreet()));
        //item.addItemProperty("Zip Code Id", new ObjectProperty(address.getZipLocation().getZipCodeId()));
        item.addItemProperty("City", new ObjectProperty(addressDTO.getCity()));
        item.addItemProperty("State", new ObjectProperty(addressDTO.getState()));
        item.addItemProperty("Latitude", new ObjectProperty(addressDTO.getLatitude()));
        item.addItemProperty("Longitude", new ObjectProperty(addressDTO.getLongitude()));

        FieldGroup binder = new FieldGroup(item);
        form.addComponent(binder.buildAndBind("Address Id"));
        form.addComponent(binder.buildAndBind("Main Street"));
        form.addComponent(binder.buildAndBind("Secondary Street"));
        //form.addComponent(binder.buildAndBind("Zip Code Id"));
        form.addComponent(binder.buildAndBind("City"));
        form.addComponent(binder.buildAndBind("State"));
        form.addComponent(binder.buildAndBind("Latitude"));
        form.addComponent(binder.buildAndBind("Longitude"));

        form.addComponent(createSaveButton(addressDTO));

        return form;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    private Button createSaveButton(AddressDTO addressDTO){
        final Button saveButton = new Button("Delete entry");

        saveButton.addClickListener((Button.ClickListener) event -> iAddressService.delete(addressDTO));

        return saveButton;
    }
}
