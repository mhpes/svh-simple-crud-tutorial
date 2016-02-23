package es.mhp.ui;


import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
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
        Layout menuLayout = createMenu();

        //LAYOUT VIEW
        Layout viewLayout = createView();

        generalLayout.addComponent(menuLayout);
        generalLayout.addComponent(viewLayout);

        setContent(generalLayout);
    }

    private Layout createMenu() {
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

    private Layout createView() {

        //LAYOUT GENERAL
        VerticalLayout generalLayout = new VerticalLayout();

        //VISTA PARA MOSTRAR
        Layout view = createViewTable();
        generalLayout.addComponent(view);

        //FORM PARA EDITAR
        FormLayout form = createForm();
        generalLayout.addComponent(form);
        
        return generalLayout;
    }

    private Layout createViewTable() {
        final VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setMargin(true);

        List<Address> addresses = servicePetshop.findAllAddressesMocked();

        BeanItemContainer<Address> addressBeanItemContainer = new BeanItemContainer<Address>(Address.class, addresses);
        Grid grid = new Grid(addressBeanItemContainer);
        grid.setSizeFull();

        addressBeanItemContainer.addNestedContainerBean("zipLocation");

        verticalLayout.addComponent(grid);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    private FormLayout createForm() {
        FormLayout form = new FormLayout();

        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("name", new ObjectProperty<>(""));
        item.addItemProperty("age", new ObjectProperty<Integer>(2));

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

