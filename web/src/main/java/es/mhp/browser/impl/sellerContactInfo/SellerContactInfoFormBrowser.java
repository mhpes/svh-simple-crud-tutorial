package es.mhp.browser.impl.sellerContactInfo;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.SellerContactInfoViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(SellerContactInfoFormBrowser.BEAN_NAME)
public class SellerContactInfoFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "sellerContactInfo_form_browser";

    public SellerContactInfoFormBrowser() {
        super();
    }



    @Override
    public void createFormBrowser(Object dto, String mode) {
        SellerContactInfoDTO sellerContactInfoDTO = new SellerContactInfoDTO();
        BeanItem<SellerContactInfoDTO> beanItem = null;
        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            sellerContactInfoDTO = (SellerContactInfoDTO) dto;
            beanItem = createBeanItem(sellerContactInfoDTO);
        } else {
            beanItem = new BeanItem<>(sellerContactInfoDTO);
        }
        createFieldGroup(beanItem);
        bindForm(sellerContactInfoDTO, mode);
        fieldGroup.bindMemberFields(form);
    }

    @Override
    protected BeanItem createBeanItem(AbstractDTO dto) {
        SellerContactInfoDTO sellerContactInfoDTO = (SellerContactInfoDTO) dto;
        BeanItem<SellerContactInfoDTO> beanItem = new BeanItem<>(sellerContactInfoDTO);
        beanItem.addItemProperty(CONTACTINFO_FIELD, new ObjectProperty(sellerContactInfoDTO.getContactInfoId()));
        beanItem.addItemProperty(FIRST_NAME_FIELD, new ObjectProperty(sellerContactInfoDTO.getLastName()));
        beanItem.addItemProperty(LAST_NAME_FIELD, new ObjectProperty(sellerContactInfoDTO.getFirstName()));
        beanItem.addItemProperty(EMAIL_FIELD, new ObjectProperty(sellerContactInfoDTO.getEmail()));
        return beanItem;
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(FIRST_NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(LAST_NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(EMAIL_FIELD, true));

        // Set the form to act immediately on user input. This is necessary for the validation of the fields to occur immediately
        // when the input focus changes and not just on commit.
        form.setImmediate(true);
    }
}
