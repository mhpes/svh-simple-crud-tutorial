package es.mhp.browser.impl.address;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static es.mhp.views.utils.AddressViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(AddressGridBrowser.BEAN_NAME)
@Scope("prototype")
public class AddressGridBrowser extends AbstractGridBrowser {

    public static final String BEAN_NAME = "address_grid_browser";

    private Grid grid;

    public AddressGridBrowser() {
        super();
        grid = new Grid();
        this.addComponent(grid);
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        grid.removeAllColumns();

        BeanItemContainer<AddressDTO> addressBeanItemContainer = new BeanItemContainer<>(AddressDTO.class, (Collection<? extends AddressDTO>) newDataSource);
        grid.setContainerDataSource(addressBeanItemContainer);
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

    @Override
    public AddressDTO getSelectedGridRow(){
        return (AddressDTO) grid.getSelectedRow();
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
