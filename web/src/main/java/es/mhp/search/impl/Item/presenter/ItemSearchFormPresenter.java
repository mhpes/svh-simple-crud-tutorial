package es.mhp.search.impl.item.presenter;

import com.vaadin.event.FieldEvents.TextChangeListener;
import es.mhp.browser.IBrowser;
import es.mhp.browser.presenter.AbstractSearchFormPresenter;
import es.mhp.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 30/03/2016.
 */
@Component
@Scope("session")
public class ItemSearchFormPresenter extends AbstractSearchFormPresenter {

    @Autowired
    private ICategoryService categoryService;

    @Override
    public void buildSearchForm(IBrowser browser) {
        browser.updateAndDisplayGrid(categoryService.findAll());
    }

    public TextChangeListener createSearchFormListener(IBrowser browser) {
        return (TextChangeListener) event -> {
            browser.buildBrowser();
            browser.updateAndDisplayGrid(categoryService.findAnyCategories(event.getText()));
        };
    }
}
