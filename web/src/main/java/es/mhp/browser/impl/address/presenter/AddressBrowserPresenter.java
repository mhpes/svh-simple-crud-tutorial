package es.mhp.browser.impl.address.presenter;

import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.presenter.AbstractBrowserPresenter;
import es.mhp.exceptions.UIException;
import es.mhp.services.IAddressService;
import es.mhp.services.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 28/03/2016.
 */

@Component
@Scope("session")
public class AddressBrowserPresenter extends AbstractBrowserPresenter {

    @Autowired
    private IAddressService addressService;

    @Override
    public boolean saveItemAndUpdateGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) throws UIException {
        if (formBrowser.isModified()) {
            formBrowser.commit();
            AddressDTO addresDto = (AddressDTO) formBrowser.extractBean();
            AddressDTO addressDTOUpdated = addressService.save(addresDto);
            gridBrowser.updateGrid(addressDTOUpdated);
            displayGridAndHideForm(formBrowser, gridBrowser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteItemAndUpdateGrid(IGridBrowser gridBrowser) throws UIException {
        try {
            addressService.delete(((AddressDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err) {
            throw new UIException("Error deleting entry", err);
        }
    }

    @Override
    public void updateAndDisplayGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        updateAndDisplayGrid(formBrowser, gridBrowser, addressService.findAll());
    }
}
