package es.mhp.browser;


import es.mhp.services.dto.AbstractDTO;

/**
 * Created by Edu on 18/03/2016.
 */

public interface IFormBrowser {
    void createFormBrowser(Object id, String mode);
    void createFieldGroup(AbstractDTO dto);
    void commit();
    AbstractDTO extractBean();
    boolean isModified();
}
