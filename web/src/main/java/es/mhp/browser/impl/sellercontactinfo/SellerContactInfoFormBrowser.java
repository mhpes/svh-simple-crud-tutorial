package es.mhp.browser.impl.sellercontactinfo;

import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.SellerContactInfoViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(SellerContactInfoFormBrowser.BEAN_NAME)
@Scope("prototype")
public class SellerContactInfoFormBrowser extends AbstractFormBrowser<SellerContactInfoDTO> {

    public static final String BEAN_NAME = "sellerContactInfo_form_browser";

    public SellerContactInfoFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        SellerContactInfoDTO sellerContactInfoDTO = new SellerContactInfoDTO();

        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            sellerContactInfoDTO = (SellerContactInfoDTO) dto;
        }

        bindForm(sellerContactInfoDTO, mode);
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(FIRST_NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(LAST_NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(EMAIL_FIELD, true));
    }
}
