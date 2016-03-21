package es.mhp.browser;

import es.mhp.services.dto.AbstractDTO;

import java.util.Collection;

/**
 * Created by Edu on 18/03/2016.
 */

public interface IGridBrowser {
    void updateGrid(Collection<? extends AbstractDTO> newDataSource);
    void addDoubleClickListenerToGrid();
    Object getSelectedGridRow();
    void deleteEntry();
    void updateGrid();
    void updateAndDisplayGrid(AbstractDTO id);
}
