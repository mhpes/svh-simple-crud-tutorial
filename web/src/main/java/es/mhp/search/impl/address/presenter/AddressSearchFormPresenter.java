package es.mhp.search.impl.address.presenter;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.address.AddressSearchForm;
import es.mhp.services.IAddressService;
import es.mhp.services.dto.AddressDTO;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static es.mhp.views.utils.AddressViewConstants.*;

/**
 * Created by Edu on 23/03/2016.
 */
@Component
@Scope("session")
public class AddressSearchFormPresenter {

    @Autowired
    private IAddressService addressService;

    public void buildSearchForm(IBrowser browser, IToolbar toolbar, AddressSearchForm addressSearchForm) {
        //browser.updateAndDisplayGrid(addressService.findAll());

        addressSearchForm.getBrowserButton().addClickListener(e -> {
            browser.buildBrowser();
            toolbar.updateToolbar(StateType.INITIAL);
            AddressDTO addressDTO;

            String mainStreet = addressSearchForm.getMainStreetTextField().getValue();
            String secondaryString = addressSearchForm.getSecondaryStreetTextField().getValue();
            String city = addressSearchForm.getCityComboBox().getValue().toString();
            String state = addressSearchForm.getStateComboBox().getValue().toString();
            String browserWay = addressSearchForm.getBrowserWayOptionGroup().getValue().toString();

            if (addressSearchForm.getStateComboBox().getValue() == null)
                addressDTO = new AddressDTO(mainStreet, secondaryString, city);
            else {
                addressDTO = new AddressDTO(mainStreet, secondaryString, city, state);
            }

            /*if (ALL.equals(browserWay)){
                browser.updateAndDisplayGrid(addressService.findAllAddresses(addressDTO));
            }
            else if (ANY.equals(browserWay)){
                browser.updateAndDisplayGrid(addressService.findAnyAddresses(addressDTO));
            }*/
        });
    }

    public void fillCitiesComboBox(ComboBox cityComboBox) {
        cityComboBox.addItems(cityList);
        cityComboBox.select("Stanford");
        cityComboBox.setNullSelectionAllowed(false);
    }

    public void fillStatesComboBoxOrderer(ComboBox stateComboBox) {
        IndexedContainer stateContainer = new IndexedContainer();
        stateContainer.addContainerProperty("name", String.class, null);
        List stateList = new ArrayList<>(addressService.getStateList());

        for (int i = 0; i < stateList.size(); i++){
            stateContainer.addItem(i).getItemProperty("name").setValue(stateList.get(i));
        }
        stateContainer.sort(new Object[]{"name"}, new boolean[]{false});
        stateComboBox.addItems(stateContainer);
    }

    public IAddressService getAddressService() {
        return addressService;
    }

    public void setAddressService(IAddressService addressService) {
        this.addressService = addressService;
    }
}
