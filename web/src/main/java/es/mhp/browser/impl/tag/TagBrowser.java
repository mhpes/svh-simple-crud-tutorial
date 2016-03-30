package es.mhp.browser.impl.tag;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.impl.tag.presenter.TagBrowserPresenter;
import es.mhp.browser.utils.StateType;
import es.mhp.exceptions.UIException;
import es.mhp.services.ITagService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.TagDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(TagBrowser.BEAN_NAME)
@Scope("prototype")
public class TagBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "tag_browser";

    @Autowired
    @Qualifier(TagGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(TagFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    public TagBrowser() {
        super();
    }

    @Autowired
    private TagBrowserPresenter tagBrowserPresenter;

    @Override
    public void buildBrowser() {
        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        tagBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser);
        gridBrowser.configure();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        tagBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
        formBrowser.createFormBrowser(gridBrowser.getSelectedGridRow(), mode);
    }

    @Override
    public boolean saveItemAndUpdateGrid() throws UIException {
        return tagBrowserPresenter.saveItemAndUpdateGrid(formBrowser, gridBrowser);
    }

    @Override
    public void deleteItemAndUpdateGrid() throws UIException {
        tagBrowserPresenter.deleteItemAndUpdateGrid(gridBrowser);
    }

    @Override
    public void updateAndDisplayGrid(Set<AbstractDTO> dataSource) {
        tagBrowserPresenter.updateAndDisplayGrid(formBrowser, gridBrowser, dataSource);
    }

    @Override
    public void displayGridAndHideForm() {
        tagBrowserPresenter.displayGridAndHideForm(formBrowser, gridBrowser);
    }

    @Override
    public void displayFormAndHideGrid() {
        tagBrowserPresenter.displayFormAndHideGrid(formBrowser, gridBrowser);
    }
}
