package es.mhp.search.impl.address.presenter;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import es.mhp.browser.IBrowser;
import es.mhp.search.impl.address.AddressSearchForm;
import es.mhp.search.impl.presenter.AbstractSearchFormPresenter;
import es.mhp.services.IAddressService;
import es.mhp.services.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.AddressViewConstants.cityList;

/**
 * Created by Edu on 23/03/2016.
 */
@Component
@Scope("session")
public class AddressSearchFormPresenter extends AbstractSearchFormPresenter {

    @Autowired
    private IAddressService addressService;

    public Button.ClickListener createBrowserButtonListener(IBrowser browser, AddressSearchForm addressSearchForm) {
        return (Button.ClickListener) event -> {
            browser.buildBrowser();
            AddressDTO addressDTO;

            String mainStreet = addressSearchForm.getMainStreetTextField().getValue();
            String secondaryString = addressSearchForm.getSecondaryStreetTextField().getValue();
            String city = addressSearchForm.getCityComboBox().getValue().toString();
            /*String browserWay = addressSearchForm.getBrowserWayOptionGroup().getValue().toString();*/
            Object state = addressSearchForm.getStateComboBox().getValue();

            if (state == null)
                addressDTO = new AddressDTO(mainStreet, secondaryString, city);
            else {
                addressDTO = new AddressDTO(mainStreet, secondaryString, city, state.toString());
            }

            if (Boolean.TRUE){
                browser.updateAndDisplayGrid(addressService.findAllAddresses(addressDTO));
            }/*
            else if (ANY.equals(browserWay)){
                browser.updateAndDisplayGrid(addressService.findAnyAddresses(addressDTO));
            }*/
        };
    }

    public void updateAndDisplayGrid(IBrowser browser){
        browser.updateAndDisplayGrid(addressService.findAll());
    }

    public void fillCitiesComboBox(ComboBox cityComboBox) {
        cityComboBox.setItems(cityList);
        cityComboBox.setValue("Stanford");
    }

    /*public void fillStatesComboBoxOrderer(ComboBox stateComboBox) {
        IndexedContainer stateContainer = new IndexedContainer();
        stateContainer.addContainerProperty("name", String.class, null);
        List stateList = new ArrayList<>(addressService.findStates());

        for (int i = 0; i < stateList.size(); i++){
            stateContainer.addItem(i).getItemProperty("name").setValue(stateList.get(i));
        }
        stateContainer.sort(new Object[]{"name"}, new boolean[]{false});
        stateComboBox.addItems(addressService.findStates());
    }*/
}
