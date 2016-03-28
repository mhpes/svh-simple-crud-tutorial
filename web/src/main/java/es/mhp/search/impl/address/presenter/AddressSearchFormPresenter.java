package es.mhp.search.impl.address.presenter;

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

    public void fillStatesComboBox(ComboBox stateComboBox) {
        stateComboBox.addItems(addressService.getStateList());
    }
}