package es.mhp.views;

/**
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
import es.mhp.entities.Product;
import es.mhp.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = ProductView.VIEW_NAME)
public class ProductView extends AbtractView<Product> {
    public static final String VIEW_NAME = "Products";

    @Autowired
    private IProductService productService;

    public ProductView(){
        setSizeFull();
        this.addStyleName("product-view");
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
        verticalLayout.addStyleName("product-view-table-container");
        verticalLayout.setMargin(true);

        List<Product> products = productService.findAllProducts();

        BeanItemContainer<Product> itemBeanItemContainer = new BeanItemContainer<>(Product.class, products);

        itemBeanItemContainer.removeContainerProperty("es.mhp.entities.Category");
        itemBeanItemContainer.removeItem("es.mhp.entities.Item");

        Grid grid = new Grid(itemBeanItemContainer);

        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<Product> productBeanItem = itemBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(productBeanItem.getBean()));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(Product product) {
        FormLayout form = new FormLayout();
        form.addStyleName("product-view-form-container");
        PropertysetItem propertysetItem = new PropertysetItem();

        propertysetItem.addItemProperty("Product Id", new ObjectProperty(product.getProductId()));
        propertysetItem.addItemProperty("Name", new ObjectProperty(product.getName()));
        propertysetItem.addItemProperty("Description", new ObjectProperty(product.getDescription()));
        propertysetItem.addItemProperty("Image URL", new ObjectProperty(product.getImageUrl()));

        FieldGroup binder = new FieldGroup(propertysetItem);
        form.addComponent(binder.buildAndBind("Product Id"));
        form.addComponent(binder.buildAndBind("Name"));
        form.addComponent(binder.buildAndBind("Description"));
        form.addComponent(binder.buildAndBind("Image URL"));

        return form;
    }
}

