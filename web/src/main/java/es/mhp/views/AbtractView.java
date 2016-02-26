package es.mhp.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Edu on 23/02/2016.
 */
public abstract class AbtractView<T> extends VerticalLayout implements View {
    protected abstract Layout createTable();
    protected abstract Layout createForm(T entity);
}
