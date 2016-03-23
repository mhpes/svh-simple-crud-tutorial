package es.mhp.browser.impl.zipLocation;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static es.mhp.views.utils.ZipLocationViewConstants.CITY_FIELD;
import static es.mhp.views.utils.ZipLocationViewConstants.STATE_FIELD;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ZipLocationGridBrowser.BEAN_NAME)
@Scope("prototype")
public class ZipLocationGridBrowser extends AbstractGridBrowser {

    public static final String BEAN_NAME = "zipLocation_grid_browser";

    private Grid grid;

    public ZipLocationGridBrowser() {
        super();
        grid = new Grid();
        this.addComponent(grid);
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        grid.removeAllColumns();

        BeanItemContainer<ZipLocationDTO> zipLocationBeanItemContainer = new BeanItemContainer<>(ZipLocationDTO.class, (Collection<? extends ZipLocationDTO>) newDataSource);
        grid.setContainerDataSource(zipLocationBeanItemContainer);
        setColumnProperties();

        this.addComponent(grid);
    }

    @Override
    protected void setColumnProperties() {
        grid.setColumnOrder(CITY_FIELD, STATE_FIELD);
        grid.setWidth("60%");
    }

    @Override
    public ZipLocationDTO getSelectedGridRow(){
        return (ZipLocationDTO) grid.getSelectedRow();
    }

    @Override
    public void deleteEntry() {
        if (grid.getSelectedRow() != null) {
            grid.getContainerDataSource().removeItem(grid.getSelectedRow());
        }
    }

    @Override
    public void updateGrid() {
        this.removeAllComponents();
        this.addComponent(grid);
    }

    @Override
    public void updateGrid(AbstractDTO dto) {
        if (grid.getContainerDataSource().containsId(dto)) {
            grid.getContainerDataSource().removeItem(dto);
        }
        grid.getContainerDataSource().addItem(dto);
        grid.select(dto);
    }

    @Override
    protected Grid getGrid() {
        return grid;
    }
}
