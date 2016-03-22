package es.mhp.browser.impl.category;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.CategoryViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(CategoryFormBrowser.BEAN_NAME)
public class CategoryFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "category_form_browser";

    @Autowired
    private ICategoryService categoryService;

    public CategoryFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(AbstractDTO dto, String mode) {
        if (dto == null){
            dto = new CategoryDTO();
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
        form.addComponent(fieldGroup.buildAndBind(CATEGORY_ID));
        form.addComponent(fieldGroup.buildAndBind(NAME));
        form.addComponent(fieldGroup.buildAndBind(DESCRIPTION));
        form.addComponent(fieldGroup.buildAndBind(IMAGE_URL));
    }

    @Override
    protected void setItemProperty(AbstractDTO dto) {
        CategoryDTO categoryDTO = (CategoryDTO) dto;
        BeanItem<? extends AbstractDTO> beanItem = fieldGroup.getItemDataSource();
        beanItem.addItemProperty(CATEGORY_ID, new ObjectProperty<>(categoryDTO.getCategoryId()));
        beanItem.addItemProperty(NAME, new ObjectProperty<>(categoryDTO.getName()));
        beanItem.addItemProperty(DESCRIPTION, new ObjectProperty<>(categoryDTO.getDescription()));
        beanItem.addItemProperty(IMAGE_URL, new ObjectProperty<>(categoryDTO.getImageUrl()));
    }
}
