package es.mhp.browser.impl.address;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.*;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.services.IAddressService;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.dto.ZipLocationDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.mhp.browser.utils.FormBrowserUtils;

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
    public void createFormBrowser(Object addresDto, String mode) {
        if (addresDto == null){
            addresDto = new AddressDTO();
        }

        BeanItem item = new BeanItem(addresDto);
        bindForm(addresDto, form, item, mode);
    }

    private void bindForm(Object addressDTO, FormLayout form, BeanItem item, String mode) {
        form.removeAllComponents();
        FieldGroup binder = new FieldGroup(item);

        if (FormBrowserUtils.EDIT_MODE.equals(mode)){
            setEditForm((AddressDTO)addressDTO, item, form, binder);
        } else if (FormBrowserUtils.NEW_MODE.equals(mode)){
            setNewForm(item, form, binder);
        }
    }

    private void setNewForm(PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        Set<ZipLocationDTO> zipList = iZipLocationService.findAllZipLocations();
        ComboBox selectZip = new ComboBox(ZIPS);
        TextField mainStreet = new TextField(MAIN_STREET);
        mainStreet.setImmediate(true);

        BeanItemContainer<ZipLocationDTO> zipLocationContainer = new BeanItemContainer<>(ZipLocationDTO.class);

        for (ZipLocationDTO zipLocationDTO : zipList){
            zipLocationContainer.addBean(zipLocationDTO);
        }

        selectZip.setItemCaptionPropertyId("city");
        selectZip.setContainerDataSource(zipLocationContainer);
        selectZip.setRequired(true);
        selectZip.setImmediate(true);

        binder.bind(selectZip, ZIP);
        form.addComponent(selectZip);

        binder.buildAndBind(ADDRESS_ID);
        binder.bind(mainStreet, MAIN_STREET);

        form.addComponent(mainStreet);
        form.addComponent(binder.buildAndBind(SECONDARY_STREET));
        form.addComponent(binder.buildAndBind(CITY));
        form.addComponent(binder.buildAndBind(STATE));
        form.addComponent(binder.buildAndBind(LATITUDE));
        form.addComponent(binder.buildAndBind(LONGITUDE));

        ((AbstractView)getParent().getParent()).updateToolbar(StateType.NEW);
    }

    private void setEditForm(AddressDTO addressDTO, BeanItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(addressDTO, item);

        binder.buildAndBind(ADDRESS_ID);

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

        binder.bind(selectZip, ZIP);
        form.addComponent(selectZip);

        form.addComponent(binder.buildAndBind(MAIN_STREET));
        form.addComponent(binder.buildAndBind(SECONDARY_STREET));
        form.addComponent(binder.buildAndBind(CITY));
        form.addComponent(binder.buildAndBind(STATE));
        form.addComponent(binder.buildAndBind(LATITUDE));
        form.addComponent(binder.buildAndBind(LONGITUDE));

        ((AbstractView)getParent().getParent()).updateToolbar(StateType.EDIT);
    }

    private void setItemPropertyEdit(PropertysetItem item) {
        item.addItemProperty(ADDRESS_ID, new ObjectProperty<>(0));
        item.addItemProperty(MAIN_STREET, new ObjectProperty<>(""));
        item.addItemProperty(SECONDARY_STREET, new ObjectProperty<>(""));
        item.addItemProperty(ZIP, new ObjectProperty<>(""));
        item.addItemProperty(CITY, new ObjectProperty<>(""));
        item.addItemProperty(STATE, new ObjectProperty<>(""));
        item.addItemProperty(LATITUDE, new ObjectProperty<>(new BigDecimal(0)));
        item.addItemProperty(LONGITUDE, new ObjectProperty<>(new BigDecimal(0)));
    }

    private void setItemPropertyEdit(AddressDTO addressDTO, BeanItem item) {
        item.addItemProperty(ADDRESS_ID, new ObjectProperty(addressDTO.getAddressId()));
        item.addItemProperty(MAIN_STREET, new ObjectProperty(addressDTO.getMainStreet()));
        item.addItemProperty(SECONDARY_STREET, new ObjectProperty(addressDTO.getSecondaryStreet()));
        item.addItemProperty(ZIP, new ObjectProperty(addressDTO.getZipLocation()));
        item.addItemProperty(CITY, new ObjectProperty(addressDTO.getCity()));
        item.addItemProperty(STATE, new ObjectProperty(addressDTO.getState()));
        item.addItemProperty(LATITUDE, new ObjectProperty(addressDTO.getLatitude()));
        item.addItemProperty(LONGITUDE, new ObjectProperty(addressDTO.getLongitude()));
    }

    public void trySaveAddress(FieldGroup addressFieldGroup) {
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
    }

    @Override
    public AddressDTO getNewForm() {
        //int addressId = Integer.parseInt(getForm().fet.getField(ADDRESS_ID).getValue().toString());
        List<Object> objectList = new ArrayList<>();

        for (int i = 0; i < getForm().getComponentCount(); i++)
        {
            objectList.add(getForm().getComponent(i));
        }

        //Something similar like in trySaveAddress...
        AddressDTO addressDTO = new AddressDTO();

        return addressDTO;
    }
}
