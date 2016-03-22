package es.mhp.browser.impl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.browser.utils.StateType;
import es.mhp.services.dto.AbstractDTO;


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
    public void createFieldGroup(AbstractDTO dto){
        fieldGroup = new BeanFieldGroup<>(dto.getClass());
        BeanItem item = new BeanItem(dto);
        fieldGroup.setItemDataSource(item);
        fieldGroup.setBuffered(true);
    }

    @Override
    public AbstractDTO extractBean() throws FieldGroup.CommitException {
        fieldGroup.commit();
        return fieldGroup.getItemDataSource().getBean();
    }

    protected StateType getStateType(String mode) {
        return mode.equals(FormBrowserUtils.EDIT_MODE) ? StateType.EDIT : StateType.NEW;
    }

    protected abstract void setItemProperty(AbstractDTO dto);
    protected abstract void bindForm(AbstractDTO dto, String mode);

}
