package es.mhp.browser.impl;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IFormBrowser;

import javax.annotation.PostConstruct;


/**
 * Created by Edu on 18/03/2016.
 */
public abstract class AbstractFormBrowser extends VerticalLayout  implements IFormBrowser {

    public AbstractFormBrowser() {
        this.setSizeFull();
        this.setMargin(true);
    }
}
