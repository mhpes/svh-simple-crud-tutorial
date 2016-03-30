package es.mhp.search.impl.ziplocation;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.ziplocation.presenter.ZipLocationSearchFormPresenter;
import es.mhp.services.IZipLocationService;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ZipLocationSearchForm.BEAN_NAME)
@Scope("prototype")
public class ZipLocationSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "zipLocation_search_form";

    private TextField filter;

    @Autowired
    private ZipLocationSearchFormPresenter zipLocationSearchFormPresenter;

    public ZipLocationSearchForm() {
        super();
        initializeComponents();
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        filter.setInputPrompt("Filter zips...");

        zipLocationSearchFormPresenter.updateAndDisplayGrid(browser);
        toolbar.updateToolbar(StateType.INITIAL);

        filter.addTextChangeListener(zipLocationSearchFormPresenter.createSearchFormListener(browser));

        searchForm.addComponents(filter);
    }

    @Override
    protected void initializeComponents() {
        filter = new TextField();
    }
}
