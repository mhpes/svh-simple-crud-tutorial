package es.mhp.helpers.interfaces;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IBrowser {
    VerticalLayout getTableLayout();
    VerticalLayout getFormLayout();
    void setVisibility(Component component, boolean visibility);
}
