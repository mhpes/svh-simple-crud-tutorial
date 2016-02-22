package es.mhp.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import entities.Address;
import entities.AddressNew;
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
        final VerticalLayout menu = new VerticalLayout();
        menu.setSizeFull();
        menu.setMargin(true);

        setContent(menu);

        List<AddressNew> addresses = new ArrayList<AddressNew>();

        /*addresses.add(new AddressNew(1, "MainStreet", "SecondaryStreet", "Tenerife", "Canarias", BigDecimal.ONE, BigDecimal.TEN));
        addresses.add(new AddressNew(2, "MainStreet_1", "SecondaryStreet_1", "Las Palmas", "Canarias", BigDecimal.ONE, BigDecimal.TEN));
        addresses.add(new AddressNew(3, "MainStreet_2", "SecondaryStreet_2", "El Hierro", "Canarias", BigDecimal.ONE, BigDecimal.TEN));*/

        BeanItemContainer<AddressNew> ds = new BeanItemContainer<AddressNew>(AddressNew.class, addresses);
        Grid grid = new Grid(ds);
        grid.setSizeFull();

        menu.addComponent(grid);
        menu.setExpandRatio(grid, 1);

        return menu;
    }

    private FormLayout CreateForm() {
        FormLayout form = new FormLayout();

        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("name", new ObjectProperty<String>("Eduardo mola"));
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

