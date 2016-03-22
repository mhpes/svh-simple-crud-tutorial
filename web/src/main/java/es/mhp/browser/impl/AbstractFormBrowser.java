package es.mhp.browser.impl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IFormBrowser;
import es.mhp.services.dto.AbstractDTO;
import org.apache.commons.lang.StringUtils;


/**
 * Created by Edu on 18/03/2016.
 */
public abstract class AbstractFormBrowser extends VerticalLayout  implements IFormBrowser {

    //Ask to Isa if this is correct
    protected FormLayout form;

    protected BeanFieldGroup<? extends AbstractDTO> fieldGroup;

    public AbstractFormBrowser() {
        form = new FormLayout();
        this.addComponent(form);

        this.setSizeFull();
        this.setMargin(true);
    }

    @Override
    public FormLayout getForm() {
        return form;
    }

    @Override
    public void createFieldGroup(BeanItem beanItem){
        fieldGroup = new BeanFieldGroup(beanItem.getBean().getClass());
        fieldGroup.setItemDataSource(beanItem);
    }

    @Override
    public void commit() throws FieldGroup.CommitException {
        fieldGroup.commit();
    }

    @Override
    public AbstractDTO extractBean() {
        return fieldGroup.getItemDataSource().getBean();
    }

    @Override
    public boolean isModified() {
        return fieldGroup.isModified();
    }

    protected abstract BeanItem createBeanItem(AbstractDTO dto);

    protected abstract void bindForm(Object dto, String mode);

    protected TextField buildAndBindTextField(String propertyId, Boolean isRequired) {
        TextField tf = (TextField) fieldGroup.buildAndBind(propertyId);
        tf.setNullRepresentation(StringUtils.EMPTY);
        tf.setRequired(isRequired);
        tf.setImmediate(true);
        return tf;
    }
}
