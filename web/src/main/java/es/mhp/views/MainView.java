package es.mhp.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Layout;

/**
 * Created by Edu on 23/02/2016.
 */
@SpringView(name = MainView.VIEW_NAME)
public class MainView extends AbstractView {
    public static final String VIEW_NAME = "";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.addStyleName("main-view");
    }

    @Override
    protected void createMainLayout() {}

//    @Override
    protected Layout createForm(Object entity, String mode) {
        return null;
    }
}
