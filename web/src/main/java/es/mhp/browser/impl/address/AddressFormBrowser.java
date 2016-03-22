package es.mhp.browser.impl.address;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.ComboBox;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.services.IAddressService;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.dto.ZipLocationDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void createFormBrowser(AbstractDTO dto, String mode) {
        if (dto == null){
            dto = new AddressDTO();
        }

        createFieldGroup(dto);
        bindForm(dto, mode);
    }

    @Override
    protected void bindForm(AbstractDTO dto, String mode) {
        form.removeAllComponents();

        setItemProperty(dto);
        bindFieldsAndAddComponents((AddressDTO) dto);

        ((AbstractView)getParent().getParent()).updateToolbar(getStateType(mode));
    }

    private void bindFieldsAndAddComponents(AddressDTO addressDTO) {
        ComboBox selectZip = buildZipComboBox(addressDTO);
        fieldGroup.bind(selectZip, ZIP);
        form.addComponent(selectZip);
        form.addComponent(fieldGroup.buildAndBind(MAIN_STREET));
        form.addComponent(fieldGroup.buildAndBind(SECONDARY_STREET));
        form.addComponent(fieldGroup.buildAndBind(CITY));
        form.addComponent(fieldGroup.buildAndBind(STATE));
        form.addComponent(fieldGroup.buildAndBind(LATITUDE));
        form.addComponent(fieldGroup.buildAndBind(LONGITUDE));
    }

    @Override
    protected void setItemProperty(AbstractDTO dto) {
        AddressDTO addressDTO = (AddressDTO) dto;
        BeanItem<? extends AbstractDTO> beanItem = fieldGroup.getItemDataSource();
        beanItem.addItemProperty(ADDRESS_ID, new ObjectProperty(addressDTO.getAddressId()));
        beanItem.addItemProperty(MAIN_STREET, new ObjectProperty(addressDTO.getMainStreet()));
        beanItem.addItemProperty(SECONDARY_STREET, new ObjectProperty(addressDTO.getSecondaryStreet()));
        beanItem.addItemProperty(ZIP, new ObjectProperty(addressDTO.getZipLocation()));
        beanItem.addItemProperty(CITY, new ObjectProperty(addressDTO.getCity()));
        beanItem.addItemProperty(STATE, new ObjectProperty(addressDTO.getState()));
        beanItem.addItemProperty(LATITUDE, new ObjectProperty(addressDTO.getLatitude()));
        beanItem.addItemProperty(LONGITUDE, new ObjectProperty(addressDTO.getLongitude()));
    }

    private ComboBox buildZipComboBox(AddressDTO addressDTO) {
        ComboBox selectZip = new ComboBox(ZIPS);

        BeanItemContainer<ZipLocationDTO> zipLocationContainer = new BeanItemContainer<>(ZipLocationDTO.class);
        Set<AbstractDTO> zipList = iZipLocationService.findAll();

        zipList.forEach((bean) -> zipLocationContainer.addBean((ZipLocationDTO) bean));

        selectZip.setContainerDataSource(zipLocationContainer);
        selectZip.setItemCaptionPropertyId(ZIPCODEID);
        selectZip.select(addressDTO.getZipLocation());
        selectZip.setNullSelectionAllowed(false);
        selectZip.setRequired(true);

        return selectZip;
    }
}
