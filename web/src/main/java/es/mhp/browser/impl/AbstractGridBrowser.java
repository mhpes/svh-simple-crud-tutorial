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

    @Override
    public void configure() {
        //Ask to Isa: (AbstractView)getGrid().getParent().getParent() is it correct? I think is not a good thing to do this: this.getGrid().getParent()
        presenter.addDoubleClickListenerToGrid(getGrid(), (AbstractView)this.getGrid().getParent().getParent().getParent());
    }

    protected abstract Grid getGrid();

    protected abstract void setColumnProperties();
}
