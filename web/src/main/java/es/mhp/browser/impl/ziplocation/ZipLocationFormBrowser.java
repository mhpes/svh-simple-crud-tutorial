package es.mhp.browser.impl.ziplocation;

import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.ZipLocationViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ZipLocationFormBrowser.BEAN_NAME)
@Scope("prototype")
public class ZipLocationFormBrowser extends AbstractFormBrowser<ZipLocationDTO> {

    public static final String BEAN_NAME = "zipLocation_form_browser";

    public ZipLocationFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        ZipLocationDTO zipLocationDTO = new ZipLocationDTO();

        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            zipLocationDTO = (ZipLocationDTO) dto;
        }

        bindForm(zipLocationDTO, mode);
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(ZIPCODE_FIELD, true));
        form.addComponent(buildAndBindTextField(CITY_FIELD, true));
        form.addComponent(buildAndBindTextField(STATE_FIELD, true));
    }
}
