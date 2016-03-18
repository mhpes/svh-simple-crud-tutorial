package es.mhp.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.AddressBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.services.dto.AddressDTO;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static es.mhp.views.utils.AddressViewUtils.VIEW_NAME;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = VIEW_NAME)
public class AddressView extends AbstractView<AddressDTO> {

    @Autowired
    private IToolbar iToolbar;

    @Autowired
    @Qualifier(AddressBrowser.BEAN_NAME)
    private IBrowser iBrowser;

    public AddressView() {
        setSizeFull();
        this.addStyleName("address-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        createMainLayout();
    }

    @Override
    protected void createMainLayout() {
        setTableSyle(this);
        buildComponents();

//        iBrowser.getIGridBrowser().setVisible(true);
//        iBrowser.getIFormBrowser().setVisible(false);

        this.addComponent(iToolbar.getToolbarLayout());
        this.addComponent(iBrowser.getBrowserLayout());
    }

    private void buildComponents() {
        this.removeAllComponents();
        //Inicializar sus componentes y poner visible el que sea
        iBrowser.buildBrowser();
        iToolbar.buildToolbar(iBrowser.getBrowserLayout());
        iToolbar.buildToolbar();
    }

    private Layout createToolbar(StateType stateType) {
        iToolbar.setButtonsInvisible();
        iToolbar.setButtonVisibilityByState(stateType);
        return iToolbar.getToolbarLayout();
    }

    private void setTableSyle(VerticalLayout layout) {
        layout.setSizeFull();
        layout.setMargin(true);
    }

    public IBrowser getiBrowser() {
        return iBrowser;
    }
}

