package es.mhp.search.impl;

import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.search.ISearchForm;
import es.mhp.toolbar.IToolbar;


public abstract class AbstractSearchForm extends VerticalLayout implements ISearchForm {

    public abstract void buildSearchForm(IBrowser browser, IToolbar toolbar);

    public AbstractSearchForm() {
        this.setSizeFull();
        this.setMargin(true);
    }
}
