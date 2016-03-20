package es.mhp.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AddressBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.ISearchForm;
import es.mhp.services.dto.AddressDTO;
import es.mhp.toolbar.IToolbar;
import es.mhp.toolbar.impl.Toolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static es.mhp.views.utils.AddressViewUtils.VIEW_NAME;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = VIEW_NAME)
public class AddressView extends AbstractView<AddressDTO> {

    @Autowired
    private ISearchForm searchForm;

    @Autowired
    private IToolbar toolbar;

    @Autowired
    @Qualifier(AddressBrowser.BEAN_NAME)
    private IBrowser browser;

    public AddressView() {
        setSizeFull();
        this.addStyleName("address-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //this.removeAllComponents();
        createMainLayout();
        //At first time, the toolbar is initialized to the INITIAL state
        toolbar.updateToolbar(StateType.INITIAL);
    }

    @Override
    protected void createMainLayout() {
        buildComponents();
        this.removeAllComponents();

//        browser.getIGridBrowser().setVisible(true);
//        browser.getIFormBrowser().setVisible(false);

        this.addComponent((AbstractBrowser) browser);
        this.addComponent((Toolbar) toolbar);
    }

    private void buildComponents() {
        this.removeAllComponents();
        //Inicializar sus componentes y poner visible el que sea
        browser.buildBrowser();
        toolbar.buildToolbar(browser.getBrowserLayout());
        toolbar.buildToolbar();
    }

    private Layout createToolbar(StateType stateType) {
        toolbar.setButtonsInvisible();
        toolbar.setButtonVisibilityByState(stateType);
        return toolbar.getToolbarLayout();
    }

    private void setTableSyle(VerticalLayout layout) {
        layout.setSizeFull();
        layout.setMargin(true);
    }

    public IBrowser getBrowser() {
        return browser;
    }
}

