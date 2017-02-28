package es.mhp.browser.impl.product;

import com.vaadin.ui.ComboBox;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.product.presenter.ProductFormBrowserPresenter;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.services.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

import static es.mhp.views.utils.ProductViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ProductFormBrowser.BEAN_NAME)
@Scope("prototype")
public class ProductFormBrowser extends AbstractFormBrowser<ProductDTO> {

    public static final String BEAN_NAME = "product_form_browser";

    @Autowired
    private ProductFormBrowserPresenter productFormBrowserPresenter;

    public ProductFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        ProductDTO productDTO = new ProductDTO();

        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            productDTO = (ProductDTO) dto;
        }

        bindForm(productDTO, mode);
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(PRODUCTID_FIELD, true));
        form.addComponent(buildAndBindCategoryComboBox((ProductDTO) dto));
        form.addComponent(buildAndBindTextField(NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(DESCRIPTION_FIELD, true));
        form.addComponent(buildAndBindTextField(IMAGEURL_FIELD, true));
    }

    private ComboBox buildAndBindCategoryComboBox(ProductDTO productDTO) {
        Set<CategoryDTO> categories = productFormBrowserPresenter.findAllCategories();

        ComboBox<CategoryDTO> categoryCombobox = new ComboBox();
        categoryCombobox.setItems(categories);
        categoryCombobox.setItemCaptionGenerator(p -> p.getCategoryId());
        categoryCombobox.setRequiredIndicatorVisible(true);
        binder.bind(categoryCombobox, CATEGORY_FIELD);

        selectCurrentCategory(productDTO, categories, categoryCombobox);
        return categoryCombobox;
    }

    private void selectCurrentCategory(ProductDTO productDTO, Set<CategoryDTO> categories, ComboBox categoryCombobox) {
        if (productDTO.getCategoryDTO() != null) {
            Optional<CategoryDTO> categoryDTOOptional = categories.stream()
              .filter(dto -> dto.getCategoryId() == productDTO.getCategoryDTO().getCategoryId()).findFirst();
            if (categoryDTOOptional.isPresent()) {
                categoryCombobox.setValue(categoryDTOOptional.get());
            }
        }
    }
}
