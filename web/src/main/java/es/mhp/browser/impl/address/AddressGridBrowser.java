package es.mhp.browser.impl.address;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Grid;
import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.browser.utils.StateType;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import es.mhp.views.AbstractView;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

import static es.mhp.views.utils.AddressViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(AddressGridBrowser.BEAN_NAME)
public class AddressGridBrowser extends AbstractGridBrowser {

    public static final String BEAN_NAME = "address_grid_browser";

    private Grid grid;

    public AddressGridBrowser() {
        grid = new Grid();
        this.addComponent(grid);
    }

    @Override
    public void addDoubleClickListenerToGrid(IBrowser browser) {
        grid.addItemClickListener((ItemClickEvent.ItemClickListener) event -> {
            if (event.isDoubleClick()){
                ((AbstractView)this.getParent().getParent()).updateToolbar(StateType.EDIT);
                BeanItem<AddressDTO> addressBeanItem = (BeanItem<AddressDTO>) event.getItem();
                ((AddressBrowser)this.getParent()).createForm(addressBeanItem.getBean(), FormBrowserUtils.EDIT_MODE);
            } else {
                ((AbstractView)this.getParent().getParent()).updateToolbar(StateType.SELECTEDROW);
                ((AddressBrowser)this.getParent()).displayGridAndHideForm();
            }
        });
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        this.removeAllComponents();

        BeanItemContainer<AddressDTO> addressBeanItemContainer = new BeanItemContainer<>(AddressDTO.class, (Collection<? extends AddressDTO>) newDataSource);
        addressBeanItemContainer.removeItem(ITEM_COUNT);

        grid.setContainerDataSource(addressBeanItemContainer);
        grid.setColumnOrder(MAIN_STREET_FIELD, SECONDARY_STREET_FIELD, CITY_FIELD, STATE_FIELD, LATITUDE_FIELD, LONGITUDE_FIELD);
        grid.removeColumn(ADDRESSID_FIELD);
        grid.removeColumn(ZIPLOCATION_FIELD);
        grid.setWidth("60%");
    }
}
