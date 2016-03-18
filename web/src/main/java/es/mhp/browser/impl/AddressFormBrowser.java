package es.mhp.browser.impl;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.services.IAddressService;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.mhp.browser.impl.utils.AddressFormUtils;

import java.math.BigDecimal;
import java.util.Set;

import static es.mhp.views.utils.AddressViewUtils.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(AddressFormBrowser.BEAN_NAME)
public class AddressFormBrowser extends AbstractFormBrowser<AddressDTO>{

    public static final String BEAN_NAME = "address_form_browser";

    @Autowired
    private IAddressService iAddressService;

    @Autowired
    private IZipLocationService iZipLocationService;

    @Override
    public void buildFormBrowser() {
        createFormBrowser();
    }

    @Override
    public FormLayout createFormBrowser() {
        FormLayout formBrowser = new FormLayout();

        TextField mainStreet = new TextField(MAIN_STREET);
        TextField secondaryStreet = new TextField(SECONDARY_STREET);
        ComboBox city = new ComboBox(CITY);
        city.addItems(cityList);
        city.select("Stanford");
        city.setNullSelectionAllowed(false);

        ComboBox state = new ComboBox(STATE);
        state.addItems(iAddressService.stateList());

        OptionGroup browserWay = new OptionGroup();
        browserWay.addItems(ALL, ANY);
        browserWay.select(ALL);

        Button browserButton = new Button(ADDRESS_SEARCH);
        iGridBrowser.fillGrid(iAddressService.findAllAddresses());

        browserButton.addClickListener(e -> {
            AddressDTO addressDTO;
            if (state.getValue() == null)
                addressDTO = new AddressDTO(mainStreet.getValue().toString(), secondaryStreet.getValue().toString(), city.getValue().toString());
            else
                addressDTO = new AddressDTO(mainStreet.getValue().toString(), secondaryStreet.getValue().toString(), city.getValue().toString(), state.getValue().toString());

            String way = browserWay.getValue().toString();

            if (ALL.equals(way)){
                iGridBrowser.fillGrid(iAddressService.findAllAddresses(addressDTO));
            }
            else if (ANY.equals(way)){
                iGridBrowser.fillGrid(iAddressService.findAnyAddresses(addressDTO));
            }
        });

        formBrowser.addComponents(mainStreet, secondaryStreet, city, state, browserWay, browserButton);

        return formBrowser;
    }

    @Override
    public AddressFormBrowser getFormBrowser() {
        return this;
    }

    private VerticalLayout createAddressForm(Grid grid) {
        VerticalLayout formContainer = new VerticalLayout();
        iBrowser.getAddressFormBrowser().removeAllComponents();
        iBrowser.getAddressFormBrowser().addComponent(formContainer);

        grid.addItemClickListener((ItemClickEvent.ItemClickListener) event -> {
            if (event.isDoubleClick()){
                formContainer.removeAllComponents();
                iToolbar.setButtonVisibilityByState(StateType.EDIT);
                iBrowser.getAddressGridBrowser().setVisible(false);
                iBrowser.getAddressFormBrowser().setVisible(true);
                BeanItem<AddressDTO> addressBeanItem = (BeanItem<AddressDTO>) event.getItem();
                formContainer.addComponent(createForm(addressBeanItem.getBean(), AddressFormUtils.EDIT_MODE));
            } else {
                iToolbar.setButtonVisibilityByState(StateType.SELECTEDROW);
                formContainer.removeAllComponents();
            }
        });

        return formContainer;
    }

    @Override
    protected Layout createForm(AddressDTO addressDTO, String mode) {
        FormLayout form = new FormLayout();
        setFormStyle(form);

        BeanItem item = new BeanItem(addressDTO);
        bindForm(addressDTO, form, item, mode);

        return form;
    }

    private void bindForm(AddressDTO addressDTO, FormLayout form, BeanItem item, String mode) {
        form.addComponent(new Label(mode));
        FieldGroup binder = new FieldGroup(item);

        if (AddressFormUtils.EDIT_MODE.equals(mode)){
            setEditForm(addressDTO, item, form, binder);
        } else if (AddressFormUtils.NEW_MODE.equals(mode)){
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

        iToolbar.setButtonVisibilityByState(StateType.EDIT);
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

    private void setFormStyle(FormLayout form) {
        form.setImmediate(true);
        form.addStyleName("address-view-form-container");
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
}
