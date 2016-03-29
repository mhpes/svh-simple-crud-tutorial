package es.mhp.toolbar.impl;

import es.mhp.browser.utils.ToolButtonType;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;

import static es.mhp.browser.utils.StateType.*;

/**
 * Created by Edu on 25/03/2016.
 */

@WebAppConfiguration
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class ToolbarTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private IToolbar toolbar;

    @PostConstruct
    private void initialize() {
        toolbar.buildToolbar();
    }

    @Test
    public void checkButtonsInSelectedRowState(){
        toolbar.updateToolbar(SELECTEDROW);
        Assert.assertTrue(toolbar.getButtonMap().get(ToolButtonType.NEW).isVisible());
        Assert.assertTrue(toolbar.getButtonMap().get(ToolButtonType.DELETE).isVisible());
        Assert.assertFalse(toolbar.getButtonMap().get(ToolButtonType.BACK).isVisible());
        Assert.assertFalse(toolbar.getButtonMap().get(ToolButtonType.SAVE).isVisible());
    }

    @Test
    public void checkButtonsInInitialState(){
        toolbar.updateToolbar(INITIAL);
        Assert.assertTrue(toolbar.getButtonMap().get(ToolButtonType.NEW).isVisible());
        Assert.assertFalse(toolbar.getButtonMap().get(ToolButtonType.DELETE).isVisible());
        Assert.assertFalse(toolbar.getButtonMap().get(ToolButtonType.BACK).isVisible());
        Assert.assertFalse(toolbar.getButtonMap().get(ToolButtonType.SAVE).isVisible());
    }

    @Test
    public void checkButtonsInEditState(){
        toolbar.updateToolbar(EDIT);
        Assert.assertTrue(toolbar.getButtonMap().get(ToolButtonType.NEW).isVisible());
        Assert.assertTrue(toolbar.getButtonMap().get(ToolButtonType.DELETE).isVisible());
        Assert.assertTrue(toolbar.getButtonMap().get(ToolButtonType.BACK).isVisible());
        Assert.assertTrue(toolbar.getButtonMap().get(ToolButtonType.SAVE).isVisible());
    }

    @Test
    public void checkButtonsInNewState(){
        toolbar.updateToolbar(NEW);
        Assert.assertFalse(toolbar.getButtonMap().get(ToolButtonType.NEW).isVisible());
        Assert.assertFalse(toolbar.getButtonMap().get(ToolButtonType.DELETE).isVisible());
        Assert.assertTrue(toolbar.getButtonMap().get(ToolButtonType.BACK).isVisible());
        Assert.assertTrue(toolbar.getButtonMap().get(ToolButtonType.SAVE).isVisible());
    }
}
