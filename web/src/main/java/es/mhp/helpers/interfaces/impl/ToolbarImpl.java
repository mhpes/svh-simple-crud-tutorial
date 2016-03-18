package es.mhp.helpers.interfaces.impl;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import es.mhp.helpers.StateType;
import es.mhp.helpers.ToolButtonType;
import es.mhp.helpers.interfaces.IToolbar;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Edu on 17/03/2016.
 */

@Component
public class ToolbarImpl extends HorizontalLayout implements IToolbar {

    private HashMap<ToolButtonType, Button> buttonMap;

    public void buildToolbar(VerticalLayout tableLayout){
        buttonMap = new LinkedHashMap<>();

        this.buttonMap.put(ToolButtonType.NEW, createNewButton(tableLayout));
        this.buttonMap.put(ToolButtonType.SAVE, new Button(ToolButtonType.SAVE.toString()));
        this.buttonMap.put(ToolButtonType.DELETE, new Button(ToolButtonType.DELETE.toString()));
        this.buttonMap.put(ToolButtonType.BACK, new Button(ToolButtonType.BACK.toString()));

        for (Button button : buttonMap.values()) {
            button.setVisible(false);
            this.addComponent(button);
        }
    }

    private Button createNewButton(VerticalLayout tableLayout) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            tableLayout.removeAllComponents();
            //tableLayout.addComponent(createForm(new AddressDTO(), NEW_MODE));
        };
        return new Button(ToolButtonType.NEW.toString(), listener);
    }

    private void setButtonVisibility(ToolButtonType buttonType){
        getButtonMap().get(buttonType).setVisible(true);
    }

    public void setButtonsInvisible(){
        getButtonMap().forEach(
                (buttonType, button) -> button.setVisible(false));
    }

    @Override
    public void setButtonVisibilityByState(StateType state){
        setButtonsInvisible();

        switch (state){
            case SELECTEDROW:
                setButtonVisibility(ToolButtonType.NEW);
                setButtonVisibility(ToolButtonType.DELETE);
                break;
            case INITIAL:
                setButtonVisibility(ToolButtonType.NEW);
                break;
            case EDIT:
                setButtonVisibility(ToolButtonType.NEW);
                setButtonVisibility(ToolButtonType.DELETE);
                setButtonVisibility(ToolButtonType.BACK);
                break;
            case NEW:
                setButtonVisibility(ToolButtonType.NEW);
                setButtonVisibility(ToolButtonType.BACK);
                break;
        }
    }

    @Override
    public ToolbarImpl getToolbarLayout() {
        return this;
    }

    public ToolbarImpl() { }

    private HashMap<ToolButtonType, Button> getButtonMap() {
        return buttonMap;
    }

    private void setButtonMap(HashMap<ToolButtonType, Button> buttonMap) {
        this.buttonMap = buttonMap;
    }
}
