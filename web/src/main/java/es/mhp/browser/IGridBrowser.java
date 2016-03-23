package es.mhp.browser;

import es.mhp.services.dto.AbstractDTO;

import java.util.Collection;

/**
 * Created by Edu on 18/03/2016.
 */

public interface IGridBrowser {
    void configure();
    Object getSelectedGridRow();
    void deleteEntry();
    void updateGrid();
    void updateGrid(AbstractDTO id);
    void updateGrid(Collection<? extends AbstractDTO> newDataSource);
}
