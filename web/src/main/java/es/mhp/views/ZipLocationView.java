/*
package es.mhp.views;

*/
/**
 * Created by Edu on 24/02/2016.
 *//*


import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import es.mhp.entities.ZipLocation;
import es.mhp.services.IZipLocationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

*/
/**
 * Created by Edu on 23/02/2016.
 *//*


@SpringView(name = ZipLocationView.VIEW_NAME)
public class ZipLocationView extends AbtractView<ZipLocation> {
    public static final String VIEW_NAME = "ZipLocation";

    @Autowired
    private IZipLocationService zipLocationService;

    public ZipLocationView(){
        setSizeFull();
        this.addStyleName("zip-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    @Override
    protected Layout createTable() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addStyleName("item-view-table-container");
        verticalLayout.setMargin(true);

        List<ZipLocation> zipLocations = zipLocationService.findAllZipLocations();

        BeanItemContainer<ZipLocation> itemBeanItemContainer = new BeanItemContainer<>(ZipLocation.class, zipLocations);
        Grid grid = new Grid(itemBeanItemContainer);
        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<ZipLocation> zipLocationBeanItem = itemBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(zipLocationBeanItem.getBean()));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(ZipLocation zipLocation) {
        FormLayout form = new FormLayout();
        form.addStyleName("zip-view-form-container");
        PropertysetItem propertysetItemtem = new PropertysetItem();

        //propertysetItemtem.addItemProperty("ZipCode Id", new ObjectProperty(zipLocation.getZipCodeId()));
        propertysetItemtem.addItemProperty("Zip Code", new ObjectProperty(zipLocation.getZipCode()));
        propertysetItemtem.addItemProperty("City", new ObjectProperty(zipLocation.getCity()));
        propertysetItemtem.addItemProperty("State", new ObjectProperty(zipLocation.getState()));

        FieldGroup binder = new FieldGroup(propertysetItemtem);
        form.addComponent(binder.buildAndBind("ZipCode Id"));
        form.addComponent(binder.buildAndBind("Zip Code"));
        form.addComponent(binder.buildAndBind("City"));
        form.addComponent(binder.buildAndBind("State"));

        return form;
    }
}

*/
