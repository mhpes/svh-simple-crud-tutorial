package es.mhp.views;

import com.vaadin.spring.annotation.SpringView;
import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.ziplocation.ZipLocationBrowser;
import es.mhp.search.ISearchForm;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.ziplocation.ZipLocationSearchForm;
import es.mhp.toolbar.impl.Toolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static es.mhp.views.utils.ZipLocationViewConstants.VIEW_NAME;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = VIEW_NAME)
public class ZipLocationView extends AbstractView {

    @Autowired
    @Qualifier(ZipLocationSearchForm.BEAN_NAME)
    private ISearchForm searchForm;

    @Autowired
    @Qualifier(ZipLocationBrowser.BEAN_NAME)
    private IBrowser browser;

    public ZipLocationView() {
        this.addStyleName("ziplocation-view");
    }

    @Override
    protected void addComponentsToView() {
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

