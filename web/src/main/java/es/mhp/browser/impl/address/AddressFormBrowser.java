package es.mhp.browser.impl.address;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.browser.utils.StateType;
import es.mhp.services.IAddressService;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.dto.ZipLocationDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static es.mhp.views.utils.AddressViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(AddressFormBrowser.BEAN_NAME)
public class AddressFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "address_form_browser";

    @Autowired
    private IAddressService iAddressService;

    @Autowired
    private IZipLocationService iZipLocationService;

    public AddressFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object addressDto, String mode) {
        if (addressDto == null){
            addressDto = new AddressDTO();
        }

        createFieldGroup((AbstractDTO) addressDto);
        bindForm(addressDto, mode);
    }

    private void bindForm(Object addressDTO, String mode) {
        form.removeAllComponents();

        if (FormBrowserUtils.EDIT_MODE.equals(mode)){
            setEditForm((AddressDTO)addressDTO);
        } else if (FormBrowserUtils.NEW_MODE.equals(mode)){
            setEditForm(new AddressDTO());
        }
    }

    private void setEditForm(AddressDTO addressDTO) {
        setItemPropertyEdit(addressDTO);
        ComboBox selectZip = buildZipComboBox(addressDTO);

        fieldGroup.bind(selectZip, ZIP);
        form.addComponent(selectZip);
        form.addComponent(fieldGroup.buildAndBind(MAIN_STREET));
        form.addComponent(fieldGroup.buildAndBind(SECONDARY_STREET));
        form.addComponent(fieldGroup.buildAndBind(CITY));
        form.addComponent(fieldGroup.buildAndBind(STATE));
        form.addComponent(fieldGroup.buildAndBind(LATITUDE));
        form.addComponent(fieldGroup.buildAndBind(LONGITUDE));

        ((AbstractView)getParent().getParent()).updateToolbar(StateType.EDIT);
    }

    private void setItemPropertyEdit(AddressDTO entityDTO) {
        BeanItem<? extends AbstractDTO> beanItem = fieldGroup.getItemDataSource();
        beanItem.addItemProperty(ADDRESS_ID, new ObjectProperty(entityDTO.getAddressId()));
        beanItem.addItemProperty(MAIN_STREET, new ObjectProperty(entityDTO.getMainStreet()));
        beanItem.addItemProperty(SECONDARY_STREET, new ObjectProperty(entityDTO.getSecondaryStreet()));
        beanItem.addItemProperty(ZIP, new ObjectProperty(entityDTO.getZipLocation()));
        beanItem.addItemProperty(CITY, new ObjectProperty(entityDTO.getCity()));
        beanItem.addItemProperty(STATE, new ObjectProperty(entityDTO.getState()));
        beanItem.addItemProperty(LATITUDE, new ObjectProperty(entityDTO.getLatitude()));
        beanItem.addItemProperty(LONGITUDE, new ObjectProperty(entityDTO.getLongitude()));
    }

    private ComboBox buildZipComboBox(AddressDTO addressDTO) {
        ComboBox selectZip = new ComboBox(ZIPS);

        BeanItemContainer<ZipLocationDTO> zipLocationContainer = new BeanItemContainer<>(ZipLocationDTO.class);
        Set<ZipLocationDTO> zipList = iZipLocationService.findAllZipLocations();

        for (ZipLocationDTO zip : zipList){
            zipLocationContainer.addBean(zip);
        }

        selectZip.setContainerDataSource(zipLocationContainer);

        selectZip.setItemCaptionPropertyId(ZIPCODEID);
        selectZip.select(addressDTO.getZipLocation());
        selectZip.setNullSelectionAllowed(false);
        selectZip.setRequired(true);

        return selectZip;
    }

    /*public void trySaveAddress(FieldGroup addressFieldGroup) {
        int addressId = Integer.parseInt(addressFieldGroup.getField(ADDRESS_ID).getValue().toString());
        String mainStreet = addressFieldGroup.getField(MAIN_STREET).getValue().toString();
        String secondaryStreet = addressFieldGroup.getField(SECONDARY_STREET).getValue().toString();
        ZipLocationDTO zipLocationDTO = (ZipLocationDTO) addressFieldGroup.getField(ZIP).getValue();
        String city = addressFieldGroup.getField(CITY).getValue().toString();
        String state = addressFieldGroup.getField(STATE).getValue().toString();

        BigDecimal longitude =  BigDecimal.ONE;
        BigDecimal latitude = BigDecimal.TEN;

        try{
            AddressDTO addressDTO = new AddressDTO(addressId, mainStreet, secondaryStreet, zipLocationDTO, city, state, latitude, longitude);
            iAddressService.save(addressDTO).getAddressId();
            Notification.show(NEW_ADDRESS_NOTIFICATION, Notification.Type.TRAY_NOTIFICATION);
        } catch (Exception err){ //I can't handle the correct Exception ConstraintViolationException
            Notification.show(ERROR_NEW_ADDRESS_NOTIFICATION + err.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
    }*/
}
