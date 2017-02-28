package es.mhp.browser.impl.sellercontactinfo.presenter;

import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.presenter.AbstractBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 30/03/2016.
 */

@Component
@Scope("session")
public class SellerContactInfoBrowserPresenter extends AbstractBrowserPresenter {

    @Autowired
    private ISellerContactInfoService sellerContactInfoService;

    @Override
    public void deleteItemAndUpdateGrid(IGridBrowser gridBrowser) throws UIException {
        try {
            sellerContactInfoService.delete(((CategoryDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err) {
            throw new UIException("Error deleting category entry", err);
        }
    }

    @Override
    public boolean saveItemAndUpdateGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) throws UIException {
        if (formBrowser.isModified()) {
            formBrowser.commit();
            SellerContactInfoDTO sellerContactInfoDTO = (SellerContactInfoDTO) formBrowser.extractBean();
            SellerContactInfoDTO sellerContactInfoDTOUpdated = sellerContactInfoService.save(sellerContactInfoDTO);
            gridBrowser.updateGrid(sellerContactInfoDTOUpdated);
            displayGridAndHideForm(formBrowser, gridBrowser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateAndDisplayGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        updateAndDisplayGrid(formBrowser, gridBrowser, sellerContactInfoService.findAll());
    }
}
