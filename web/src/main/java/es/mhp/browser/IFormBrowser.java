package es.mhp.browser;


import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.FormLayout;
import es.mhp.services.dto.AbstractDTO;

/**
 * Created by Edu on 18/03/2016.
 */

public interface IFormBrowser {
    void createFormBrowser(Object id, String mode);
    AbstractDTO getNewForm();
    FormLayout getForm();
    void createFieldGroup(AbstractDTO dto);
    AbstractDTO extractBean() throws FieldGroup.CommitException;
}
