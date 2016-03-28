/**
 * Created by Edu on 25/03/2016.
 */

import com.vaadin.ui.Button;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.IToolbar;
import es.mhp.toolbar.impl.Toolbar;
import es.mhp.toolbar.presenter.ToolbarPresenter;
import es.mhp.views.AbstractView;
import es.mhp.views.AddressView;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

import static es.mhp.browser.utils.StateType.*;

public class example {

    private IToolbar toolbar;
    private ToolbarPresenter presenter;
    private AbstractView view;

    @Test
    public void checkButtonsInSelectedRowState(){
        initialize(SELECTEDROW);
        Map<ToolButtonType, Button> buttonMap = toolbar.getButtonMap();

        presenter.updateToolbar(toolbar, StateType.SELECTEDROW);
        Assert.assertTrue(buttonMap.get(ToolButtonType.NEW).isVisible());
        Assert.assertTrue(buttonMap.get(ToolButtonType.DELETE).isVisible());
        Assert.assertFalse(buttonMap.get(ToolButtonType.BACK).isVisible());
        Assert.assertFalse(buttonMap.get(ToolButtonType.SAVE).isVisible());
    }

    @Test
    public void checkButtonsInInitialState(){
        initialize(INITIAL);
        Map<ToolButtonType, Button> buttonMap = toolbar.getButtonMap();

        presenter.updateToolbar(toolbar, StateType.INITIAL);
        Assert.assertTrue(buttonMap.get(ToolButtonType.NEW).isVisible());
        Assert.assertFalse(buttonMap.get(ToolButtonType.DELETE).isVisible());
        Assert.assertFalse(buttonMap.get(ToolButtonType.BACK).isVisible());
        Assert.assertFalse(buttonMap.get(ToolButtonType.SAVE).isVisible());
    }

    @Test
    public void checkButtonsInEditState(){
        initialize(EDIT);
        Map<ToolButtonType, Button> buttonMap = toolbar.getButtonMap();

        presenter.updateToolbar(toolbar, StateType.EDIT);
        Assert.assertTrue(buttonMap.get(ToolButtonType.NEW).isVisible());
        Assert.assertTrue(buttonMap.get(ToolButtonType.DELETE).isVisible());
        Assert.assertTrue(buttonMap.get(ToolButtonType.BACK).isVisible());
        Assert.assertTrue(buttonMap.get(ToolButtonType.SAVE).isVisible());
    }

    @Test
    public void checkButtonsInNewState(){
        initialize(NEW);
        Map<ToolButtonType, Button> buttonMap = toolbar.getButtonMap();

        presenter.updateToolbar(toolbar, StateType.NEW);
        Assert.assertFalse(buttonMap.get(ToolButtonType.NEW).isVisible());
        Assert.assertFalse(buttonMap.get(ToolButtonType.DELETE).isVisible());
        Assert.assertTrue(buttonMap.get(ToolButtonType.BACK).isVisible());
        Assert.assertTrue(buttonMap.get(ToolButtonType.SAVE).isVisible());
    }

    private void initialize(StateType stateType) {
        presenter = new ToolbarPresenter();
        toolbar = new Toolbar();
        view = new AddressView();

        presenter.createButtons(view);

        //Ask to Isa if this is correct. I don't like to add a new method to an interface only to allow testing...
        toolbar.setPresenter(presenter);
        toolbar.buildToolbar();
        toolbar.updateToolbar(stateType);
    }
}
