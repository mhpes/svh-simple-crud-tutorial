package es.mhp.browser.impl.sellercontactinfo.presenter;

import com.vaadin.data.fieldgroup.FieldGroup;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.presenter.AbstractBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.CategoryDTO;
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
    private ICategoryService categoryService;

    @Override
    public void deleteItemAndUpdateGrid(IGridBrowser gridBrowser) throws UIException {
        try{
            categoryService.delete(((CategoryDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err){
            throw new UIException("Error deleting category entry", err);
        }
    }

    @Override
    public boolean saveItemAndUpdateGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) throws UIException {
        try {
            if (formBrowser.isModified()) {
                formBrowser.commit();
                CategoryDTO categoryDTO = (CategoryDTO) formBrowser.extractBean();
                CategoryDTO addressDTOUpdated = categoryService.save(categoryDTO);
                gridBrowser.updateGrid(addressDTOUpdated);
                displayGridAndHideForm(formBrowser, gridBrowser);
                return true;
            } else {
                return false;
            }
        } catch (FieldGroup.CommitException e) {
            throw new UIException("Error! Category entity cannot been saved.", e);
        }
    }

    @Override
    public void updateAndDisplayGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        updateAndDisplayGrid(formBrowser, gridBrowser, categoryService.findAll());
    }
}
