package es.mhp.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import es.mhp.browser.IBrowser;
import es.mhp.search.ISearchForm;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.browser.impl.address.AddressBrowser;
import es.mhp.search.impl.address.AddressSearchForm;
import es.mhp.browser.utils.StateType;
import es.mhp.services.dto.AddressDTO;
import es.mhp.toolbar.impl.Toolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static es.mhp.views.utils.AddressViewConstants.VIEW_NAME;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = VIEW_NAME)
public class AddressView extends AbstractView<AddressDTO> {

    @Autowired
    @Qualifier(AddressSearchForm.BEAN_NAME)
    private ISearchForm searchForm;

    @Autowired
    @Qualifier(AddressBrowser.BEAN_NAME)
    private IBrowser browser;

    public AddressView() {
        this.setSizeFull();
        this.addStyleName("address-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        createMainLayout();
        //At first time, the toolbar is initialized to the INITIAL state
        toolbar.updateToolbar(StateType.INITIAL);
    }

    @Override
    protected void createMainLayout() {
        buildComponents();
        this.removeAllComponents();
        this.addComponent((AbstractSearchForm)searchForm);
        this.addComponent((AbstractBrowser)browser);
        this.addComponent((Toolbar)toolbar);
    }

    private void buildComponents() {
        browser.buildBrowser();
        toolbar.buildToolbar(browser);
        searchForm.buildSearchForm(browser);
    }
}

