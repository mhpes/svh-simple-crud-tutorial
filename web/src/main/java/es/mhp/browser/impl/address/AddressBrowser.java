package es.mhp.browser.impl.address;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.exceptions.UIException;
import es.mhp.services.IAddressService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(AddressBrowser.BEAN_NAME)
public class AddressBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "address_browser";

    @Autowired
    @Qualifier(AddressGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(AddressFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    @Autowired
    private IAddressService addressService;

    public AddressBrowser() {
    }

    @Override
    public void buildBrowser() {
        gridBrowser.updateGrid(addressService.findAll());
        gridBrowser.addDoubleClickListenerToGrid();

        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        displayGridAndHideForm();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        displayFormAndHideGrid();
        formBrowser.createFormBrowser((AbstractDTO) gridBrowser.getSelectedGridRow(), mode);
    }

    @Override
    public boolean saveItemAndUpdateGrid() throws UIException {
        try {
            if (formBrowser.isModified()) {
                formBrowser.commit();
                AddressDTO addresDto = (AddressDTO) formBrowser.extractBean();
                addressService.save(addresDto);
                gridBrowser.updateAndDisplayGrid(addresDto);
                return true;
            } else {
                return false;
            }
        } catch (FieldGroup.CommitException e) {
            throw new UIException("Error! Entity cannot been saved.", e);
        }
    }

    @Override
    public void updateGrid(Set<AbstractDTO> newDataSource) {
        gridBrowser.updateGrid(newDataSource);
        displayGridAndHideForm();
    }

    @Override
    public void deleteItemAndUpdateGrid() throws UIException {
        try{
            addressService.delete(((AddressDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err){
            throw new UIException("Error deleting entry", err);
        }
    }

    @Override
    public void displayGridAndHideForm() {
        ((AbstractFormBrowser)formBrowser).setVisible(false);
        ((AbstractGridBrowser)gridBrowser).setVisible(true);
    }

    @Override
    public void displayFormAndHideGrid() {
        ((AbstractGridBrowser)gridBrowser).setVisible(false);
        ((AbstractFormBrowser)formBrowser).setVisible(true);
    }
}