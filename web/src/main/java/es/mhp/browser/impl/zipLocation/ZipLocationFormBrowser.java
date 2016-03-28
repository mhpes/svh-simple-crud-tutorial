package es.mhp.browser.impl.ziplocation;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.ZipLocationViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ZipLocationFormBrowser.BEAN_NAME)
public class ZipLocationFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "zipLocation_form_browser";

    public ZipLocationFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        ZipLocationDTO zipLocationDTO = new ZipLocationDTO();
        BeanItem<ZipLocationDTO> beanItem;

        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            zipLocationDTO = (ZipLocationDTO) dto;
            beanItem = createBeanItem(zipLocationDTO);
        } else {
            beanItem = new BeanItem<>(zipLocationDTO);
        }

        createFieldGroup(beanItem);
        bindForm(zipLocationDTO, mode);
        fieldGroup.bindMemberFields(form);
    }

    @Override
    protected BeanItem createBeanItem(AbstractDTO dto) {
        ZipLocationDTO zipLocationDTO = (ZipLocationDTO) dto;
        BeanItem<ZipLocationDTO> beanItem = new BeanItem<>(zipLocationDTO);
        beanItem.addItemProperty(ZIPCODE_FIELD, new ObjectProperty<>(zipLocationDTO.getZipCodeId()));
        beanItem.addItemProperty(CITY_FIELD, new ObjectProperty<>(zipLocationDTO.getCity()));
        beanItem.addItemProperty(STATE_FIELD, new ObjectProperty<>(zipLocationDTO.getState()));

        return beanItem;
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(ZIPCODE_FIELD, true));
        form.addComponent(buildAndBindTextField(CITY_FIELD, true));
        form.addComponent(buildAndBindTextField(STATE_FIELD, true));

        // Set the form to act immediately on user input. This is necessary for the validation of the fields to occur immediately
        // when the input focus changes and not just on commit.
        form.setImmediate(true);
    }
}
