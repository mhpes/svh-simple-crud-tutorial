package es.mhp.browser.impl.item.presenter;

import com.vaadin.data.fieldgroup.FieldGroup;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.presenter.AbstractBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.IItemService;
import es.mhp.services.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 30/03/2016.
 */

@Component
@Scope("session")
public class ItemBrowserPresenter extends AbstractBrowserPresenter {

    @Autowired
    private IItemService itemService;

    @Override
    public void deleteItemAndUpdateGrid(IGridBrowser gridBrowser) throws UIException {
        try{
            itemService.delete(((ItemDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err){
            throw new UIException("Error deleting item entry", err);
        }
    }

    @Override
    public boolean saveItemAndUpdateGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) throws UIException {
        try {
            if (formBrowser.isModified()) {
                formBrowser.commit();
                ItemDTO itemDTO = (ItemDTO) formBrowser.extractBean();
                ItemDTO addressDTOUpdated = itemService.save(itemDTO);
                gridBrowser.updateGrid(addressDTOUpdated);
                displayGridAndHideForm(formBrowser, gridBrowser);
                return true;
            } else {
                return false;
            }
        } catch (FieldGroup.CommitException e) {
            throw new UIException("Error! Item entity cannot been saved.", e);
        }
    }

    @Override
    public void updateAndDisplayGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        updateAndDisplayGrid(formBrowser, gridBrowser, itemService.findAll());
    }
}
