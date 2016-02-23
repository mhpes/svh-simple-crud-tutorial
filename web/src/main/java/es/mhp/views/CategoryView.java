package es.mhp.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Notification;

/**
 * Created by Edu on 23/02/2016.
 */
public class CategoryView extends AbtractView {
    public CategoryView(){
        Notification.show("Entrando en la vista de Category");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
