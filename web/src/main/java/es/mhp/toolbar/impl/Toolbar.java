package es.mhp.toolbar.impl;

import com.vaadin.ui.Button;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.AbstractToolbar;
import es.mhp.toolbar.presenter.ToolbarPresenter;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component
@Scope("prototype")
public class Toolbar extends AbstractToolbar {

    private Map<ToolButtonType, Button> buttonMap;

    @Autowired
    private ToolbarPresenter presenter;

    public Toolbar() {
        super();
        this.buttonMap = new LinkedHashMap<>();
    }

    public void buildToolbar(IBrowser browser){
        this.removeAllComponents();
        this.buttonMap.putAll(presenter.createButtons((AbstractView)getParent()));

        for (Button button : buttonMap.values()) {
            this.addComponent(button);
        }
    }

    @Override
    public void updateToolbar(StateType stateType) {
        presenter.updateToolbar(this, stateType);
    }

    @Override
    public Map<ToolButtonType, Button> getButtonMap() {
        return buttonMap;
    }

    private void setButtonMap(Map<ToolButtonType, Button> buttonMap) {
        this.buttonMap = buttonMap;
    }
}
