package es.mhp.browser.impl.address;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.ComboBox;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.dto.ZipLocationDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.Set;

import static es.mhp.views.utils.AddressViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(AddressFormBrowser.BEAN_NAME)
public class AddressFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "address_form_browser";

    @Autowired
    private IZipLocationService zipLocationService;

    public AddressFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        AddressDTO addressDto = new AddressDTO();
        BeanItem<AddressDTO> beanItem;

        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            addressDto = (AddressDTO) dto;
            beanItem = createBeanItem(addressDto);
        } else {
            beanItem = new BeanItem<>(addressDto);
        }

        createFieldGroup(beanItem);
        bindForm(addressDto, mode);
        fieldGroup.bindMemberFields(form);
    }

    @Override
    protected BeanItem createBeanItem(AbstractDTO dto) {
        AddressDTO addressDto = (AddressDTO) dto;
        BeanItem<AddressDTO> beanItem = new BeanItem<>(addressDto);
        beanItem.addItemProperty(ADDRESSID_FIELD, new ObjectProperty(addressDto.getAddressId()));
        beanItem.addItemProperty(MAIN_STREET_FIELD, new ObjectProperty(addressDto.getMainStreet()));
        beanItem.addItemProperty(SECONDARY_STREET_FIELD, new ObjectProperty(addressDto.getSecondaryStreet() != null ? addressDto.getSecondaryStreet() : StringUtils.EMPTY));
        beanItem.addItemProperty(ZIPLOCATION_FIELD, new ObjectProperty(addressDto.getZipLocationDTO()));
        beanItem.addItemProperty(CITY_FIELD, new ObjectProperty(addressDto.getCity()));
        beanItem.addItemProperty(STATE_FIELD, new ObjectProperty(addressDto.getState()));
        beanItem.addItemProperty(LATITUDE_FIELD, new ObjectProperty(addressDto.getLatitude()));
        beanItem.addItemProperty(LONGITUDE_FIELD, new ObjectProperty(addressDto.getLongitude()));
        return beanItem;
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindZipComboBox((AddressDTO)dto));
        form.addComponent(buildAndBindTextField(MAIN_STREET_FIELD, true));
        form.addComponent(buildAndBindTextField(SECONDARY_STREET_FIELD, false));
        form.addComponent(buildAndBindTextField(CITY_FIELD, true));
        form.addComponent(buildAndBindTextField(STATE_FIELD, true));
        form.addComponent(buildAndBindTextField(LATITUDE_FIELD, true));
        form.addComponent(buildAndBindTextField(LONGITUDE_FIELD, true));
        // Set the form to act immediately on user input. This is necessary for the validation of the fields to occur immediately
        // when the input focus changes and not just on commit.
        form.setImmediate(true);
    }

    private ComboBox buildAndBindZipComboBox(AddressDTO addressDTO) {
        Set<ZipLocationDTO> zipSet = (Set<ZipLocationDTO>)(Set<?>) zipLocationService.findAll();
        BeanItemContainer<ZipLocationDTO> zipLocationContainer = new BeanItemContainer<>(ZipLocationDTO.class, zipSet);
        ComboBox zipCombobox = new ComboBox(ZIP, zipSet);
        zipCombobox.setContainerDataSource(zipLocationContainer);
        zipCombobox.setItemCaptionPropertyId(ZIPCODEID);
        zipCombobox.setNullSelectionAllowed(false);
        zipCombobox.setRequired(true);
        fieldGroup.bind(zipCombobox, ZIPLOCATION_FIELD);

        if (addressDTO.getZipLocationDTO() != null) {
            //One way to select the zip on the combobox field
            Optional<ZipLocationDTO> zipLocationDTOOptional = zipLocationContainer.getItemIds().stream()
                    .filter(dto -> dto.getZipCodeId() == addressDTO.getZipLocationDTO().getZipCodeId()).findFirst();
            if (zipLocationDTOOptional.isPresent()) {
                zipCombobox.setValue(zipLocationDTOOptional.get());
            }
            //Second way. This is a worst solution because it checks all the list items and that is not needed
//            zipLocationContainer.getItemIds().stream().forEach(dto -> {
//                    if (dto.getZipCodeId() == addressDTO.getZipLocationDTO().getZipCodeId()) {
//                        zipCombobox.setValue(dto);
//                    }
//                });
        }
        return zipCombobox;
    }
}
