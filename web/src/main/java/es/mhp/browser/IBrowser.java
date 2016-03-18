package es.mhp.browser;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.impl.AbstractBrowser;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IBrowser {
    AbstractBrowser getBrowserLayout();
    VerticalLayout getIGridBrowser();
    VerticalLayout getIFormBrowser();
    void setVisibility(Component component, boolean visibility);
    void buildBrowser();

    void createForm(Object id, String mode);
}
