package es.mhp.browser.impl.ziplocation;

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
public class ZipLocationGridBrowser extends AbstractGridBrowser<ZipLocationDTO> {

    public static final String BEAN_NAME = "zipLocation_grid_browser";

    public ZipLocationGridBrowser() {
        super();
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        this.removeComponent(grid);
        grid = new Grid<>(ZipLocationDTO.class);
        grid.setItems(newDataSource);
        setColumnProperties();

        this.addComponent(grid);
    }

    @Override
    protected void setColumnProperties() {
        grid.setColumnOrder(CITY_FIELD, STATE_FIELD);
        grid.setWidth("60%");
    }
}