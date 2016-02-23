package es.mhp.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;

/**
 * Created by Edu on 23/02/2016.
 */
public class ErrorView extends AbtractView{
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();
        setMargin(true);
        Label label = new Label("Could not find a view with that name. You are most likely doing it wrong.");

        addComponent(label);
    }
}
