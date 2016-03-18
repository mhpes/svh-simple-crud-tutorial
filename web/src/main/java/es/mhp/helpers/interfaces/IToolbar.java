package es.mhp.helpers.interfaces;

import com.vaadin.ui.VerticalLayout;
import es.mhp.helpers.StateType;
import es.mhp.helpers.interfaces.impl.ToolbarImpl;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IToolbar {
    void setButtonVisibilityByState(StateType state);
    ToolbarImpl getToolbarLayout();
    void setButtonsInvisible();
    void buildToolbar(VerticalLayout tableLayout);
}
