package es.mhp.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@Theme("holamundo")
public class HolamundoUI extends UI {

//    @WebServlet(value = "/ui", asyncSupported = true)
//    @VaadinServletConfiguration(productionMode = false, ui = HolamundoUI.class)
//    public static class Servlet extends VaadinServlet {
//    }

    private final String BTN_CAPTION_MOSTRAR_MENSAJE = "Mostrar mensaje";
    private final String BTN_CAPTION_OCULTAR_MENSAJE = "Ocultar mensaje";
    private final String MENSAJE = "Hola Mundo!!!";

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        final Label mensaje = new Label(MENSAJE);
        final Button button = new Button(BTN_CAPTION_MOSTRAR_MENSAJE);

        layout.setMargin(true);
        setContent(layout);

        layout.addComponent(button);
        mensaje.setVisible(false);
        layout.addComponent(mensaje);

        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                mensaje.setVisible(!mensaje.isVisible());
                if (mensaje.isVisible())
                    button.setCaption(BTN_CAPTION_OCULTAR_MENSAJE);
                else
                    button.setCaption(BTN_CAPTION_MOSTRAR_MENSAJE);
            }
        });
        layout.addComponent(button);
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(ui = HolamundoUI.class, productionMode = false)
    protected static class MyUIServlet extends VaadinServlet {
    }
}