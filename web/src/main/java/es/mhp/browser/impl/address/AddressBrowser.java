package es.mhp.browser.impl.address;

import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.impl.address.presenter.AddressBrowserPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(AddressBrowser.BEAN_NAME)
@Scope("prototype")
public class AddressBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "address_browser";

    @Autowired
    @Qualifier(AddressGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(AddressFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    @Autowired
    private AddressBrowserPresenter addressBrowserPresenter;

    public AddressBrowser() {
        super();
    }

    @Override
    public void buildBrowser() {
        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        addressBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser);
        gridBrowser.configure();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        addressBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
        formBrowser.createFormBrowser(gridBrowser.getSelectedGridRow(), mode);
    }
}
