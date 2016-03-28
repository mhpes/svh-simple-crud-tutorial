package es.mhp.browser.impl.item;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.exceptions.UIException;
import es.mhp.services.IItemService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ItemDTO;
import es.mhp.views.AbstractView;
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
    private IItemService itemService;

    public ItemBrowser() {
        super();
    }

    @Override
    public void buildBrowser() {
        gridBrowser.updateGrid(itemService.findAll());
        gridBrowser.configure();

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
                ItemDTO itemDTO = (ItemDTO) formBrowser.extractBean();
                ItemDTO itemDTOUpdated = itemService.save(itemDTO);
                gridBrowser.updateGrid(itemDTOUpdated);
                displayGridAndHideForm();
                return true;
            } else {
                return false;
            }
        } catch (FieldGroup.CommitException e) {
            throw new UIException("Error! Entity cannot been saved.", e);
        }
    }

    @Override
    public void updateAndDisplayGrid(Set<AbstractDTO> newDataSource) {
        gridBrowser.updateGrid(newDataSource);
        ((AbstractView)this.getParent()).updateToolbar(StateType.INITIAL);
    }

    @Override
    public void deleteItemAndUpdateGrid() throws UIException {
        try{
            itemService.delete(((ItemDTO) gridBrowser.getSelectedGridRow()).getId());
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
