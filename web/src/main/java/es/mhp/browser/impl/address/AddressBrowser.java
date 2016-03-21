package es.mhp.browser.impl.address;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.IAddressService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

import static com.vaadin.ui.Notification.show;

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

    /*@Autowired
    private IBrowserNotification browserNotification;*/

    public AddressBrowser() {
    }

    @Override
    public void buildBrowser() {
        gridBrowser.updateGrid(addressService.findAll());
        gridBrowser.addDoubleClickListenerToGrid(this);

        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        displayGridAndHideForm();
    }

    @Override
    public void createForm(Object id, String mode) {
        displayFormAndHideGrid();
        formBrowser.createFormBrowser(id, mode);
    }

    @Override
    public void saveFormData(AbstractDTO entityDto) {
        addressService.save((AddressDTO) entityDto);
        //browserNotification.showHumanizedNotification("Added!");
        show("Added!", Notification.Type.HUMANIZED_MESSAGE);
        displayGridAndHideForm();
    }

    @Override
    public void updateGrid(Set<AbstractDTO> newDataSource) {
        gridBrowser.updateGrid(newDataSource);
    }

    @Override
    public AddressDTO getSelectedFormRow() {
        return formBrowser.getNewForm();
    }

    @Override
    public Object getSelectedGridRow() {
        return gridBrowser.getSelectedGridRow();
    }

    @Override
    public void deleteFormData(Object id) {
        try{
            addressService.delete(id);
            gridBrowser.deleteEntry(id);
            gridBrowser.updateGrid();
            show("Delete entry", Notification.Type.HUMANIZED_MESSAGE);
        } catch (Exception err){
            show("Error deleting entry", Notification.Type.WARNING_MESSAGE);
        }
    }

    public void displayGridAndHideForm() {
        ((AbstractFormBrowser)formBrowser).setVisible(false);
        ((AbstractGridBrowser)gridBrowser).setVisible(true);
    }

    public void displayFormAndHideGrid() {
        ((AbstractGridBrowser)gridBrowser).setVisible(false);
        ((AbstractFormBrowser)formBrowser).setVisible(true);
    }
}
