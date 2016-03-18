package es.mhp.helpers.interfaces.impl;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import es.mhp.helpers.interfaces.IBrowser;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component
public class BrowserImpl extends VerticalLayout implements IBrowser {

    private VerticalLayout tableLayout;
    private VerticalLayout formLayout;

    public BrowserImpl() {
        this.tableLayout = new VerticalLayout();
        this.formLayout = new VerticalLayout();

        setVisibility(this.tableLayout, true);
        setVisibility(this.formLayout, false);
    }

    @Override
    public VerticalLayout getTableLayout() { return tableLayout; }

    public void setTableLayout(VerticalLayout tableLayout) {
        this.tableLayout = tableLayout;
    }

    @Override
    public VerticalLayout getFormLayout() {
        return formLayout;
    }

    public void setFormLayout(VerticalLayout formLayout) {
        this.formLayout = formLayout;
    }

    @Override
    public void setVisibility(Component component, boolean visibility) {
        component.setVisible(visibility);
    }
}
