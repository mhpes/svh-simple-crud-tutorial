package es.mhp.views;

import com.vaadin.spring.annotation.SpringView;
import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.sellercontactinfo.SellerContactInfoBrowser;
import es.mhp.search.ISearchForm;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.sellercontactinfo.SellerContactInfoSearchForm;
import es.mhp.toolbar.impl.Toolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static es.mhp.views.utils.SellerContactInfoViewConstants.VIEW_NAME;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = VIEW_NAME)
public class SellerContactInfoView extends AbstractView {

    @Autowired
    @Qualifier(SellerContactInfoSearchForm.BEAN_NAME)
    private ISearchForm searchForm;

    @Autowired
    @Qualifier(SellerContactInfoBrowser.BEAN_NAME)
    private IBrowser browser;

    public SellerContactInfoView() {
        this.addStyleName("sellercontactinfo-view");
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

