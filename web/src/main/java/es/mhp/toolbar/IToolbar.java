package es.mhp.toolbar;

import com.vaadin.ui.Button;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.presenter.ToolbarPresenter;

import java.util.Map;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IToolbar {
    void buildToolbar();
    void setPresenter(ToolbarPresenter presenter);
    void updateToolbar(StateType stateType);
    Map<ToolButtonType, Button> getButtonMap();
}
