package es.mhp.search.impl.tag;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.services.ITagService;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(TagSearchForm.BEAN_NAME)
public class TagSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "tag_search_form";

    @Autowired
    private ITagService tagService;

    private FormLayout searchForm;

    public TagSearchForm() {
        super();
        searchForm = new FormLayout();
        this.addComponent(searchForm);
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        TextField filter = new TextField();
        filter.setInputPrompt("Filter tags...");

        /*browser.updateAndDisplayGrid(tagService.findAll());
        toolbar.updateToolbar(StateType.INITIAL);

        filter.addTextChangeListener(e -> {
            browser.buildBrowser();
            browser.updateAndDisplayGrid(tagService.findAnyTags(e.getText()));
        });*/

        searchForm.addComponents(filter);
    }
}
