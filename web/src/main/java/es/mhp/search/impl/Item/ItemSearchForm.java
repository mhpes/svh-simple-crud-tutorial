package es.mhp.search.impl.item;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.services.IItemService;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ItemSearchForm.BEAN_NAME)
public class ItemSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "item_search_form";

    @Autowired
    private IItemService itemService;

    private FormLayout searchForm;

    public ItemSearchForm() {
        super();
        searchForm = new FormLayout();
        this.addComponent(searchForm);
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        TextField filter = new TextField();
        filter.setInputPrompt("Filter items...");

        browser.updateGrid(itemService.findAll());
        toolbar.updateToolbar(StateType.INITIAL);

        filter.addTextChangeListener(e -> {
            browser.buildBrowser();
            browser.updateGrid(itemService.findAnyItems(e.getText()));
        });

        searchForm.addComponents(filter);
    }
}
