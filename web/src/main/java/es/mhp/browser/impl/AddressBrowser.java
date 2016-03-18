package es.mhp.browser.impl;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(AddressBrowser.BEAN_NAME)
public class AddressBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "address_browser";

    @Autowired
    @Qualifier(AddressGridBrowser.BEAN_NAME)
    private IGridBrowser iGridBrowser;

    @Autowired
    @Qualifier(AddressFormBrowser.BEAN_NAME)
    private IFormBrowser iFormBrowser;

    public AddressBrowser() { }

    @Override
    public void setVisibility(Component component, boolean visibility) {
        component.setVisible(visibility);
    }

    @Override
    public void buildBrowser() {
        setVisibility(iGridBrowser.getGridBrowser(), true);
        setVisibility(iFormBrowser.getFormBrowser(), false);

        iGridBrowser.buildGridBrowser();
        iFormBrowser.buildFormBrowser();

        this.addComponent(iFormBrowser.getFormBrowser());
        this.addComponent(iGridBrowser.getGridBrowser());
    }

    @Override
    public AddressBrowser getBrowserLayout() {
        return this;
    }

    @Override
    public AddressGridBrowser getIGridBrowser() {
        return iGridBrowser.getGridBrowser();
    }

    public void setIGridBrowser(AddressGridBrowser iGridBrowser) {
        this.iGridBrowser = iGridBrowser;
    }

    @Override
    public AddressFormBrowser getIFormBrowser() {
        return iFormBrowser.getFormBrowser();
    }

    public void setIFormBrowser(AddressFormBrowser iFormBrowser) {
        this.iFormBrowser = iFormBrowser;
    }
}
