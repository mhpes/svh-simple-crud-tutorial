package es.mhp.browser.impl;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.presenter.GridBrowserPresenter;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Edu on 18/03/2016.
 */
public abstract class AbstractGridBrowser extends VerticalLayout implements IGridBrowser {

    @Autowired
    protected GridBrowserPresenter presenter;

    public AbstractGridBrowser() {
        this.setSizeFull();
        this.setMargin(true);
    }

    abstract protected void setColumnProperties();

    @Override
    public void configure() {
        presenter.addDoubleClickListenerToGrid(getGrid(), (AbstractView)getParent().getParent());
    }

    protected abstract Grid getGrid();
}
