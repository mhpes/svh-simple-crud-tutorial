package es.mhp.browser.impl;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;

/**
 * Created by Edu on 17/03/2016.
 */
public abstract class AbstractBrowser extends VerticalLayout implements IBrowser {

    public AbstractBrowser() { }

    @Override
    public void setVisibility(Component component, boolean visibility) {
        component.setVisible(visibility);
    }

    @Override
    public AbstractBrowser getBrowserLayout() {
        return this;
    }
}
