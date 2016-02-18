package es.mhp.ui;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import entities.Example;
import es.mhp.services.IServicePetshop;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import java.util.Collection;

@SuppressWarnings("serial")
//@Theme("holamundo")
public class holamundoui extends UI {

    private IServicePetshop servicePetshop;

    private final String BTN_CAPTION_MOSTRAR_MENSAJE = "Mostrar mensaje";
    private final String BTN_CAPTION_OCULTAR_MENSAJE = "Ocultar mensaje";
    private final String MENSAJE = "Hola Mundo!!!";

    public holamundoui() {
    }

    @Override
    protected void init(VaadinRequest request) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetshopUnit");

        final VerticalLayout layout = new VerticalLayout();

        HorizontalLayout horiz = new HorizontalLayout();
        horiz.addStyleName("outlined");

        Collection<Example> people = Lists.newArrayList(
                new Example("Nicolaus Copernicus", 9555),
                new Example("Galileo Galilei", 788),
                new Example("Johannes Kepler", 22));

        Table table = new Table("Example");

        table.addContainerProperty("name", String.class, null);
        table.addContainerProperty("telephone", Integer.class, null);

        for (Example person: people){
            table.addItem(person);
        }

        table.setSelectable(true);
        table.setImmediate(true);

        horiz.setSizeFull();
        horiz.addComponent(table);

        layout.setSizeFull();
        layout.addComponent(horiz);
        layout.setExpandRatio(horiz, 1);
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = true, ui = holamundoui.class)
    public static class MyUIServlet extends VaadinServlet {

    }
}