package es.mhp.browser.impl.category;

import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.CategoryDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static es.mhp.views.utils.CategoryViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(CategoryGridBrowser.BEAN_NAME)
@Scope("prototype")
public class CategoryGridBrowser extends AbstractGridBrowser<CategoryDTO> {

    public static final String BEAN_NAME = "category_grid_browser";

    public CategoryGridBrowser() {
        super();
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        this.removeComponent(grid);
        grid = new Grid<>(CategoryDTO.class);
        grid.setItems(newDataSource);
        setColumnProperties();

        this.addComponent(grid);
    }

    @Override
    protected void setColumnProperties() {
        grid.removeColumn(ASSOCIATEDPRODUCTSCOUNT_FIELD);
        grid.setColumnOrder(CATEGORYID_FIELD, NAME_FIELD, DESCRIPTION_FIELD, IMAGEURL_FIELD);
        grid.setWidth("60%");
    }
}
