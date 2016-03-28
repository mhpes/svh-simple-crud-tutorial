package es.mhp.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.exceptions.UIException;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;

import static es.mhp.browser.utils.FormBrowserUtils.EDIT_MODE;
import static es.mhp.browser.utils.FormBrowserUtils.NEW_MODE;

/**
 * Created by Edu on 23/02/2016.
 */
public abstract class AbstractView extends VerticalLayout implements View {

    @Autowired
    protected IToolbar toolbar;

    public AbstractView(){
        this.setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponentsToView();
        configureComponents();
    }

    abstract void addComponentsToView();

    abstract void configureComponents();

    public void updateToolbar(StateType stateType) {
        toolbar.updateToolbar(stateType);
    }

    public void updateView(StateType state) {
        try {
            switch (state) {
                case INITIAL:
                    //getBrowser().displayGridAndHideForm();
                    toolbar.updateToolbar(StateType.INITIAL);
                    break;
                case NEW:
                    getBrowser().createAndDisplayForm(NEW_MODE);
                    toolbar.updateToolbar(StateType.NEW);
                    break;
                case EDIT:
                    getBrowser().createAndDisplayForm(EDIT_MODE);
                    toolbar.updateToolbar(StateType.EDIT);
                    break;
                case SAVE:
                    Boolean isSaved = true;/*getBrowser().saveItemAndUpdateGrid();*/
                    if (isSaved) {
                        toolbar.updateToolbar(StateType.SELECTEDROW);
                        Notification.show("item saved correctly!", Notification.Type.HUMANIZED_MESSAGE);
                    } else {
                        Notification.show("Nothing to save", Notification.Type.HUMANIZED_MESSAGE);
                    }
                    break;
                case SELECTEDROW:
                    //getBrowser().displayGridAndHideForm();
                    toolbar.updateToolbar(StateType.SELECTEDROW);
                    break;
                case DELETE:
                    /*getBrowser().deleteItemAndUpdateGrid();
                    getBrowser().displayGridAndHideForm();*/
                    toolbar.updateToolbar(StateType.INITIAL);
                    Notification.show("item deleted correctly!", Notification.Type.HUMANIZED_MESSAGE);
                    break;
                default:
                    break;
            }
        } catch (Exception e/*UIException e*/) {
            Notification.show(/*e.getMessage()*/"", Notification.Type.ERROR_MESSAGE);
        }
    }

    abstract IBrowser getBrowser();
}
