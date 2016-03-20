package es.mhp.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IBrowser;

/**
 * Created by Edu on 23/02/2016.
 */
public abstract class AbstractView<T> extends VerticalLayout implements View {
    protected abstract void createMainLayout();
    public abstract IBrowser getBrowser();
}
