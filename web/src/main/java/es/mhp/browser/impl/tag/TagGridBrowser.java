package es.mhp.browser.impl.tag;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.TagDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static es.mhp.views.utils.TagViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(TagGridBrowser.BEAN_NAME)
@Scope("prototype")
public class TagGridBrowser extends AbstractGridBrowser {

    public static final String BEAN_NAME = "tag_grid_browser";

    private Grid grid;

    public TagGridBrowser() {
        super();
        grid = new Grid();
        this.addComponent(grid);
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        grid.removeAllColumns();

        BeanItemContainer<TagDTO> tagBeanItemContainer = new BeanItemContainer<>(TagDTO.class, (Collection<? extends TagDTO>) newDataSource);
        grid.setContainerDataSource(tagBeanItemContainer);
        setColumnProperties();

        this.addComponent(grid);
    }

    @Override
    protected void setColumnProperties() {
        grid.removeColumn(TAGID_FIELD);
        grid.setColumnOrder(TAG_FIELD, REFCOUNT_FIELD);
        grid.setWidth("60%");
    }

    @Override
    public TagDTO getSelectedGridRow(){
        return (TagDTO) grid.getSelectedRow();
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
