package es.mhp.browser.impl.tag.presenter;

import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.presenter.AbstractBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.ITagService;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.services.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 30/03/2016.
 */

@Component
@Scope("session")
public class TagBrowserPresenter extends AbstractBrowserPresenter {

    @Autowired
    private ITagService tagService;

    @Override
    public void deleteItemAndUpdateGrid(IGridBrowser gridBrowser) throws UIException {
        try {
            tagService.delete(((CategoryDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err) {
            throw new UIException("Error deleting tag entry", err);
        }
    }

    @Override
    public boolean saveItemAndUpdateGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) throws UIException {
        if (formBrowser.isModified()) {
            formBrowser.commit();
            TagDTO tagDTO = (TagDTO) formBrowser.extractBean();
            TagDTO tagDTOUpdated = tagService.save(tagDTO);
            gridBrowser.updateGrid(tagDTOUpdated);
            displayGridAndHideForm(formBrowser, gridBrowser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateAndDisplayGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        updateAndDisplayGrid(formBrowser, gridBrowser, tagService.findAll());
    }
}
