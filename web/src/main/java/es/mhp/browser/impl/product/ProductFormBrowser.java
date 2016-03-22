package es.mhp.browser.impl.product;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.ComboBox;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.services.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

import static es.mhp.views.utils.ProductViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ProductFormBrowser.BEAN_NAME)
public class ProductFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "product_form_browser";

    @Autowired
    private ICategoryService categoryService;

    public ProductFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        ProductDTO productDTO = new ProductDTO();
        BeanItem<ProductDTO> beanItem = null;
        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            productDTO = (ProductDTO) dto;
            beanItem = createBeanItem(productDTO);
        } else {
            beanItem = new BeanItem<>(productDTO);
        }
        createFieldGroup(beanItem);
        bindForm(productDTO, mode);
        fieldGroup.bindMemberFields(form);
    }

    @Override
    protected BeanItem createBeanItem(AbstractDTO dto) {
        ProductDTO productDTO = (ProductDTO) dto;
        BeanItem<ProductDTO> beanItem = new BeanItem<>(productDTO);
        beanItem.addItemProperty(PRODUCTID_FIELD, new ObjectProperty<>(productDTO.getProductId()));
        beanItem.addItemProperty(CATEGORY_FIELD, new ObjectProperty<>(productDTO.getCategoryDTO()));
        beanItem.addItemProperty(NAME_FIELD, new ObjectProperty<>(productDTO.getName()));
        beanItem.addItemProperty(DESCRIPTION_FIELD, new ObjectProperty<>(productDTO.getDescription()));
        beanItem.addItemProperty(IMAGEURL_FIELD, new ObjectProperty<>(productDTO.getImageUrl()));
        return beanItem;
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(PRODUCTID_FIELD, true));
        form.addComponent(buildAndBindCategoryComboBox((ProductDTO) dto));
        form.addComponent(buildAndBindTextField(NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(DESCRIPTION_FIELD, true));
        form.addComponent(buildAndBindTextField(IMAGEURL_FIELD, true));

        // Set the form to act immediately on user input. This is necessary for the validation of the fields to occur immediately
        // when the input focus changes and not just on commit.
        form.setImmediate(true);
    }

    private ComboBox buildAndBindCategoryComboBox(ProductDTO productDTO) {
        Set<AbstractDTO> categorySet = categoryService.findAll();
        BeanItemContainer<AbstractDTO> zipLocationContainer = new BeanItemContainer<>(AbstractDTO.class, categorySet);
        ComboBox categoryCombobox = new ComboBox(CATEGORY, categorySet);
        categoryCombobox.setContainerDataSource(zipLocationContainer);
        categoryCombobox.setItemCaptionPropertyId(CATEGORYID_FIELD);
        categoryCombobox.setNullSelectionAllowed(false);
        categoryCombobox.setRequired(true);
        fieldGroup.bind(categoryCombobox, CATEGORY_FIELD);

        if (productDTO.getCategoryDTO() != null) {
            Optional<AbstractDTO> categoryDTOOptional = zipLocationContainer.getItemIds().stream()
                    .filter(dto -> ((CategoryDTO)dto).getCategoryId() == productDTO.getCategoryDTO().getCategoryId()).findFirst();
            if (categoryDTOOptional.isPresent()) {
                categoryCombobox.setValue(categoryDTOOptional.get());
            }
        }
        return categoryCombobox;
    }
}
