package es.mhp.toolbar.impl;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.IToolbar;
import es.mhp.views.AbstractView;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static es.mhp.browser.impl.utils.AddressFormUtils.NEW_MODE;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component
public class Toolbar extends HorizontalLayout implements IToolbar {

    private HashMap<ToolButtonType, Button> buttonMap;

    public Toolbar() { }

//    public Toolbar buildToolbar(AbstractBrowser browserLayout){
    public Toolbar buildToolbar(){

        buttonMap = new LinkedHashMap<>();

//        this.buttonMap.put(ToolButtonType.NEW, createNewButton(browserLayout));
        this.buttonMap.put(ToolButtonType.NEW, createNewButton());
        this.buttonMap.put(ToolButtonType.SAVE, new Button(ToolButtonType.SAVE.toString()));
        this.buttonMap.put(ToolButtonType.DELETE, new Button(ToolButtonType.DELETE.toString()));
        this.buttonMap.put(ToolButtonType.BACK, new Button(ToolButtonType.BACK.toString()));

        for (Button button : buttonMap.values()) {
            button.setVisible(false);
            this.addComponent(button);
        }

        return this;
    }

//    private Button createNewButton(AbstractBrowser browserLayout) {
    private Button createNewButton() {
        Button.ClickListener listener = (Button.ClickListener) clickEvent -> {
            //browserLayout.getiFormBrowser().addComponent(createForm(new AddressDTO(), NEW_MODE));
            ((AbstractView)this.getParent()).getiBrowser().createForm(null, NEW_MODE);
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
    public Toolbar getToolbarLayout() {
        return this;
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
