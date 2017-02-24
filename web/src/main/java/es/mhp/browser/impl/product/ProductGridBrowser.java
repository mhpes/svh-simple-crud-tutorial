package es.mhp.browser.impl.product;

import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ProductDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static es.mhp.views.utils.ProductViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ProductGridBrowser.BEAN_NAME)
@Scope("prototype")
public class ProductGridBrowser extends AbstractGridBrowser<ProductDTO> {

    public static final String BEAN_NAME = "product_grid_browser";

    public ProductGridBrowser() {
        super();
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        List<Grid.Column> columnList = grid.getColumns();
        columnList.stream().peek(d -> d.remove());

        grid = new Grid(ProductDTO.class);
        grid.setItems(newDataSource);
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
}
