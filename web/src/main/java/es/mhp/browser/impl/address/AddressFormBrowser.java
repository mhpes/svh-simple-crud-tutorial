package es.mhp.browser.impl.address;

import com.vaadin.ui.ComboBox;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.address.presenter.AddressFormBrowserPresenter;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

import static es.mhp.views.utils.AddressViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(AddressFormBrowser.BEAN_NAME)
@Scope("prototype")
public class AddressFormBrowser extends AbstractFormBrowser<AddressDTO> {

    public static final String BEAN_NAME = "address_form_browser";

    @Autowired
    private AddressFormBrowserPresenter addressFormBrowserPresenter;

    public AddressFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        AddressDTO addressDto = new AddressDTO();

        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            addressDto = (AddressDTO) dto;
            createFieldGroup(addressDto);
        }

        bindForm(addressDto, mode);
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();

        form.addComponent(buildAndBindZipComboBox((AddressDTO)dto));
        form.addComponent(buildAndBindTextField(MAIN_STREET_FIELD, true));
        form.addComponent(buildAndBindTextField(SECONDARY_STREET_FIELD, false));
        form.addComponent(buildAndBindTextField(CITY_FIELD, true));
        form.addComponent(buildAndBindTextField(STATE_FIELD, true));
        /*form.addComponent(buildAndBindTextField(LATITUDE_FIELD, true));
        form.addComponent(buildAndBindTextField(LONGITUDE_FIELD, true));*/
        testsEdu();
    }

    private ComboBox buildAndBindZipComboBox(AddressDTO addressDTO) {
        Set<ZipLocationDTO> zipLocations = addressFormBrowserPresenter.findAllZipLocation();

        ComboBox<ZipLocationDTO> zipCombobox = new ComboBox(ZIP);
        zipCombobox.setItems(zipLocations);
        zipCombobox.setItemCaptionGenerator(p-> p.getId().toString());
        zipCombobox.setRequiredIndicatorVisible(true);
        binder.bind(zipCombobox, ZIPLOCATION_FIELD);

        selectCurrentAddress(addressDTO, zipLocations, zipCombobox);
        return zipCombobox;
    }

    private void selectCurrentAddress(AddressDTO addressDTO, Set<ZipLocationDTO> zipLocationContainer, ComboBox zipCombobox) {
        if (addressDTO.getZipLocationDTO() != null) {
            Optional<ZipLocationDTO> zipLocationDTOOptional = zipLocationContainer.stream()
                    .filter(dto -> dto.getZipCodeId() == addressDTO.getZipLocationDTO().getZipCodeId()).findFirst();
            if (zipLocationDTOOptional.isPresent()) {
                zipCombobox.setValue(zipLocationDTOOptional.get());
            }
        }
    }

    private void testsEdu() {
        form.addComponent(buildAndBindColorPicker(COLOR_FIELD, true));

        Set<String> examples = new HashSet<>();
        examples.add("a");
        examples.add("b");
        examples.add("c");
        examples.add("d");
        examples.add("e");
        ComboBox exampleCombo = new ComboBox<>();
        exampleCombo.setItems(examples);
        exampleCombo.setItemCaptionGenerator(p-> p.toString());
        exampleCombo.setRequiredIndicatorVisible(true);
        this.addComponent(exampleCombo);
        binder.bind(exampleCombo, COMBO_EXAMPLE_FIELD);
    }

}
