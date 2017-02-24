package es.mhp.toolbar;

import com.vaadin.ui.HorizontalLayout;

/**
 * Created by Edu on 20/03/2016.
 */
public abstract class AbstractToolbar extends HorizontalLayout implements IToolbar{

    public AbstractToolbar() {
        this.setMargin(true);
    }
}
