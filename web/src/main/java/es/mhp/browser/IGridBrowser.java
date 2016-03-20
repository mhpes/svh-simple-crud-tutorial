package es.mhp.browser;

import es.mhp.services.dto.AbstractDTO;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Edu on 18/03/2016.
 */

public interface IGridBrowser {
    void updateGrid(Collection<? extends AbstractDTO> newDataSource);
    void addDoubleClickListenerToGrid(IBrowser browser);
}
