package es.mhp.browser.impl.category;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.FormLayout;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.browser.utils.StateType;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    public void createFormBrowser(Object categoryDto, String mode) {
        if (categoryDto == null){
            categoryDto = new CategoryDTO();
        }

        BeanItem item = new BeanItem(categoryDto);
        bindForm(categoryDto, form, item, mode);
    }

    private void bindForm(Object categoryDto, FormLayout form, BeanItem item, String mode) {
        form.removeAllComponents();
        FieldGroup binder = new FieldGroup(item);

        if (FormBrowserUtils.EDIT_MODE.equals(mode)){
            setEditForm((CategoryDTO)categoryDto, item, form, binder);
        } else if (FormBrowserUtils.NEW_MODE.equals(mode)){
            setNewForm(item, form, binder);
        }
    }

    private void setNewForm(BeanItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        form.addComponent(binder.buildAndBind(CATEGORY_ID));
        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));

        ((AbstractView)getParent().getParent()).updateToolbar(StateType.NEW);
    }

    private void setEditForm(CategoryDTO categoryDTO, BeanItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(categoryDTO, item);

        binder.buildAndBind(CATEGORY_ID);
        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));

        ((AbstractView)getParent().getParent()).updateToolbar(StateType.EDIT);
    }

    private void setItemPropertyEdit(BeanItem item) {
        item.addItemProperty(CATEGORY_ID, new ObjectProperty<>(""));
        item.addItemProperty(NAME, new ObjectProperty<>(""));
        item.addItemProperty(DESCRIPTION, new ObjectProperty<>(""));
        item.addItemProperty(IMAGE_URL, new ObjectProperty<>(""));
    }

    private void setItemPropertyEdit(CategoryDTO categoryDTO, BeanItem item) {
        item.addItemProperty(CATEGORY_ID, new ObjectProperty<>(categoryDTO.getCategoryId()));
        item.addItemProperty(NAME, new ObjectProperty<>(categoryDTO.getName()));
        item.addItemProperty(DESCRIPTION, new ObjectProperty<>(categoryDTO.getDescription()));
        item.addItemProperty(IMAGE_URL, new ObjectProperty<>(categoryDTO.getImageUrl()));
    }
}
