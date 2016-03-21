package es.mhp.browser.impl.category;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.browser.utils.StateType;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.views.AbstractView;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static es.mhp.views.utils.CategoryViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(CategoryGridBrowser.BEAN_NAME)
public class CategoryGridBrowser extends AbstractGridBrowser {

    public static final String BEAN_NAME = "category_grid_browser";

    private Grid grid;

    public CategoryGridBrowser() {
        super();
        grid = new Grid();
        this.addComponent(grid);
        /*//Ask Isa if this way to create the grid is correct
        setColumnProperties();*/
    }

    @Override
    public void addDoubleClickListenerToGrid() {
        grid.addItemClickListener((ItemClickEvent.ItemClickListener) event -> {
            if (event.isDoubleClick()){
                ((AbstractView)this.getParent().getParent()).updateToolbar(StateType.EDIT);
                BeanItem<CategoryDTO> categoryBeanItem = (BeanItem<CategoryDTO>) event.getItem();
                ((CategoryBrowser)this.getParent()).createForm(categoryBeanItem.getBean(), FormBrowserUtils.EDIT_MODE);
            } else {
                ((AbstractView)this.getParent().getParent()).updateToolbar(StateType.SELECTEDROW);
                ((CategoryBrowser)this.getParent()).displayGridAndHideForm();
            }
        });
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        grid.removeAllColumns();

        BeanItemContainer<CategoryDTO> categoryBeanItemContainer = new BeanItemContainer<>(CategoryDTO.class, (Collection<? extends CategoryDTO>) newDataSource);
        grid.setContainerDataSource(categoryBeanItemContainer);
        setColumnProperties();

        this.addComponent(grid);
    }

    @Override
    protected void setColumnProperties() {
        grid.setColumnOrder(CATEGORY_ID, NAME, DESCRIPTION, IMAGE_URL);
        grid.setWidth("60%");
    }

    @Override
    public CategoryDTO getSelectedGridRow(){
        return (CategoryDTO) grid.getSelectedRow();
    }

    @Override
    public void deleteEntry() {

    }

    public void deleteEntry(Object id) {
        grid.getContainerDataSource().removeItem(grid.getSelectedRow());
    }

    @Override
    public void updateGrid() {
        this.removeAllComponents();
        this.addComponent(grid);
    }

    @Override
    public void updateAndDisplayGrid(AbstractDTO id) {

    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
