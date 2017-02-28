package es.mhp.search.impl.sellercontactinfo;

import com.vaadin.ui.TextField;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.sellercontactinfo.presenter.SellerContactInfoSearchFormPresenter;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(SellerContactInfoSearchForm.BEAN_NAME)
@Scope("prototype")
public class SellerContactInfoSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "sellerContactInfo_search_form";

    private TextField filter;

    @Autowired
    private SellerContactInfoSearchFormPresenter sellerContactInfoSearchFormPresenter;

    public SellerContactInfoSearchForm() {
        super();
        initializeComponents();
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();

        filter.setPlaceholder("Filter sellers...");

        sellerContactInfoSearchFormPresenter.updateAndDisplayGrid(browser);
        toolbar.updateToolbar(StateType.INITIAL);

        filter.addValueChangeListener(sellerContactInfoSearchFormPresenter.createSearchFormListener(browser));

        searchForm.addComponents(filter);
    }

    @Override
    protected void initializeComponents() {
        filter = new TextField();
    }
}
