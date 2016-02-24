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
import entities.Address;
import es.mhp.services.IAdressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = AddressView.VIEW_NAME)
public class AddressView extends AbtractView<Address> {
    public static final String VIEW_NAME = "Addresses";

    @Autowired
    private IAdressService adressService;

    public AddressView() {
        setSizeFull();
        this.addStyleName("address-view");
    }

    @Override
    Layout createTable() {
        final VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addStyleName("address-view-table-container");
        verticalLayout.setMargin(true);

        List<Address> addresses = adressService.findAllAddresses();

        BeanItemContainer<Address> addressBeanItemContainer = new BeanItemContainer<>(Address.class, addresses);
        Grid grid = new Grid(addressBeanItemContainer);
        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<Address> addressBeanItem = addressBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(addressBeanItem.getBean()));
            }
        });

        addressBeanItemContainer.addNestedContainerBean("zipLocation");

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    Layout createForm(Address address) {
        FormLayout form = new FormLayout();
        form.addStyleName("address-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty("Address Id", new ObjectProperty(address.getAddressId()));
        item.addItemProperty("Main Street", new ObjectProperty(address.getMainStreet()));
        item.addItemProperty("Secondary Street", new ObjectProperty(address.getSecondaryStreet()));
        item.addItemProperty("Zip Code Id", new ObjectProperty(address.getZipLocation().getZipCodeId()));
        item.addItemProperty("City", new ObjectProperty(address.getCity()));
        item.addItemProperty("State", new ObjectProperty(address.getState()));
        item.addItemProperty("Latitude", new ObjectProperty(address.getLatitude()));
        item.addItemProperty("Longitude", new ObjectProperty(address.getLongitude()));

        FieldGroup binder = new FieldGroup(item);
        form.addComponent(binder.buildAndBind("Address Id"));
        form.addComponent(binder.buildAndBind("Main Street"));
        form.addComponent(binder.buildAndBind("Secondary Street"));
        form.addComponent(binder.buildAndBind("Zip Code Id"));
        form.addComponent(binder.buildAndBind("City"));
        form.addComponent(binder.buildAndBind("State"));
        form.addComponent(binder.buildAndBind("Latitude"));
        form.addComponent(binder.buildAndBind("Longitude"));

        return form;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }
}
