package es.mhp.search.impl.ziplocation.presenter;

import com.vaadin.data.HasValue;
import es.mhp.browser.IBrowser;
import es.mhp.search.impl.presenter.AbstractSearchFormPresenter;
import es.mhp.services.IZipLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 30/03/2016.
 */
@Component
@Scope("session")
public class ZipLocationSearchFormPresenter extends AbstractSearchFormPresenter {

    @Autowired
    private IZipLocationService zipLocationService;

    public HasValue.ValueChangeListener createSearchFormListener(IBrowser browser) {
        return (HasValue.ValueChangeListener) event -> {
            browser.buildBrowser();
            browser.updateAndDisplayGrid(zipLocationService.findAnyZipLocations((String) event.getValue()));
        };
    }

    @Override
    public void updateAndDisplayGrid(IBrowser browser) {
        browser.updateAndDisplayGrid(zipLocationService.findAll());
    }
}

