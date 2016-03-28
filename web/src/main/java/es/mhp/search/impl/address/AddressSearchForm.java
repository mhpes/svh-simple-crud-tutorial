package es.mhp.search.impl.address;

import com.vaadin.ui.FormLayout;
import es.mhp.browser.IBrowser;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.address.presenter.AddressSearchFormPresenter;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Scope("prototype")
@Component(AddressSearchForm.BEAN_NAME)
public class AddressSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "address_search_form";

    @Autowired
    private AddressSearchFormPresenter presenter;

    public AddressSearchForm() {
        super();
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();
        presenter.buildSearchForm(browser, toolbar, searchForm);
        addComponent(searchForm);
    }

    public FormLayout getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(FormLayout searchForm) {
        this.searchForm = searchForm;
    }

    public AddressSearchFormPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(AddressSearchFormPresenter presenter) {
        this.presenter = presenter;
    }
}
