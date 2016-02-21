package es.mhp.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import es.mhp.services.IServicePetshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
//@Theme("holamundo")


@Component
@Scope("prototype")
@SpringUI
public class HolaMundoUI extends UI {
    @Autowired
    private IServicePetshop servicePetshop;

    public HolaMundoUI() {
    }

    @Override
    protected void init(VaadinRequest request) {
        createPetshopService();

        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetshopUnit");
        final VerticalLayout layout = new VerticalLayout();

        String example = servicePetshop.test();

//        List<Address> addresses = exampleClass.getPetshopService().findAllAddresses();

        HorizontalLayout horiz = new HorizontalLayout();
        setContent(new Label(example));
        /*horiz.addStyleName("outlined");

        Table table = new Table("Example");

        table.addContainerProperty("name", String.class, null);
        table.addContainerProperty("telephone", Integer.class, null);

        for (Address address: addresses){
            table.addItem(address);
        }

        table.setSelectable(true);
        table.setImmediate(true);

        horiz.setSizeFull();
        horiz.addComponent(table);

        layout.setSizeFull();
        layout.addComponent(horiz);
        layout.setExpandRatio(horiz, 1);*/
    }

    private void createPetshopService() {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
        servicePetshop = context.getBean(IServicePetshop.class);
    }


}

