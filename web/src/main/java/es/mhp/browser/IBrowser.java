package es.mhp.browser;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.toolbar.IToolbar;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IBrowser {
    void buildBrowser();
    void createForm(Object id, String mode);
    void updateGrid(Set<AbstractDTO> newDataSource);
}
