package es.mhp.browser.presenter;

import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.exceptions.UIException;
import es.mhp.services.dto.AbstractDTO;

import java.util.Set;

/**
 * Created by Edu on 30/03/2016.
 */
public abstract class AbstractBrowserPresenter {

    public void updateAndDisplayGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser, Set<AbstractDTO> dataSource){
        gridBrowser.updateGrid(dataSource);
        displayGridAndHideForm(formBrowser, gridBrowser);
    }

    public void displayGridAndHideForm(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        ((AbstractFormBrowser)formBrowser).setVisible(false);
        ((AbstractGridBrowser)gridBrowser).setVisible(true);
    }

    public void displayFormAndHideGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        ((AbstractGridBrowser)gridBrowser).setVisible(false);
        ((AbstractFormBrowser)formBrowser).setVisible(true);
    }

    public abstract void deleteItemAndUpdateGrid(IGridBrowser gridBrowser) throws UIException;
    public abstract boolean saveItemAndUpdateGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) throws UIException;
    public abstract void updateAndDisplayGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser);
}
