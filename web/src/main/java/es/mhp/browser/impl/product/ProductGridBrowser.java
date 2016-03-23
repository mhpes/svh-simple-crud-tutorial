package es.mhp.browser.impl.product;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ProductDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static es.mhp.views.utils.ProductViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ProductGridBrowser.BEAN_NAME)
@Scope("prototype")
public class ProductGridBrowser extends AbstractGridBrowser {

    public static final String BEAN_NAME = "product_grid_browser";

    private Grid grid;

    public ProductGridBrowser() {
        super();
        grid = new Grid();
        this.addComponent(grid);
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        grid.removeAllColumns();

        BeanItemContainer<ProductDTO> productBeanItemContainer = new BeanItemContainer<>(ProductDTO.class, (Collection<? extends ProductDTO>) newDataSource);
        grid.setContainerDataSource(productBeanItemContainer);
        setColumnProperties();

        this.addComponent(grid);
    }

    @Override
    protected void setColumnProperties() {
        grid.removeColumn(PRODUCTID_FIELD);
        grid.removeColumn(CATEGORY_FIELD);
        grid.setColumnOrder(NAME_FIELD, DESCRIPTION_FIELD, IMAGEURL_FIELD);
        grid.setWidth("60%");
    }

    @Override
    public ProductDTO getSelectedGridRow(){
        return (ProductDTO) grid.getSelectedRow();
    }

    @Override
    public void deleteEntry() {
        if (grid.getSelectedRow() != null) {
            grid.getContainerDataSource().removeItem(grid.getSelectedRow());
        }
    }

    @Override
    public void updateGrid() {
        this.removeAllComponents();
        this.addComponent(grid);
    }

    @Override
    public void updateGrid(AbstractDTO dto) {
        if (grid.getContainerDataSource().containsId(dto)) {
            grid.getContainerDataSource().removeItem(dto);
        }
        grid.getContainerDataSource().addItem(dto);
        grid.select(dto);
    }

    @Override
    protected Grid getGrid() {
        return grid;
    }
}
