package es.mhp.toolbar.impl;

import com.vaadin.ui.Button;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.AbstractToolbar;
import es.mhp.views.AbstractView;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component
public class Toolbar extends AbstractToolbar {

    private HashMap<ToolButtonType, Button> buttonMap;

    public Toolbar() {
        super();
        this.buttonMap = new LinkedHashMap<>();
    }

    public void buildToolbar(IBrowser browser){
        this.removeAllComponents();
        this.buttonMap.put(ToolButtonType.NEW, createNewButton());
        this.buttonMap.put(ToolButtonType.SAVE, createSaveButton(browser));
        this.buttonMap.put(ToolButtonType.DELETE, createDeleteButton());
        this.buttonMap.put(ToolButtonType.BACK, createBackButton());

        for (Button button : buttonMap.values()) {
            this.addComponent(button);
        }

        updateToolbar(StateType.INITIAL);
    }

    private Button createBackButton() {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            ((AbstractView)getParent()).updateView(StateType.INITIAL);
        };
        return new Button(ToolButtonType.BACK.toString(), listener);
    }

    private Button createDeleteButton() {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            ((AbstractView)getParent()).updateView(StateType.DELETE);
        };
        return new Button(ToolButtonType.DELETE.toString(), listener);
    }

    private Button createSaveButton(IBrowser browser) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            ((AbstractView)getParent()).updateView(StateType.SAVE);
        };
        return new Button(ToolButtonType.SAVE.toString(), listener);
    }

    private Button createNewButton() {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            ((AbstractView)getParent()).updateView(StateType.NEW);
        };
        return new Button(ToolButtonType.NEW.toString(), listener);
    }

    @Override
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
        }
    }

    private void setButtonVisible(ToolButtonType buttonType){
        getButtonMap().get(buttonType).setVisible(true);
    }

    private HashMap<ToolButtonType, Button> getButtonMap() {
        return buttonMap;
    }

    private void setButtonMap(HashMap<ToolButtonType, Button> buttonMap) {
        this.buttonMap = buttonMap;
    }





    /* PONER ESTOS BOTONES CON SUS LISTENERS!*/

    /*private Button createCancelButton(FormLayout form, FieldGroup binder) {
        Button cancelButton = new Button(CANCEL, FontAwesome.TRASH_O);
        binder.discard();

        cancelButton.addClickListener((Button.ClickListener) event ->
                form.removeAllComponents()
        );

        return cancelButton;
    }

    private Button createSaveButton(FieldGroup addressFieldGroup) {
        final Button saveButton = new Button(SAVE, FontAwesome.SAVE);

        saveButton.addClickListener((Button.ClickListener) event ->
                trySaveAddress(addressFieldGroup));

        return saveButton;
    }

    private Button createDeleteButton(AddressDTO addressDTO){
        final Button deleteButton = new Button(DELETE_ENTRY);

        deleteButton.addClickListener((Button.ClickListener) event ->
                iAddressService.delete(addressDTO));

        return deleteButton;
    }*/
}
