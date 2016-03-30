package es.mhp.toolbar;

import com.vaadin.ui.Button;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;

import java.util.Map;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IToolbar {

    /**
     *
     */
    void buildToolbar();

    /**
     *
     * @param stateType
     */
    void updateToolbar(StateType stateType);

    /**
     *
     * @return
     */
    Map<ToolButtonType, Button> getButtonMap();
}
