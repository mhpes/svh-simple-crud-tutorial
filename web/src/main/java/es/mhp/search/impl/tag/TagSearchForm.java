package es.mhp.search.impl.tag;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.tag.presenter.TagSearchFormPresenter;
import es.mhp.services.ITagService;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(TagSearchForm.BEAN_NAME)
@Scope("prototype")
public class TagSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "tag_search_form";

    private TextField filter;

    @Autowired
    private TagSearchFormPresenter tagSearchFormPresenter;

    public TagSearchForm() {
        super();
        initializeComponents();
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        filter.setInputPrompt("Filter tags...");

        tagSearchFormPresenter.updateAndDisplayGrid(browser);
        toolbar.updateToolbar(StateType.INITIAL);

        filter.addTextChangeListener(tagSearchFormPresenter.createSearchFormListener(browser));

        searchForm.addComponents(filter);
    }

    @Override
    protected void initializeComponents() {
        filter = new TextField();
    }
}
