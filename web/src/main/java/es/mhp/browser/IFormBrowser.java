package es.mhp.browser;


import es.mhp.services.dto.AddressDTO;

/**
 * Created by Edu on 18/03/2016.
 */

public interface IFormBrowser {
    void createFormBrowser(Object id, String mode);
    AddressDTO getNewForm();
}
