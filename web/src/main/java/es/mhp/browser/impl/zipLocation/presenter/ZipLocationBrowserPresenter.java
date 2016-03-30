package es.mhp.browser.impl.ziplocation.presenter;

import com.vaadin.data.fieldgroup.FieldGroup;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.presenter.AbstractBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 30/03/2016.
 */

@Component
@Scope("session")
public class ZipLocationBrowserPresenter extends AbstractBrowserPresenter {

    @Autowired
    private IZipLocationService zipLocationService;

    @Override
    public void deleteItemAndUpdateGrid(IGridBrowser gridBrowser) throws UIException {
        try{
            zipLocationService.delete(((CategoryDTO) gridBrowser.getSelectedGridRow()).getId());
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
                ZipLocationDTO zipLocationDTO = (ZipLocationDTO) formBrowser.extractBean();
                ZipLocationDTO zipLocationDTOUpdated = zipLocationService.save(zipLocationDTO);
                gridBrowser.updateGrid(zipLocationDTOUpdated);
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
        updateAndDisplayGrid(formBrowser, gridBrowser, zipLocationService.findAll());
    }
}
