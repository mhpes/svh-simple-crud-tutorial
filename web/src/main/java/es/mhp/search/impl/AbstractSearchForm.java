package es.mhp.search.impl;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.search.ISearchForm;
import es.mhp.toolbar.IToolbar;

/**
 * Created by Edu on 20/03/2016.
 */

public abstract class AbstractSearchForm extends VerticalLayout implements ISearchForm {

    protected FormLayout searchForm;

    public AbstractSearchForm() {
        searchForm = new FormLayout();
        this.setSizeFull();
        this.setMargin(true);
        this.addComponent(searchForm);
    }

    @Override
    public abstract void buildSearchForm(IBrowser browser, IToolbar toolbar);

    protected abstract void initializeComponents();
}
