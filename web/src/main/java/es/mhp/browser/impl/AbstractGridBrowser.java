package es.mhp.browser.impl;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.presenter.GridBrowserPresenter;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Edu on 18/03/2016.
 */
public abstract class AbstractGridBrowser<T extends AbstractDTO> extends VerticalLayout implements IGridBrowser {

    @Autowired
    protected GridBrowserPresenter gridBrowserPresenter;

    protected Grid grid;

    public AbstractGridBrowser() {
        this.setSizeFull();
        this.setMargin(true);
        grid = new Grid();
        this.addComponent(grid);
    }

    @Override
    public void configure() {
        //Ask to Isa: (AbstractView)getGrid().getParent().getParent() is it correct? I think is not a good thing to do this: this.getGrid().getParent()
        gridBrowserPresenter.addDoubleClickListenerToGrid(getGrid(), (AbstractView)this.getGrid().getParent().getParent().getParent());
    }

    @Override
    public void deleteEntry() {
        if (grid.getSelectedItems() != null) {
            grid.getSelectedItems().remove(grid.getSelectedItems());
        }
    }

    @Override
    public void updateGrid() {
        this.removeAllComponents();
        this.addComponent(grid);
    }

    @Override
    public void updateGrid(AbstractDTO dto) {
        if (grid.getSelectedItems().contains(dto)) {
            grid.getSelectedItems().remove(dto);
        }
        grid.getSelectedItems().add(dto);
        grid.select(dto);
    }

    @Override
    public T getSelectedGridRow(){
        return (T) grid.getSelectedItems();
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    protected abstract void setColumnProperties();
}
