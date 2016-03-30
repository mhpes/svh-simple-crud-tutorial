package es.mhp.search.impl.category;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.category.presenter.CategorySearchFormPresenter;
import es.mhp.services.ICategoryService;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(CategorySearchForm.BEAN_NAME)
@Scope("prototype")
public class CategorySearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "category_search_form";

    private TextField filter;

    @Autowired
    private CategorySearchFormPresenter categorySearchFormPresenter;

    @Autowired
    private ICategoryService categoryService;

    private FormLayout searchForm;

    public CategorySearchForm() {
        super();
        initializeComponents();
        this.addComponent(searchForm);
    }

    @Override
    public void initializeComponents() {
        searchForm = new FormLayout();

        filter = new TextField();
        filter.setInputPrompt("Filter categories...");
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        toolbar.updateToolbar(StateType.INITIAL);
        categorySearchFormPresenter.updateAndDisplayGrid(browser);

        filter.addTextChangeListener(categorySearchFormPresenter.createSearchFormListener(browser));
        searchForm.addComponents(filter);
    }
}
