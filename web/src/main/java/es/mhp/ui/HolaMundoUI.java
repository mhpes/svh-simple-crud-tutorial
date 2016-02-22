package es.mhp.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import entities.Address;
import es.mhp.services.IServicePetshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Theme("valo")
@SpringUI
public class HolaMundoUI extends UI {
    @Autowired
    private IServicePetshop servicePetshop;

    Navigator navigator;
    protected static final String MAINVIEW = "main";

    public HolaMundoUI() {
    }

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetshopUnit");

    @Override
    protected void init(VaadinRequest request) {
        createPetshopService();

        //LAYOUT GENERAL
        HorizontalLayout generalLayout = new HorizontalLayout();

        //LAYOUT MENU
        Layout menuLayout = CreateMenu();

        //LAYOUT VIEW
        Layout viewLayout = CreateView();

        generalLayout.addComponent(menuLayout);
        generalLayout.addComponent(viewLayout);

        setContent(generalLayout);
    }

    private Layout CreateMenu() {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.addComponent(new Button("Address"));
        verticalLayout.addComponent(new Button("Category"));
        verticalLayout.addComponent(new Button("Item"));
        verticalLayout.addComponent(new Button("Product"));
        verticalLayout.addComponent(new Button("SellerContactInfo"));
        verticalLayout.addComponent(new Button("Tag"));
        verticalLayout.addComponent(new Button("ZipLocation"));

        return verticalLayout;
    }

    private Layout CreateView() {

        //LAYOUT GENERAL
        VerticalLayout generalLayout = new VerticalLayout();

        //VISTA PARA MOSTRAR
        Layout view = CreateViewTable();
        generalLayout.addComponent(view);

        //FORM PARA EDITAR
        FormLayout form = CreateForm();
        generalLayout.addComponent(form);
        
        return generalLayout;
    }

    private Layout CreateViewTable() {
        Layout menu = new VerticalLayout();

        List<Address> addresses = new ArrayList<>();

        addresses.add(new Address(1, "MainStreet", "SecondaryStreet", "Tenerife", "Canarias", BigDecimal.ONE, BigDecimal.TEN));
        addresses.add(new Address(2, "MainStreet_1", "SecondaryStreet_1", "Las Palmas", "Canarias", BigDecimal.ONE, BigDecimal.TEN));
        addresses.add(new Address(3, "MainStreet_2", "SecondaryStreet_2", "El Hierro", "Canarias", BigDecimal.ONE, BigDecimal.TEN));

        //Grid grid = new Grid("Table 1, loop through the items");
        IndexedContainer container1 = new IndexedContainer();
        container1.addContainerProperty("Main street", String.class, "");
        container1.addContainerProperty("Second street", String.class, "");
        container1.addContainerProperty("City", String.class, "");
        container1.addContainerProperty("State", String.class, "");
        container1.addContainerProperty("Latitude", BigDecimal.class, "");
        container1.addContainerProperty("Longitude", BigDecimal.class, "");

        //final BeanItemContainer<Address> ds = new BeanItemContainer<>(Address.class, addresses);
        //Grid grid = new Grid("Addresses");

        /*for (Address address : addresses) {
            grid.addRow("Main street", address.getMainStreet());
            grid.addRow("Second street", address.getMainStreet());
            grid.addRow("City", address.getCity());
            grid.addRow("State", address.getState());
            grid.addRow("Latitude", address.getLatitude());
            grid.addRow("Longitude", address.getLongitude());
            /*Item item = container1.addItem(address);
            item.getItemProperty("First name").setValue(address.getMainStreet());
            item.getItemProperty("Last name").setValue(address.getSecondaryStreet());
            item.getItemProperty("City").setValue(address.getCity());
            item.getItemProperty("State").setValue(address.getState());
            item.getItemProperty("Latitude").setValue(address.getLatitude());
            item.getItemProperty("Longitude").setValue(address.getLongitude());
        }*/

        //grid.setContainerDataSource(container1);

        //menu.addComponent(grid);
        return menu;
    }

    private FormLayout CreateForm() {
        FormLayout form = new FormLayout();

        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("name", new ObjectProperty<String>("Eduardo Mola"));
        item.addItemProperty("age", new ObjectProperty<Integer>(26));

        FieldGroup binder = new FieldGroup(item);
        form.addComponent(binder.buildAndBind("Name", "name"));
        form.addComponent(binder.buildAndBind("Age", "age"));

        return form;
    }

    private void createPetshopService() {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
        servicePetshop = context.getBean(IServicePetshop.class);
    }


}

