package es.mhp.toolbar;

import com.vaadin.ui.Layout;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.toolbar.impl.Toolbar;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IToolbar {
    void buildToolbar(IBrowser browser);
    void updateToolbar(StateType stateType);
}
