package es.mhp.browser.impl.sellercontactinfo;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.impl.item.presenter.ItemBrowserPresenter;
import es.mhp.browser.impl.sellercontactinfo.presenter.SellerContactInfoBrowserPresenter;
import es.mhp.browser.utils.StateType;
import es.mhp.exceptions.UIException;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.SellerContactInfoDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(SellerContactInfoBrowser.BEAN_NAME)
@Scope("prototype")
public class SellerContactInfoBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "sellerContactInfo_browser";

    @Autowired
    @Qualifier(SellerContactInfoGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(SellerContactInfoFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    @Autowired
    private SellerContactInfoBrowserPresenter sellerContactInfoBrowserPresenter;

    public SellerContactInfoBrowser() {
        super();
    }

    @Override
    public void buildBrowser() {
        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        sellerContactInfoBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser);
        gridBrowser.configure();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        sellerContactInfoBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
        formBrowser.createFormBrowser(gridBrowser.getSelectedGridRow(), mode);
    }

    @Override
    public boolean saveItemAndUpdateGrid() throws UIException {
        return sellerContactInfoBrowserPresenter.saveItemAndUpdateGrid(formBrowser, gridBrowser);
    }

    @Override
    public void deleteItemAndUpdateGrid() throws UIException {
        sellerContactInfoBrowserPresenter.deleteItemAndUpdateGrid(gridBrowser);
    }

    @Override
    public void updateAndDisplayGrid(Set<AbstractDTO> dataSource) {
        sellerContactInfoBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser, dataSource);
    }

    @Override
    public void displayGridAndHideForm() {
        sellerContactInfoBrowserPresenter.displayGridAndHideForm(formBrowser, gridBrowser);
    }

    @Override
    public void displayFormAndHideGrid() {
        sellerContactInfoBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
    }
}
