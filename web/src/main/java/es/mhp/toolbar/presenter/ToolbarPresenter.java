package es.mhp.toolbar.presenter;

import com.vaadin.ui.Button;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.IToolbar;
import es.mhp.views.AbstractView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Edu on 23/03/2016.
 */
@Component
@Scope("session")
public class ToolbarPresenter {

    public Map<? extends ToolButtonType,? extends Button> createButtons(AbstractView view) {
        Map<ToolButtonType, Button> buttonMap = new LinkedHashMap<>();
        buttonMap.put(ToolButtonType.NEW, createNewButton(view));
        buttonMap.put(ToolButtonType.SAVE, createSaveButton(view));
        buttonMap.put(ToolButtonType.DELETE, createDeleteButton(view));
        buttonMap.put(ToolButtonType.BACK, createBackButton(view));
        return buttonMap;
    }

    private Button createBackButton(AbstractView view) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            view.updateView(StateType.INITIAL);
        };
        return new Button(ToolButtonType.BACK.toString(), listener);
    }

    private Button createDeleteButton(AbstractView view) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            view.updateView(StateType.DELETE);
        };
        return new Button(ToolButtonType.DELETE.toString(), listener);
    }

    private Button createSaveButton(AbstractView view) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            view.updateView(StateType.SAVE);
        };
        return new Button(ToolButtonType.SAVE.toString(), listener);
    }

    private Button createNewButton(AbstractView view) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            view.updateView(StateType.NEW);
        };
        return new Button(ToolButtonType.NEW.toString(), listener);
    }

    public void updateToolbar(IToolbar toolbar, StateType stateType) {
        this.setAllButtonsNotVisible(toolbar);
        this.setButtonVisibleByState(toolbar, stateType);
    }

    private void setAllButtonsNotVisible(IToolbar toolbar){
        toolbar.getButtonMap().forEach(
                (buttonType, button) -> button.setVisible(false));
    }


    private void setButtonVisibleByState(IToolbar toolbar, StateType state){
        switch (state){
            case SELECTEDROW:
                setButtonVisible(ToolButtonType.NEW, toolbar);
                setButtonVisible(ToolButtonType.DELETE, toolbar);
                break;
            case INITIAL:
                setButtonVisible(ToolButtonType.NEW, toolbar);
                break;
            case EDIT:
                setButtonVisible(ToolButtonType.BACK, toolbar);
                setButtonVisible(ToolButtonType.NEW, toolbar);
                setButtonVisible(ToolButtonType.DELETE, toolbar);
                setButtonVisible(ToolButtonType.SAVE, toolbar);
                break;
            case NEW:
                setButtonVisible(ToolButtonType.BACK, toolbar);
                setButtonVisible(ToolButtonType.SAVE, toolbar);
                break;
        }
    }

    private void setButtonVisible(ToolButtonType buttonType, IToolbar toolbar){
        toolbar.getButtonMap().get(buttonType).setVisible(true);
    }
}