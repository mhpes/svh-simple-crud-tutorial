package es.mhp.browser.impl.category;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.CategoryViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(CategoryFormBrowser.BEAN_NAME)
public class CategoryFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "category_form_browser";

    public CategoryFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanItem<CategoryDTO> beanItem = null;
        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            categoryDTO = (CategoryDTO) dto;
            beanItem = createBeanItem(categoryDTO);
        } else {
            beanItem = new BeanItem<>(categoryDTO);
        }
        createFieldGroup(beanItem);
        bindForm(categoryDTO, mode);
        fieldGroup.bindMemberFields(form);
    }

    @Override
    protected BeanItem createBeanItem(AbstractDTO dto) {
        CategoryDTO categoryDTO = (CategoryDTO) dto;
        BeanItem<CategoryDTO> beanItem = new BeanItem<>(categoryDTO);
        beanItem.addItemProperty(CATEGORYID_FIELD, new ObjectProperty<>(categoryDTO.getCategoryId()));
        beanItem.addItemProperty(NAME_FIELD, new ObjectProperty<>(categoryDTO.getName()));
        beanItem.addItemProperty(DESCRIPTION_FIELD, new ObjectProperty<>(categoryDTO.getDescription()));
        beanItem.addItemProperty(IMAGEURL_FIELD, new ObjectProperty<>(categoryDTO.getImageUrl()));
        return beanItem;
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(CATEGORYID_FIELD, true));
        form.addComponent(buildAndBindTextField(NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(DESCRIPTION_FIELD, true));
        form.addComponent(buildAndBindTextField(IMAGEURL_FIELD, true));

        // Set the form to act immediately on user input. This is necessary for the validation of the fields to occur immediately
        // when the input focus changes and not just on commit.
        form.setImmediate(true);
    }
}
