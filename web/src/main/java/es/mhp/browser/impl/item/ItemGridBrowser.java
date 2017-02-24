package es.mhp.browser.impl.item;

import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ItemDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static es.mhp.views.utils.ItemViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ItemGridBrowser.BEAN_NAME)
@Scope("prototype")
public class ItemGridBrowser extends AbstractGridBrowser {

    public static final String BEAN_NAME = "item_grid_browser";

    public ItemGridBrowser() {
        super();
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        List<Grid.Column> columnList = grid.getColumns();
        columnList.stream().peek(d -> d.remove());

        grid = new Grid(ItemDTO.class);
        grid.setItems(newDataSource);
        setColumnProperties();

        this.addComponent(grid);
    }

    @Override
    protected void setColumnProperties() {
        grid.removeColumn(ITEMID_FIELD);
        grid.removeColumn(PRODUCT_FIELD);
        grid.removeColumn(ADDRESS_FIELD);
        grid.removeColumn(SELLERCONTACTINFO_FIELD);
        grid.removeColumn(TOTALSCORE_FIELD);
        grid.removeColumn(NUMBEROFVOTES_FIELD);
        grid.removeColumn(DISABLED_FIELD);
        grid.setColumnOrder(NAME_FIELD, DESCRIPTION_FIELD, PRICE_FIELD, IMAGEURL_FIELD, PRODUCTSUMMARY_FIELD, ADDRESSSUMMARY_FIELD, SELLERCONTACTSUMMARY_FIELD);
        grid.setWidth("60%");
    }
}
