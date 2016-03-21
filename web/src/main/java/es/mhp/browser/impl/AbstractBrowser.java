package es.mhp.browser.impl;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.services.dto.AbstractDTO;

import java.util.Set;

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
