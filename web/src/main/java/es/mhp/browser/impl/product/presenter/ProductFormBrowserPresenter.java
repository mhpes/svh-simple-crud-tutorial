package es.mhp.browser.impl.product.presenter;

import com.vaadin.data.util.BeanItemContainer;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Edu on 30/03/2016.
 */

@Component
@Scope("session")
public class ProductFormBrowserPresenter {

    @Autowired
    private ICategoryService categoryService;

    public BeanItemContainer<CategoryDTO> findAllCategories() {
        Set<CategoryDTO> sellerSet = (Set<CategoryDTO>)(Set<?>) categoryService.findAll();
        return new BeanItemContainer<>(CategoryDTO.class, sellerSet);
    }
}
