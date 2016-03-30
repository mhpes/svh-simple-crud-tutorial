package es.mhp.search.impl.item.presenter;

import com.vaadin.event.FieldEvents.TextChangeListener;
import es.mhp.browser.IBrowser;
import es.mhp.search.impl.presenter.AbstractSearchFormPresenter;
import es.mhp.services.IItemService;
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
    private IItemService itemService;

    public TextChangeListener createSearchFormListener(IBrowser browser) {
        return (TextChangeListener) event -> {
            browser.buildBrowser();
            browser.updateAndDisplayGrid(itemService.findAnyItems(event.getText()));
        };
    }

    @Override
    public void updateAndDisplayGrid(IBrowser browser) {
        browser.updateAndDisplayGrid(itemService.findAll());
    }
}
