package es.mhp.views;

import com.vaadin.spring.annotation.SpringView;
import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.product.ProductBrowser;
import es.mhp.search.ISearchForm;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.product.ProductSearchForm;
import es.mhp.toolbar.impl.Toolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static es.mhp.views.utils.ProductViewConstants.VIEW_NAME;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = VIEW_NAME)
public class ProductView extends AbstractView {

    @Autowired
    @Qualifier(ProductSearchForm.BEAN_NAME)
    private ISearchForm searchForm;

    @Autowired
    @Qualifier(ProductBrowser.BEAN_NAME)
    private IBrowser browser;

    public ProductView() {
        this.addStyleName("product-view");
    }

    @Override
    public void addComponentsToView() {
        this.removeAllComponents();
        this.addComponent((AbstractSearchForm)searchForm);
        this.addComponent((Toolbar)toolbar);
        this.addComponent((AbstractBrowser)browser);
    }

    @Override
    protected void configureComponents() {
        browser.buildBrowser();
        toolbar.buildToolbar();
        searchForm.buildSearchForm(browser, toolbar);
    }

    @Override
    protected IBrowser getBrowser() {
        return browser;
    }
}
