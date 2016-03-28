package es.mhp.browser.impl.address.presenter;

import com.vaadin.data.fieldgroup.FieldGroup;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
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
public class AddressBrowserPresenter {

    @Autowired
    private IAddressService addressService;

    public void updateAndDisplayGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser){
        gridBrowser.updateGrid(addressService.findAll());
        displayGridAndHideForm(formBrowser, gridBrowser);
    }

    public boolean saveItemAndUpdateGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) throws UIException {
        try {
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
        } catch (FieldGroup.CommitException e) {
            throw new UIException("Error! Entity cannot been saved.", e);
        }
    }

    public void deleteItemAndUpdateGrid(IGridBrowser gridBrowser) throws UIException {
        try{
            addressService.delete(((AddressDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err){
            throw new UIException("Error deleting entry", err);
        }
    }

    public void displayGridAndHideForm(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        ((AbstractFormBrowser)formBrowser).setVisible(false);
        ((AbstractGridBrowser)gridBrowser).setVisible(true);
    }

    public void displayFormAndHideGrid(IFormBrowser formBrowser, IGridBrowser gridBrowser) {
        ((AbstractGridBrowser)gridBrowser).setVisible(false);
        ((AbstractFormBrowser)formBrowser).setVisible(true);
    }
}
