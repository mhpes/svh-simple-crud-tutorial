package es.mhp.browser.impl.sellerContactInfo;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.SellerContactInfoDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.SellerContactInfoViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(SellerContactInfoFormBrowser.BEAN_NAME)
public class SellerContactInfoFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "sellerContactInfo_form_browser";

    @Autowired
    private ISellerContactInfoService sellerContactInfoService;

    public SellerContactInfoFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(AbstractDTO dto, String mode) {
        if (dto == null){
            dto = new SellerContactInfoDTO();
        }

        createFieldGroup(dto);
        bindForm(dto, mode);
    }

    @Override
    protected void bindForm(AbstractDTO dto, String mode) {
        form.removeAllComponents();

        setItemProperty(dto);
        bindFieldsAndAddComponents();

        ((AbstractView)getParent().getParent()).updateToolbar(getStateType(mode));
    }

    private void bindFieldsAndAddComponents() {
        //fieldGroup.bind(CONTACTINFO_FIELD);
        form.addComponent(fieldGroup.buildAndBind(FIRST_NAME_FIELD));
        form.addComponent(fieldGroup.buildAndBind(LAST_NAME_FIELD));
        form.addComponent(fieldGroup.buildAndBind(EMAIL_FIELD));
    }

    @Override
    protected void setItemProperty(AbstractDTO dto) {
        SellerContactInfoDTO sellerContactInfoDTO = (SellerContactInfoDTO) dto;
        BeanItem<? extends AbstractDTO> beanItem = fieldGroup.getItemDataSource();
        beanItem.addItemProperty(CONTACTINFO_FIELD, new ObjectProperty(sellerContactInfoDTO.getContactInfoId()));
        beanItem.addItemProperty(FIRST_NAME_FIELD, new ObjectProperty(sellerContactInfoDTO.getLastName()));
        beanItem.addItemProperty(LAST_NAME_FIELD, new ObjectProperty(sellerContactInfoDTO.getFirstName()));
        beanItem.addItemProperty(EMAIL_FIELD, new ObjectProperty(sellerContactInfoDTO.getEmail()));
    }
}
