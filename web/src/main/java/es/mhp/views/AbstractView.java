package es.mhp.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Edu on 23/02/2016.
 */
public abstract class AbstractView<T> extends VerticalLayout implements View {

    @Autowired
    protected IToolbar toolbar;

    abstract void createMainLayout();

    public void updateToolbar(StateType stateType) {
        toolbar.updateToolbar(stateType);
    }
}
