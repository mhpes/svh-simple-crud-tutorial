package es.mhp.browser.impl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IGridBrowser;
import es.mhp.services.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

import static es.mhp.views.utils.AddressViewUtils.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(AddressGridBrowser.BEAN_NAME)
public class AddressGridBrowser extends AbstractGridBrowser<AddressDTO> {

    public static final String BEAN_NAME = "address_grid_browser";

    @Override
    public FormLayout createGridBrowser() {
            return null;
    }

    @Override
    protected Grid fillGrid(Set <AddressDTO> addressDTOs) {
        iBrowser.getAddressGridBrowser().removeAllComponents();

        BeanItemContainer<AddressDTO> addressBeanItemContainer = new BeanItemContainer<>(AddressDTO.class, addressDTOs);

        addressBeanItemContainer.removeItem(ITEM_COUNT);
        Grid grid = new Grid(addressBeanItemContainer);
        grid.setColumnOrder(MAIN_STREET_FIELD, SECONDARY_STREET_FIELD, CITY_FIELD, STATE_FIELD, LATITUDE_FIELD, LONGITUDE_FIELD);
        grid.removeColumn(ADDRESSID_FIELD);
        grid.removeColumn(ZIPLOCATION_FIELD);
        grid.setWidth("60%");

        VerticalLayout formContainer = createAddressForm(grid);

        return grid;
    }
}
