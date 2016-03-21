package es.mhp.browser.impl;

import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;

/**
 * Created by Edu on 17/03/2016.
 */
public abstract class AbstractBrowser extends VerticalLayout implements IBrowser {
    public AbstractBrowser() { }

    public void setStyle() {
        this.setSizeFull();
        this.setMargin(true);
    }
}
