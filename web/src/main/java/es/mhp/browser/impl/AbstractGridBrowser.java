package es.mhp.browser.impl;

import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IGridBrowser;

/**
 * Created by Edu on 18/03/2016.
 */
public abstract class AbstractGridBrowser extends VerticalLayout implements IGridBrowser {

    public AbstractGridBrowser() {
        this.setSizeFull();
        this.setMargin(true);
    }

    abstract protected void setColumnProperties();
}
