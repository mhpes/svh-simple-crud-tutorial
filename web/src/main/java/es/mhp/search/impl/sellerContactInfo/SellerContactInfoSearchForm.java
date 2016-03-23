package es.mhp.search.impl.sellerContactInfo;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(SellerContactInfoSearchForm.BEAN_NAME)
public class SellerContactInfoSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "sellerContactInfo_search_form";

    @Autowired
    private ISellerContactInfoService sellerContactInfoService;

    private FormLayout searchForm;

    public SellerContactInfoSearchForm() {
        super();
        searchForm = new FormLayout();
        this.addComponent(searchForm);
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        TextField filter = new TextField();
        filter.setInputPrompt("Filter sellers...");

        browser.updateAndDisplayGrid(sellerContactInfoService.findAll());
        toolbar.updateToolbar(StateType.INITIAL);

        filter.addTextChangeListener(e -> {
            browser.buildBrowser();
            browser.updateAndDisplayGrid(sellerContactInfoService.findAnySellers(e.getText()));
        });

        searchForm.addComponents(filter);
    }
}
