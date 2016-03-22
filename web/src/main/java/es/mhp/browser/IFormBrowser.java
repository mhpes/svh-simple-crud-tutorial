package es.mhp.browser;


import com.vaadin.data.fieldgroup.FieldGroup;
import es.mhp.services.dto.AbstractDTO;

/**
 * Created by Edu on 18/03/2016.
 */

public interface IFormBrowser {
    void createFieldGroup(AbstractDTO dto);
    void createFormBrowser(AbstractDTO dto, String mode);
    AbstractDTO extractBean() throws FieldGroup.CommitException;
}
