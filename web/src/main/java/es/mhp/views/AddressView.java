package  es.mhp.views;

import com.vaadin.spring.annotation.SpringView;
import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.address.AddressBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.ISearchForm;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.address.AddressSearchForm;
import es.mhp.toolbar.impl.Toolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static es.mhp.views.utils.AddressViewConstants.VIEW_NAME;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = VIEW_NAME)
public class AddressView extends AbstractView {

    @Autowired
    @Qualifier(AddressSearchForm.BEAN_NAME)
    private ISearchForm searchForm;

    @Autowired
    @Qualifier(AddressBrowser.BEAN_NAME)
    private IBrowser browser;

    public AddressView() {
        this.addStyleName("address-view");
    }

    @Override
    protected void addComponentsToView() {
        this.removeAllComponents();
        this.addComponent((AbstractSearchForm)searchForm);
        this.addComponent((Toolbar)toolbar);
        this.addComponent((AbstractBrowser)browser);

        configureComponents();
    }

    @Override
    protected void configureComponents() {
        browser.buildBrowser();
        toolbar.buildToolbar();
        updateToolbar(StateType.INITIAL);
        searchForm.buildSearchForm(browser, toolbar);
    }

    @Override
    protected IBrowser getBrowser() {
        return browser;
    }
}

