package es.mhp.toolbar.impl;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.IToolbar;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static es.mhp.browser.utils.FormBrowserUtils.NEW_MODE;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component
public class Toolbar extends HorizontalLayout implements IToolbar {

    private HashMap<ToolButtonType, Button> buttonMap;

    public Toolbar() {
        this.buttonMap = new LinkedHashMap<>();
    }

    public void buildToolbar(IBrowser browser){
        this.buttonMap.put(ToolButtonType.NEW, createNewButton(browser));
        this.buttonMap.put(ToolButtonType.SAVE, new Button(ToolButtonType.SAVE.toString()));
        this.buttonMap.put(ToolButtonType.DELETE, new Button(ToolButtonType.DELETE.toString()));
        this.buttonMap.put(ToolButtonType.BACK, new Button(ToolButtonType.BACK.toString()));

        for (Button button : buttonMap.values()) {
            this.addComponent(button);
        }
    }

    private Button createNewButton(IBrowser browser) {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            browser.createForm(null, NEW_MODE);
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


    public void setButtonVisibleByState(StateType state){
        switch (state){
            case SELECTEDROW:
                setButtonVisible(ToolButtonType.NEW);
                setButtonVisible(ToolButtonType.DELETE);
                break;
            case INITIAL:
                setButtonVisible(ToolButtonType.NEW);
                break;
            case EDIT:
                setButtonVisible(ToolButtonType.NEW);
                setButtonVisible(ToolButtonType.DELETE);
                setButtonVisible(ToolButtonType.BACK);
                break;
            case NEW:
                setButtonVisible(ToolButtonType.NEW);
                setButtonVisible(ToolButtonType.BACK);
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

    /*private Button createNewAddressButton(boolean isVisible) {
        Button newAddressButton = new Button("New");
        newAddressButton.setVisible(isVisible);

        newAddressButton.addClickListener((Button.ClickListener) event -> {
            addressTableLayout.removeAllComponents();
            addressTableLayout.addComponent(
                    createForm(new AddressDTO(), NEW_MODE)
            );
        });

        newAddressButton.setVisible(true);
        return newAddressButton;
    }*/

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
