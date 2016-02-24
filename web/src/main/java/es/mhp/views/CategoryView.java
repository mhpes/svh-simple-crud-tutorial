package es.mhp.views;

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
import entities.Category;
import es.mhp.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = CategoryView.VIEW_NAME)
public class CategoryView extends AbtractView<Category> {
    public static final String VIEW_NAME = "Categories";

    @Autowired
    private ICategoryService categoryService;

    public CategoryView(){
        setSizeFull();
        this.addStyleName("category-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    @Override
    Layout createTable() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addStyleName("category-view-table-container");
        verticalLayout.setMargin(true);

        List<Category> categories = categoryService.findAllCategories();

        BeanItemContainer<Category> categoryBeanItemContainer = new BeanItemContainer<>(Category.class, categories);
        Grid grid = new Grid(categoryBeanItemContainer);
        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<Category> categoryBeanItem = categoryBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(categoryBeanItem.getBean()));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    Layout createForm(Category category) {
        FormLayout form = new FormLayout();
        form.addStyleName("category-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty("Category Id", new ObjectProperty(category.getCategoryId()));
        item.addItemProperty("Description", new ObjectProperty(category.getDescription()));
        item.addItemProperty("Image", new ObjectProperty(category.getImageUrl()));
        item.addItemProperty("Name", new ObjectProperty(category.getName()));

        FieldGroup binder = new FieldGroup(item);
        form.addComponent(binder.buildAndBind("Category Id"));
        form.addComponent(binder.buildAndBind("Description"));
        form.addComponent(binder.buildAndBind("Image"));
        form.addComponent(binder.buildAndBind("Name"));

        return form;
    }
}
