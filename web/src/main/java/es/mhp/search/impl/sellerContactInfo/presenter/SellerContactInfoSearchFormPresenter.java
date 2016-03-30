package es.mhp.search.impl.sellercontactinfo.presenter;

import com.vaadin.event.FieldEvents.TextChangeListener;
import es.mhp.browser.IBrowser;
import es.mhp.search.impl.presenter.AbstractSearchFormPresenter;
import es.mhp.services.ISellerContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 30/03/2016.
 */
@Component
@Scope("session")
public class SellerContactInfoSearchFormPresenter extends AbstractSearchFormPresenter {

    @Autowired
    private ISellerContactInfoService sellerContactInfoService;

    public TextChangeListener createSearchFormListener(IBrowser browser) {
        return (TextChangeListener) event -> {
            browser.buildBrowser();
            browser.updateAndDisplayGrid(sellerContactInfoService.findAnySellers(event.getText()));
        };
    }

    @Override
    public void updateAndDisplayGrid(IBrowser browser) {
        browser.updateAndDisplayGrid(sellerContactInfoService.findAll());
    }
}
