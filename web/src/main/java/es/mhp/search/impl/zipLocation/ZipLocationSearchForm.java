package es.mhp.search.impl.zipLocation;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.services.IZipLocationService;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ZipLocationSearchForm.BEAN_NAME)
public class ZipLocationSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "zipLocation_search_form";

    @Autowired
    private IZipLocationService zipLocationService;

    private FormLayout searchForm;

    public ZipLocationSearchForm() {
        super();
        searchForm = new FormLayout();
        this.addComponent(searchForm);
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        TextField filter = new TextField();
        filter.setInputPrompt("Filter zips...");

        browser.updateGrid(zipLocationService.findAll());
        toolbar.updateToolbar(StateType.INITIAL);

        filter.addTextChangeListener(e -> {
            browser.buildBrowser();
            browser.updateGrid(zipLocationService.findAnyZipLocations(e.getText()));
        });

        searchForm.addComponents(filter);
    }
}