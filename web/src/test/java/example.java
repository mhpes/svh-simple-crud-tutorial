/**
 * Created by Edu on 25/03/2016.
 */

import com.vaadin.ui.Button;
import es.mhp.browser.utils.StateType;
import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.IToolbar;
import es.mhp.toolbar.presenter.ToolbarPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class example {

    @Autowired
    protected IToolbar toolbar;

    @Autowired
    private ToolbarPresenter presenter;

    Map<ToolButtonType, Button> buttonMapTestSelectedRow;

    @Test
    public void checkButtonsInSelectedRowState(){
        /*presenter.updateToolbar(toolbar, StateType.SELECTEDROW);
        Map<ToolButtonType, Button> buttonMap = toolbar.getButtonMap();

        Assert.assertEquals(buttonMap, buttonMapTestSelectedRow);*/
    }
}
