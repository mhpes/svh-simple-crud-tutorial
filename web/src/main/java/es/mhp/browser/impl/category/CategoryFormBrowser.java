package es.mhp.browser.impl.category;

import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.CategoryDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.CategoryViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(CategoryFormBrowser.BEAN_NAME)
@Scope("prototype")
public class CategoryFormBrowser extends AbstractFormBrowser<CategoryDTO> {

    public static final String BEAN_NAME = "category_form_browser";

    public CategoryFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        CategoryDTO categoryDTO = new CategoryDTO();
        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            categoryDTO = (CategoryDTO) dto;
        }
        bindForm(categoryDTO, mode);
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(DESCRIPTION_FIELD, true));
        form.addComponent(buildAndBindTextField(IMAGEURL_FIELD, true));
    }
}
