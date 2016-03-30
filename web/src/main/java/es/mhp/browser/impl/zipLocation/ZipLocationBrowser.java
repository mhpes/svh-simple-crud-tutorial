package es.mhp.browser.impl.ziplocation;

import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.impl.ziplocation.presenter.ZipLocationBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.dto.AbstractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(ZipLocationBrowser.BEAN_NAME)
@Scope("prototype")
public class ZipLocationBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "zipLocation_browser";

    @Autowired
    @Qualifier(ZipLocationGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(ZipLocationFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    @Autowired
    private ZipLocationBrowserPresenter zipLocationBrowserPresenter;

    public ZipLocationBrowser() {
        super();
    }

    @Override
    public void buildBrowser() {
        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        zipLocationBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser);
        gridBrowser.configure();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        zipLocationBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
        formBrowser.createFormBrowser(gridBrowser.getSelectedGridRow(), mode);
    }

    @Override
    public boolean saveItemAndUpdateGrid() throws UIException {
        return zipLocationBrowserPresenter.saveItemAndUpdateGrid(formBrowser, gridBrowser);
    }

    @Override
    public void deleteItemAndUpdateGrid() throws UIException {
        zipLocationBrowserPresenter.deleteItemAndUpdateGrid(gridBrowser);
    }

    @Override
    public void updateAndDisplayGrid(Set<AbstractDTO> dataSource) {
        zipLocationBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser, dataSource);
    }

    @Override
    public void displayGridAndHideForm() {
        zipLocationBrowserPresenter.displayGridAndHideForm(formBrowser, gridBrowser);
    }

    @Override
    public void displayFormAndHideGrid() {
        zipLocationBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
    }
}
