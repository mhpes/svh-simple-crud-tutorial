package es.mhp.browser.impl;

import com.vaadin.data.Binder;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import es.mhp.browser.IFormBrowser;
import es.mhp.services.dto.AbstractDTO;


/**
 * Created by Edu on 18/03/2016.
 */
public abstract class AbstractFormBrowser<T> extends VerticalLayout implements IFormBrowser {

    //Ask to Isa if this is correct
    protected FormLayout form;

    protected Binder<AbstractDTO> binder;

    public AbstractFormBrowser() {
        form = new FormLayout();
        this.addComponent(form);

        this.setSizeFull();
        this.setMargin(true);
    }

    @Override
    public void createFieldGroup(AbstractDTO dto) {
        binder = new Binder(dto.getClass());
        binder.setBean(dto);
    }

    @Override
    public void commit() {
        binder.validate();
    }

    @Override
    public AbstractDTO extractBean() {
        return binder.getBean();
    }

    @Override
    public boolean isModified() {
        return binder.hasChanges();
    }

    protected abstract void bindForm(Object dto, String mode);

    protected TextField buildAndBindTextField(String propertyId, Boolean isRequired) {
        TextField tf = new TextField(propertyId);
        binder.bind(tf, propertyId);
        return tf;
    }

    protected ColorPicker buildAndBindColorPicker(String propertyId, Boolean isRequired) {
        ColorPicker cp = new ColorPicker(propertyId);
        cp.setRequiredIndicatorVisible(Boolean.TRUE);
        binder.bind(cp, propertyId);
        return cp;
    }
}