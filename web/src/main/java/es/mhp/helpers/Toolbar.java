package es.mhp.helpers;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Edu on 17/03/2016.
 */
public class Toolbar extends HorizontalLayout {

    private HashMap<ToolButtonType, Button> buttonMap;

    public Toolbar(VerticalLayout tableLayout) {
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
        Button.ClickListener listener = null;
        return new Button(ToolButtonType.NEW.toString(), listener);

//        newAddressButton.addClickListener((Button.ClickListener) event -> {
//            addressTable.removeAllComponents();
//            addressTable.addComponent(
//                    createForm(new AddressDTO(), NEW_MODE)
//            );
//        });
    }

    private void setButtonVisibility(ToolButtonType buttonType){
        getButtonMap().get(buttonType).setVisible(true);
    }

    public void setButtonsInvisible(){
        getButtonMap().forEach((buttonType, button) -> button.setVisible(false));
    }

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

    public HashMap<ToolButtonType, Button> getButtonMap() {
        return buttonMap;
    }

    public void setButtonMap(HashMap<ToolButtonType, Button> buttonMap) {
        this.buttonMap = buttonMap;
    }
}
