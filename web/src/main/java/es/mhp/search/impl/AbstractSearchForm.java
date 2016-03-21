package es.mhp.search.impl;

import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.search.ISearchForm;


public abstract class AbstractSearchForm extends VerticalLayout implements ISearchForm {

    public abstract void buildSearchForm(IBrowser browser);

    public AbstractSearchForm() {
        this.setSizeFull();
        this.setMargin(true);
    }
}
