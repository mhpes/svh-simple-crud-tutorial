package es.mhp.search.impl.item;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.item.presenter.ItemSearchFormPresenter;
import es.mhp.services.IItemService;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ItemSearchForm.BEAN_NAME)
@Scope("prototype")
public class ItemSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "item_search_form";

    private TextField filter;

    @Autowired
    private ItemSearchFormPresenter itemSearchFormPresenter;

    public ItemSearchForm() {
        super();
        initializeComponents();
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        filter.setInputPrompt("Filter items...");

        itemSearchFormPresenter.updateAndDisplayGrid(browser);
        toolbar.updateToolbar(StateType.INITIAL);

        filter.addTextChangeListener(itemSearchFormPresenter.createSearchFormListener(browser));

        searchForm.addComponents(filter);
    }

    @Override
    protected void initializeComponents() {
        filter = new TextField();
    }
}
