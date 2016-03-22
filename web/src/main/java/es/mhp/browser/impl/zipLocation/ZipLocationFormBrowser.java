package es.mhp.browser.impl.zipLocation;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ZipLocationDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.ZipLocationViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ZipLocationFormBrowser.BEAN_NAME)
public class ZipLocationFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "zipLocation_form_browser";

    @Autowired
    private IZipLocationService zipLocationService;

    public ZipLocationFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(AbstractDTO dto, String mode) {
        if (dto == null){
            dto = new ZipLocationDTO();
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
        form.addComponent(fieldGroup.buildAndBind(ZIPCODE));
        form.addComponent(fieldGroup.buildAndBind(CITY));
        form.addComponent(fieldGroup.buildAndBind(STATE));
    }

    @Override
    protected void setItemProperty(AbstractDTO dto) {
        ZipLocationDTO zipLocationDTO = (ZipLocationDTO) dto;
        BeanItem<? extends AbstractDTO> beanItem = fieldGroup.getItemDataSource();
        beanItem.addItemProperty(ZIPCODE, new ObjectProperty<>(zipLocationDTO.getZipCodeId()));
        beanItem.addItemProperty(CITY, new ObjectProperty<>(zipLocationDTO.getCity()));
        beanItem.addItemProperty(STATE, new ObjectProperty<>(zipLocationDTO.getState()));
    }
}
