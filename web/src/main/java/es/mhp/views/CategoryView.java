package es.mhp.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.category.CategoryBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.ISearchForm;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.category.CategorySearchForm;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.toolbar.impl.Toolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static es.mhp.views.utils.CategoryViewConstants.VIEW_NAME;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = VIEW_NAME)
public class CategoryView extends AbstractView<CategoryDTO> {

    @Autowired
    @Qualifier(CategorySearchForm.BEAN_NAME)
    private ISearchForm searchForm;

    @Autowired
    @Qualifier(CategoryBrowser.BEAN_NAME)
    private IBrowser browser;

    public CategoryView() {
        this.setSizeFull();
        this.addStyleName("category-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        createMainLayout();
        //At first time, the toolbar is initialized to the INITIAL state
        toolbar.updateToolbar(StateType.INITIAL);
    }

    @Override
    void addComponentsToView() {

    }


    protected void createMainLayout() {
        buildComponents();
        this.removeAllComponents();
        this.addComponent((AbstractSearchForm)searchForm);
        this.addComponent((Toolbar)toolbar);
        this.addComponent((AbstractBrowser)browser);
    }

    private void buildComponents() {
        browser.buildBrowser();
        toolbar.buildToolbar(browser);
        searchForm.buildSearchForm(browser, toolbar);
    }

    @Override
    void configureComponents() {

    }

    @Override
    IBrowser getBrowser() {
        return null;
    }
}

