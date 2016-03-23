package es.mhp.browser.impl.tag;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.exceptions.UIException;
import es.mhp.services.ITagService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.TagDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(TagBrowser.BEAN_NAME)
public class TagBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "tag_browser";

    @Autowired
    @Qualifier(TagGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(TagFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    @Autowired
    private ITagService tagService;

    /*@Autowired
    private IBrowserNotification browserNotification;*/

    public TagBrowser() {
    }

    @Override
    public void buildBrowser() {
        gridBrowser.updateGrid(tagService.findAll());
        gridBrowser.addDoubleClickListenerToGrid();

        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        displayGridAndHideForm();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        displayFormAndHideGrid();
        formBrowser.createFormBrowser(gridBrowser.getSelectedGridRow(), mode);
    }

    @Override
    public boolean saveItemAndUpdateGrid() throws UIException {
        try {
            if (formBrowser.isModified()) {
                formBrowser.commit();
                TagDTO tagDTO = (TagDTO) formBrowser.extractBean();
                tagService.save(tagDTO);
                gridBrowser.updateAndDisplayGrid(tagDTO);
                return true;
            } else {
                return false;
            }
        } catch (FieldGroup.CommitException e) {
            throw new UIException("Error! Entity cannot been saved.", e);
        }
    }

    @Override
    public void updateGrid(Set<AbstractDTO> newDataSource) {
        gridBrowser.updateGrid(newDataSource);
        ((AbstractView)this.getParent()).updateToolbar(StateType.INITIAL);
    }

    @Override
    public void deleteItemAndUpdateGrid() throws UIException {
        try{
            tagService.delete(((TagDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err){
            throw new UIException("Error deleting entry", err);
        }
    }

    @Override
    public void displayGridAndHideForm() {
        ((AbstractFormBrowser)formBrowser).setVisible(false);
        ((AbstractGridBrowser)gridBrowser).setVisible(true);
    }

    @Override
    public void displayFormAndHideGrid() {
        ((AbstractGridBrowser)gridBrowser).setVisible(false);
        ((AbstractFormBrowser)formBrowser).setVisible(true);
    }
}
