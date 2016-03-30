package es.mhp.browser.impl.item;

import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.impl.item.presenter.ItemBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.dto.AbstractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(ItemBrowser.BEAN_NAME)
@Scope("prototype")
public class ItemBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "item_browser";

    @Autowired
    @Qualifier(ItemGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(ItemFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    @Autowired
    private ItemBrowserPresenter itemBrowserPresenter;

    public ItemBrowser() {
        super();
    }

    @Override
    public void buildBrowser() {
        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        itemBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser);
        gridBrowser.configure();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        itemBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
        formBrowser.createFormBrowser(gridBrowser.getSelectedGridRow(), mode);
    }

    @Override
    public boolean saveItemAndUpdateGrid() throws UIException {
        return itemBrowserPresenter.saveItemAndUpdateGrid(formBrowser, gridBrowser);
    }

    @Override
    public void deleteItemAndUpdateGrid() throws UIException {
        itemBrowserPresenter.deleteItemAndUpdateGrid(gridBrowser);
    }

    @Override
    public void updateAndDisplayGrid(Set<AbstractDTO> dataSource) {
        itemBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser, dataSource);
    }

    @Override
    public void displayGridAndHideForm() {
        itemBrowserPresenter.displayGridAndHideForm(formBrowser, gridBrowser);
    }

    @Override
    public void displayFormAndHideGrid() {
        itemBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
    }
}
