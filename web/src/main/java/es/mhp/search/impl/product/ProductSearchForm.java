package es.mhp.search.impl.product;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.product.presenter.ProductSearchFormPresenter;
import es.mhp.services.IProductService;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ProductSearchForm.BEAN_NAME)
@Scope("prototype")
public class ProductSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "product_search_form";

    private TextField filter;

    @Autowired
    private ProductSearchFormPresenter productSearchFormPresenter;

    public ProductSearchForm() {
        super();
        initializeComponents();
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        filter.setInputPrompt("Filter products...");

        productSearchFormPresenter.updateAndDisplayGrid(browser);
        toolbar.updateToolbar(StateType.INITIAL);

        filter.addTextChangeListener(productSearchFormPresenter.createSearchFormListener(browser));

        searchForm.addComponents(filter);
    }

    @Override
    protected void initializeComponents() {
        filter = new TextField();
    }
}
