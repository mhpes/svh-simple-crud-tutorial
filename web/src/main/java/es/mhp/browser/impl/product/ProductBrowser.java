package es.mhp.browser.impl.product;

import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.impl.product.presenter.ProductBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.dto.AbstractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(ProductBrowser.BEAN_NAME)
@Scope("prototype")
public class ProductBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "product_browser";

    @Autowired
    @Qualifier(ProductGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(ProductFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    @Autowired
    private ProductBrowserPresenter productBrowserPresenter;

    public ProductBrowser() {
        super();
    }

    @Override
    public void buildBrowser() {
        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        productBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser);
        gridBrowser.configure();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        productBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
        formBrowser.createFormBrowser(gridBrowser.getSelectedGridRow(), mode);
    }

    @Override
    public boolean saveItemAndUpdateGrid() throws UIException {
        return productBrowserPresenter.saveItemAndUpdateGrid(formBrowser, gridBrowser);
    }

    @Override
    public void deleteItemAndUpdateGrid() throws UIException {
        productBrowserPresenter.deleteItemAndUpdateGrid(gridBrowser);
    }

    @Override
    public void updateAndDisplayGrid(Set<AbstractDTO> dataSource) {
        productBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser, dataSource);
    }

    @Override
    public void displayGridAndHideForm() {
        productBrowserPresenter.displayGridAndHideForm(formBrowser, gridBrowser);
    }

    @Override
    public void displayFormAndHideGrid() {
        productBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
    }
}
