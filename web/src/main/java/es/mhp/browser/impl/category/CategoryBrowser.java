package es.mhp.browser.impl.category;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.exceptions.UIException;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

import static com.vaadin.ui.Notification.show;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(CategoryBrowser.BEAN_NAME)
public class CategoryBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "category_browser";

    @Autowired
    @Qualifier(CategoryGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(CategoryFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    @Autowired
    private ICategoryService categoryService;

    /*@Autowired
    private IBrowserNotification browserNotification;*/

    public CategoryBrowser() {
    }

    @Override
    public void buildBrowser() {
        gridBrowser.updateGrid(categoryService.findAll());
        gridBrowser.addDoubleClickListenerToGrid();

        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        displayGridAndHideForm();
    }

    @Override
    public void createAndDisplayForm(String mode) {

    }

    @Override
    public void saveItemAndUpdateGrid() {

    }



    @Override
    public void deleteItemAndUpdateGrid() throws UIException {

    }

    public void createForm(Object id, String mode) {
        displayFormAndHideGrid();
        formBrowser.createFormBrowser(id, mode);
    }


    public void saveFormData(AbstractDTO entityDto) {
        categoryService.save((CategoryDTO) entityDto);
        //browserNotification.showHumanizedNotification("Added!");
        show("Added!", Notification.Type.HUMANIZED_MESSAGE);
        displayGridAndHideForm();
    }

    @Override
    public void updateGrid(Set<AbstractDTO> newDataSource) {
        gridBrowser.updateGrid(newDataSource);
        ((AbstractView)this.getParent()).updateToolbar(StateType.INITIAL);
    }

    @Override
    public AbstractDTO getSelectedFormRow() {
        return formBrowser.getNewForm();
    }

    @Override
    public Object getSelectedGridRow() {
        return gridBrowser.getSelectedGridRow();
    }


    public void deleteFormData(Object id) {
        try{
            categoryService.delete(id);
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
            show("Delete entry", Notification.Type.HUMANIZED_MESSAGE);
        } catch (Exception err){
            show("Error deleting entry", Notification.Type.WARNING_MESSAGE);
        }
    }

    public void displayGridAndHideForm() {
        ((AbstractFormBrowser)formBrowser).setVisible(false);
        ((AbstractGridBrowser)gridBrowser).setVisible(true);
    }

    public void displayFormAndHideGrid() {
        ((AbstractGridBrowser)gridBrowser).setVisible(false);
        ((AbstractFormBrowser)formBrowser).setVisible(true);
    }
}
