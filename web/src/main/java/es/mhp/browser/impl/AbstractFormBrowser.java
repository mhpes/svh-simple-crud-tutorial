package es.mhp.browser.impl;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IFormBrowser;


/**
 * Created by Edu on 18/03/2016.
 */
public abstract class AbstractFormBrowser<T> extends VerticalLayout  implements IFormBrowser {
    abstract FormLayout createFormBrowser();
}
