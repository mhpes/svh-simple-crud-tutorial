package es.mhp.browser;


import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import es.mhp.services.dto.AbstractDTO;

/**
 * Created by Edu on 18/03/2016.
 */

public interface IFormBrowser {
    void createFormBrowser(Object id, String mode);
    void createFieldGroup(BeanItem beanItem);
    void commit() throws FieldGroup.CommitException;
    AbstractDTO extractBean();
    boolean isModified();
}
