package es.mhp.browser.impl.address;

import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static es.mhp.views.utils.AddressViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(AddressGridBrowser.BEAN_NAME)
@Scope("prototype")
public class AddressGridBrowser extends AbstractGridBrowser<AddressDTO> {

    public static final String BEAN_NAME = "address_grid_browser";

    public AddressGridBrowser() {
        super();
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        List<Grid.Column> columnList = grid.getColumns();
        columnList.stream().peek(d -> d.remove());

        grid = new Grid(AddressDTO.class);
        grid.setItems(newDataSource);
        setColumnProperties();

        this.addComponent(grid);
    }

    @Override
    protected void setColumnProperties() {
        grid.setColumnOrder(MAIN_STREET_FIELD, SECONDARY_STREET_FIELD, CITY_FIELD, STATE_FIELD, LATITUDE_FIELD, LONGITUDE_FIELD);
        grid.removeColumn(ADDRESSID_FIELD);
        grid.removeColumn(ZIPLOCATION_FIELD);
        grid.removeColumn(ASSOCIATED_ITEMS_COUNT);
        grid.setWidth("60%");
    }
}
