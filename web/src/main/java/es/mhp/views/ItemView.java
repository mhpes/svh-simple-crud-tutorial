package es.mhp.views;

/*
 * Created by Edu on 24/02/2016.
*/


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
import es.mhp.services.IItemService;
import es.mhp.services.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@SpringView(name = ItemView.VIEW_NAME)
public class ItemView extends AbtractView<ItemDTO> {
    public static final String VIEW_NAME = "Items";

    @Autowired
    private IItemService itemService;

    public ItemView(){
        setSizeFull();
        this.addStyleName("item-view");
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

        Set<ItemDTO> items = itemService.findAllItems();

        BeanItemContainer<ItemDTO> itemBeanItemContainer = new BeanItemContainer<>(ItemDTO.class, items);
        Grid grid = new Grid(itemBeanItemContainer);
        grid.removeColumn("itemId");

        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<ItemDTO> itemBeanItem = itemBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(itemBeanItem.getBean()));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(ItemDTO itemDTO) {
        FormLayout form = new FormLayout();
        form.addStyleName("item-view-form-container");
        PropertysetItem propertysetItemtem = new PropertysetItem();

        propertysetItemtem.addItemProperty("Name", new ObjectProperty(itemDTO.getName()));
        propertysetItemtem.addItemProperty("Description", new ObjectProperty(itemDTO.getDescription()));
        propertysetItemtem.addItemProperty("Image URL", new ObjectProperty(itemDTO.getImageUrl()));
        propertysetItemtem.addItemProperty("Image Thumb URL", new ObjectProperty(itemDTO.getImageThumbUrl()));
        propertysetItemtem.addItemProperty("Price", new ObjectProperty(itemDTO.getPrice()));

        FieldGroup binder = new FieldGroup(propertysetItemtem);
        form.addComponent(binder.buildAndBind("Name"));
        form.addComponent(binder.buildAndBind("Description"));
        form.addComponent(binder.buildAndBind("Image URL"));
        form.addComponent(binder.buildAndBind("Image Thumb URL"));
        form.addComponent(binder.buildAndBind("Price"));

        return form;
    }
}

