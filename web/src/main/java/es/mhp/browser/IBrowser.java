package es.mhp.browser;

import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */
public interface IBrowser {
    void buildBrowser();
    void createForm(Object id, String mode);
    void saveFormData(AbstractDTO entityDto);
    void deleteFormData(Object id);
    void updateGrid(Set<AbstractDTO> newDataSource);
    AddressDTO getSelectedFormRow();
    Object getSelectedGridRow();
}
