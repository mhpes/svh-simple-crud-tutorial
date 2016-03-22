package es.mhp.browser;

import es.mhp.exceptions.UIException;
import es.mhp.services.dto.AbstractDTO;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IBrowser {
    void buildBrowser();
    void createAndDisplayForm(String mode);
    boolean saveItemAndUpdateGrid() throws UIException;
    void deleteItemAndUpdateGrid() throws UIException;
    void updateGrid(Set<AbstractDTO> newDataSource);
    void displayGridAndHideForm();
    void displayFormAndHideGrid();
}
