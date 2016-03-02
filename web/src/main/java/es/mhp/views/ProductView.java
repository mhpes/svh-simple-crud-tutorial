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
import com.vaadin.ui.*;
import es.mhp.services.IProductService;
import es.mhp.services.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@SpringView(name = ProductView.VIEW_NAME)
public class ProductView extends AbtractView<ProductDTO> {
    public static final String VIEW_NAME = "Products";

    private static final String PRODUCT_ID = "Product Id";
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String IMAGE_URL = "Image Url";

    @Autowired
    private IProductService iProductService;

    public ProductView() {
        this.addStyleName("product-view");
    }

    @Override
    protected Layout createTable() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addStyleName("product-view-table-container");
        verticalLayout.setMargin(true);

        Set<ProductDTO> productDTOs = iProductService.findAllProducts();

        BeanItemContainer<ProductDTO> productDTOBeanItemContainer = new BeanItemContainer<>(ProductDTO.class, productDTOs);
        productDTOBeanItemContainer.removeItem("itemCount");
        Grid grid = new Grid(productDTOBeanItemContainer);
        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null) {
                formContainer.removeAllComponents();
                BeanItem<ProductDTO> productDTOBeanItem = productDTOBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(productDTOBeanItem.getBean()));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(ProductDTO productDTO) {
        FormLayout form = new FormLayout();
        form.setImmediate(true);
        form.addStyleName("product-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty(PRODUCT_ID, new ObjectProperty(productDTO.getProductId()));
        item.addItemProperty(NAME, new ObjectProperty(productDTO.getName()));
        item.addItemProperty(DESCRIPTION, new ObjectProperty(productDTO.getDescription()));
        item.addItemProperty(IMAGE_URL, new ObjectProperty(productDTO.getImageUrl()));


        FieldGroup binder = new FieldGroup(item);
        form.addComponent(binder.buildAndBind(PRODUCT_ID));
        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));

        form.addComponent(createDeleteButton(productDTO));
        form.addComponent(createSavebutton(binder));

        return form;
    }

    private Button createSavebutton(FieldGroup productFieldGroup) {
        final Button saveButton = new Button("Save");

        saveButton.addClickListener((Button.ClickListener) event -> {
            String productId = productFieldGroup.getField(PRODUCT_ID).getValue().toString();
            String name = productFieldGroup.getField(NAME).getValue().toString();
            String description = productFieldGroup.getField(DESCRIPTION).getValue().toString();
            String imageUrl = productFieldGroup.getField(IMAGE_URL).getValue().toString();

            ProductDTO productDTO = new ProductDTO(productId, name, description, imageUrl);
            iProductService.save(productDTO);
        });

        return saveButton;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    private Button createDeleteButton(ProductDTO productDTO) {
        final Button deleteButton = new Button("Delete entry");

        deleteButton.addClickListener((Button.ClickListener) event -> iProductService.delete(productDTO));

        return deleteButton;
    }
}

