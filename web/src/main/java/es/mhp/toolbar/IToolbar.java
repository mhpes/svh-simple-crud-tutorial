package es.mhp.toolbar;

import es.mhp.browser.utils.StateType;
import es.mhp.toolbar.impl.Toolbar;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IToolbar {
    void setButtonVisibilityByState(StateType state);
    Toolbar getToolbarLayout();
    void setButtonsInvisible();
    Toolbar buildToolbar();
    void updateToolbar(StateType stateType);
}
