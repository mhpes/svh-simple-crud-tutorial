package es.mhp.browser.impl.product.presenter;

import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.presenter.AbstractBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.IProductService;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.services.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 30/03/2016.
 */

@Component
@Scope("session")
public class ProductBrowserPresenter extends AbstractBrowserPresenter {

    @Autowired
    private IProductService productService;

    @Override
    public void deleteItemAndUpdateGrid(IGridBrowser gridBrowser) throws UIException {
        try {
            productService.delete(((CategoryDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err) {
            throw new UIException("Error deleting product entry", err);
        }
    }

    @Override
    public boolean saveItemAndUpdateGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) throws UIException {
        if (formBrowser.isModified()) {
            formBrowser.commit();
            ProductDTO productDTO = (ProductDTO) formBrowser.extractBean();
            ProductDTO addressDTOUpdated = productService.save(productDTO);
            gridBrowser.updateGrid(addressDTOUpdated);
            displayGridAndHideForm(formBrowser, gridBrowser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateAndDisplayGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        updateAndDisplayGrid(formBrowser, gridBrowser, productService.findAll());
    }
}
