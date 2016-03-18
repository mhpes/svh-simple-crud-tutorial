package es.mhp.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import es.mhp.services.ICategoryService;
import es.mhp.services.IProductService;
import es.mhp.services.dto.CategoryDTO;
import es.mhp.services.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/*
 * Created by Edu on 23/02/2016.
*/

@SpringView(name = ProductView.VIEW_NAME)
public class ProductView extends AbtractView<ProductDTO> {
    public static final String VIEW_NAME = "Products";
    private static final String PRODUCT_ID = "Product Id";
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String IMAGE_URL = "Image Url";
    private static final String CATEGORY = "Category";

    private VerticalLayout productLayout;
    private VerticalLayout productTable;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    public ProductView(){
        setSizeFull();
        productLayout = new VerticalLayout();
        productTable = new VerticalLayout();
        this.addStyleName("product-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        createTable();
    }

    @Override
    protected void createTable() {
        setTableSyle(productLayout);
        createFilter();
        productLayout.addComponent(productTable);
        this.addComponent(productLayout);
    }

    @Override
    protected Layout createForm(ProductDTO productDTO, String mode) {
        FormLayout form = new FormLayout();
        setFormStyle(form);

        PropertysetItem item = new PropertysetItem();
        bindForm(productDTO, form, item, mode);

        return form;
    }

    private Button createDeleteButton(ProductDTO productDTO){
        final Button deleteButton = new Button("Delete entry", FontAwesome.TRASH_O);

        deleteButton.addClickListener((Button.ClickListener) event ->
                iProductService.delete(productDTO));

        return deleteButton;
    }

    private void setTableSyle(VerticalLayout layout) {
        layout.setSizeFull();
        layout.setMargin(true);
    }

    private void createFilter() {
        productLayout.removeAllComponents();
        TextField filter = new TextField();
        productLayout.addComponent(filter);

        filter.setInputPrompt("Filter products...");
        fillProductTable(iProductService.findAllProducts());
        filter.addTextChangeListener(e ->
                fillProductTable(iProductService.findAnyProducts(e.getText())));
    }

    private void fillProductTable(Set<ProductDTO> productDTOs) {
        productTable.removeAllComponents();

        BeanItemContainer<ProductDTO> productDTOBeanItemContainer = new BeanItemContainer<>(ProductDTO.class, productDTOs);
        Grid grid = new Grid(productDTOBeanItemContainer);
        grid.removeColumn("productId");
        grid.removeColumn("category");

        grid.setColumnOrder("name", "description", "imageUrl");

        grid.setWidth("60%");
        VerticalLayout formContainer = createProductForm(productDTOBeanItemContainer, grid);

        productTable.addComponent(grid);
        productTable.addComponent(formContainer);
        productTable.setExpandRatio(grid, 1);

        setNewProduct(productTable);
    }

    private VerticalLayout createProductForm(BeanItemContainer<ProductDTO> productDTOBeanItemContainer, Grid grid) {
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<ProductDTO> productDTOBeanItem = productDTOBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(productDTOBeanItem.getBean(), EDIT_MODE));
            }
        });

        return formContainer;
    }

    public void setNewProduct(VerticalLayout verticalLayout) {
        VerticalLayout createNewProductLayout = new VerticalLayout();

        Button createButton = new Button("New Product");

        createButton.addClickListener((Button.ClickListener) event -> {
            createNewProductLayout.removeAllComponents();
            createNewProductLayout.addComponent(
                    createForm(new ProductDTO(), NEW_MODE)
            );
        });

        verticalLayout.addComponent(createButton);
        verticalLayout.addComponent(createNewProductLayout);
    }

    private void setNewForm(PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        Set<CategoryDTO> categoryList = iCategoryService.findAllCategories();
        ComboBox selectCategory = new ComboBox("Category");

        BeanItemContainer<CategoryDTO> categoryDTOBeanItemContainer = new BeanItemContainer<>(CategoryDTO.class);
        categoryDTOBeanItemContainer.addAll(categoryList);

        selectCategory.setItemCaptionPropertyId("categoryId");
        selectCategory.setContainerDataSource(categoryDTOBeanItemContainer);
        selectCategory.setRequired(true);
        selectCategory.setImmediate(true);

        binder.bind(selectCategory, CATEGORY);
        form.addComponent(selectCategory);

        form.addComponent(binder.buildAndBind(PRODUCT_ID));
        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));

        form.addComponent(createCancelButton(form, binder));
        form.addComponent(createSaveButton(binder));
    }

    private void setEditForm(ProductDTO productDTO, PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(productDTO, item);

        Set<CategoryDTO> categoryList = iCategoryService.findAllCategories();
        ComboBox selectCategory = new ComboBox("Categories");

        BeanItemContainer<CategoryDTO> zipLocationContainer = new BeanItemContainer<>(CategoryDTO.class);

        for (CategoryDTO category : categoryList){
            zipLocationContainer.addBean(category);
        }

        selectCategory.setItemCaptionPropertyId("categoryId");
        selectCategory.setContainerDataSource(zipLocationContainer);
        selectCategory.setRequired(true);
        selectCategory.setImmediate(true);

        binder.buildAndBind(PRODUCT_ID);
        binder.bind(selectCategory, CATEGORY);

        form.addComponent(selectCategory);
        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));

        form.addComponent(createDeleteButton(productDTO));
        form.addComponent(createSaveButton(binder));
    }

    private void setItemPropertyEdit(PropertysetItem item) {
        item.addItemProperty(PRODUCT_ID, new ObjectProperty<>(""));
        item.addItemProperty(CATEGORY, new ObjectProperty<>(""));
        item.addItemProperty(NAME, new ObjectProperty<>(""));
        item.addItemProperty(DESCRIPTION, new ObjectProperty<>(""));
        item.addItemProperty(IMAGE_URL, new ObjectProperty<>(""));
    }

    private void setItemPropertyEdit(ProductDTO productDTO, PropertysetItem item) {
        item.addItemProperty(PRODUCT_ID, new ObjectProperty<>(productDTO.getProductId()));
        item.addItemProperty(CATEGORY, new ObjectProperty<>(productDTO.getCategory()));
        item.addItemProperty(NAME, new ObjectProperty<>(productDTO.getName()));
        item.addItemProperty(DESCRIPTION, new ObjectProperty<>(productDTO.getDescription()));
        item.addItemProperty(IMAGE_URL, new ObjectProperty<>(productDTO.getImageUrl()));
    }

    private Component createCancelButton(FormLayout form, FieldGroup binder) {
        Button cancelButton = new Button("Cancel", FontAwesome.TRASH_O);
        binder.discard();

        cancelButton.addClickListener((Button.ClickListener) event ->
                form.removeAllComponents()
        );

        return cancelButton;
    }

    private Button createSaveButton(FieldGroup productFieldGroup) {
        final Button saveButton = new Button("Save", FontAwesome.SAVE);

        saveButton.addClickListener((Button.ClickListener) event ->
                trySaveProduct(productFieldGroup));

        return saveButton;
    }

    public void trySaveProduct(FieldGroup productFieldGroup) {
        String productId = productFieldGroup.getField(PRODUCT_ID).getValue().toString();
        String name = productFieldGroup.getField(NAME).getValue().toString();
        String description = productFieldGroup.getField(DESCRIPTION).getValue().toString();
        String imageUrl = productFieldGroup.getField(IMAGE_URL).getValue().toString();
        CategoryDTO categoryDTO = (CategoryDTO) productFieldGroup.getField(CATEGORY).getValue();

        try{
            ProductDTO productDTO = new ProductDTO(productId, name, description, imageUrl, categoryDTO.getCategoryId());
            iProductService.save(productDTO);
            Notification.show("New Product added!", Notification.Type.TRAY_NOTIFICATION);
        } catch (Exception err){ //I can't handle the correct Exception ConstraintViolationException
            Notification.show("Error adding new Product: " + err.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
    }

    private void bindForm(ProductDTO productDTO, FormLayout form, PropertysetItem item, String mode) {
        form.addComponent(new Label(mode));
        FieldGroup binder = new FieldGroup(item);

        if (EDIT_MODE.equals(mode)){
            setEditForm(productDTO, item, form, binder);
        } else if (NEW_MODE.equals(mode)){
            setNewForm(item, form, binder);
        }
    }

    private void setFormStyle(FormLayout form) {
        form.setImmediate(true);
        form.addStyleName("product-view-form-container");
    }
}