package es.mhp.toolbar.impl;

import com.vaadin.ui.Button;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.AbstractToolbar;
import es.mhp.views.AbstractView;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component
@Scope("prototype")
public class Toolbar extends AbstractToolbar {

    private Map<ToolButtonType, Button> buttonMap;

    public Toolbar() {
        super();
        this.buttonMap = new LinkedHashMap<>();
    }

    //Has to be PostConstruct because we have to create the buttons before to use this class
    @PostConstruct
    @Override
    public void buildToolbar(){
        this.removeAllComponents();
        this.buttonMap.putAll(createButtons((AbstractView)getParent()));

        for (Button button : buttonMap.values()) {
            this.addComponent(button);
        }
    }

    public Map<ToolButtonType,Button> createButtons(AbstractView view) {
        Map<ToolButtonType, Button> buttonMap = new LinkedHashMap<>();
        buttonMap.put(ToolButtonType.NEW, createNewButton(view));
        buttonMap.put(ToolButtonType.SAVE, createSaveButton(view));
        buttonMap.put(ToolButtonType.DELETE, createDeleteButton(view));
        buttonMap.put(ToolButtonType.BACK, createBackButton(view));
        return buttonMap;
    }

    private Button createBackButton(AbstractView view) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent ->
                view.updateView(StateType.INITIAL);
        return new Button(ToolButtonType.BACK.toString(), listener);
    }

    private Button createDeleteButton(AbstractView view) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent ->
                view.updateView(StateType.DELETE);
        return new Button(ToolButtonType.DELETE.toString(), listener);
    }

    private Button createSaveButton(AbstractView view) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent ->
                view.updateView(StateType.SAVE);
        return new Button(ToolButtonType.SAVE.toString(), listener);
    }

    private Button createNewButton(AbstractView view) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent ->
                view.updateView(StateType.NEW);
        return new Button(ToolButtonType.NEW.toString(), listener);
    }

    public void updateToolbar(StateType stateType) {
        this.setAllButtonsNotVisible();
        this.setButtonVisibleByState(stateType);
    }

    private void setAllButtonsNotVisible(){
        getButtonMap().forEach(
                (buttonType, button) -> button.setVisible(false));
    }


    private void setButtonVisibleByState(StateType state){
        switch (state){
            case SELECTEDROW:
                setButtonVisible(ToolButtonType.NEW);
                setButtonVisible(ToolButtonType.DELETE);
                break;
            case INITIAL:
                setButtonVisible(ToolButtonType.NEW);
                break;
            case EDIT:
                setButtonVisible(ToolButtonType.BACK);
                setButtonVisible(ToolButtonType.NEW);
                setButtonVisible(ToolButtonType.DELETE);
                setButtonVisible(ToolButtonType.SAVE);
                break;
            case NEW:
                setButtonVisible(ToolButtonType.BACK);
                setButtonVisible(ToolButtonType.SAVE);
                break;
            default:
                break;
        }
    }

    private void setButtonVisible(ToolButtonType buttonType){
        getButtonMap().get(buttonType).setVisible(true);
    }

    @Override
    public Map<ToolButtonType, Button> getButtonMap() {
        return buttonMap;
    }
}