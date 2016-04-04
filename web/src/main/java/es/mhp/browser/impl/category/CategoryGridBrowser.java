package es.mhp.browser.impl.category;

import com.vaadin.data.util.BeanItemContainer;
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
        grid.removeAllColumns();

        BeanItemContainer<CategoryDTO> categoryBeanItemContainer = new BeanItemContainer<>(CategoryDTO.class, (Collection<? extends CategoryDTO>) newDataSource);
        grid.setContainerDataSource(categoryBeanItemContainer);
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
