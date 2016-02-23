package es.mhp.views;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import entities.Address;
import entities.ZipLocation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edu on 23/02/2016.
 */

@DesignRoot
public class AddressView extends AbtractView {

    public AddressView() {
        setSizeFull();
        Notification.show("En AddressView");
        createViewTable();
    }

    private Layout createViewTable() {
        final VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setMargin(true);

        List<Address> addresses = findAllAddressesMocked();

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

    private List<Address> findAllAddressesMocked() {
        List<Address> addresses = new ArrayList<>();

        addresses.add(new Address(1, "MainStreet", "SecondaryStreet", "Tenerife", "Canarias", BigDecimal.ONE, BigDecimal.TEN, new ZipLocation(1, 8822, "S/C de Tenerife", "Canary Islands")));
        addresses.add(new Address(2, "MainStreet_1", "SecondaryStreet_1", "Las Palmas", "Canarias", BigDecimal.ONE, BigDecimal.TEN, new ZipLocation(1, 32108, "Arico powah", "Canary Islands")));
        addresses.add(new Address(3, "MainStreet_2", "SecondaryStreet_2", "El Hierro", "Canarias", BigDecimal.ONE, BigDecimal.TEN, new ZipLocation(1, 38108, "Añaza", "Canary Islands")));

        return addresses;
    }

    public FormLayout createForm(Address address) {
        FormLayout form = new FormLayout();

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

    }
}
