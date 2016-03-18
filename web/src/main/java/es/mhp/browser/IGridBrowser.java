package es.mhp.browser;

import es.mhp.browser.impl.AddressGridBrowser;
import es.mhp.services.dto.AddressDTO;

import java.util.Set;

/**
 * Created by Edu on 18/03/2016.
 */

public interface IGridBrowser {
    void fillGrid(Set<T> newDataSource);
}
