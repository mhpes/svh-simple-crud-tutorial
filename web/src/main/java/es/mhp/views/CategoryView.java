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
import es.mhp.services.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static es.mhp.browser.utils.FormBrowserUtils.EDIT_MODE;
import static es.mhp.browser.utils.FormBrowserUtils.NEW_MODE;

/*
 * Created by Edu on 23/02/2016.
*/

@SpringView(name = CategoryView.VIEW_NAME)
public class CategoryView extends AbstractView<CategoryDTO> {
    public static final String VIEW_NAME = "Categories";
    public static final String CATEGORY_ID = "Category Id";
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String IMAGE_URL = "Image URL";

    private VerticalLayout categoryLayout;
    private VerticalLayout categoryTable;

    @Autowired
    private ICategoryService iCategoryService;

    public CategoryView(){
        setSizeFull();
        categoryLayout = new VerticalLayout();
        categoryTable = new VerticalLayout();
        this.addStyleName("category-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        createMainLayout();
    }

    @Override
    protected void createMainLayout() {
        setTableSyle(categoryLayout);
        createFilter();
        categoryLayout.addComponent(categoryTable);
        this.addComponent(categoryLayout);
    }

//    @Override
    protected Layout createForm(CategoryDTO categoryDTO, String mode) {
        FormLayout form = new FormLayout();
        setFormStyle(form);

        PropertysetItem item = new PropertysetItem();
        bindForm(categoryDTO, form, item, mode);

        return form;
    }

    private Button createDeleteButton(CategoryDTO categoryDTO){
        final Button deleteButton = new Button("Delete entry", FontAwesome.TRASH_O);

        deleteButton.addClickListener((Button.ClickListener) event ->
                iCategoryService.delete(categoryDTO));

        return deleteButton;
    }

    private void setTableSyle(VerticalLayout layout) {
        layout.setSizeFull();
        layout.setMargin(true);
    }

    private void createFilter() {
        categoryLayout.removeAllComponents();
        TextField filter = new TextField();
        categoryLayout.addComponent(filter);

        filter.setInputPrompt("Filter categories...");
        fillCategoryTable(iCategoryService.findAllCategories());
        filter.addTextChangeListener(e ->
                fillCategoryTable(iCategoryService.findAnyCategories(e.getText())));
    }

    private void fillCategoryTable(Set<CategoryDTO> categoryDTOs) {
        categoryTable.removeAllComponents();

        BeanItemContainer<CategoryDTO> categoryDTOBeanItemContainer = new BeanItemContainer<>(CategoryDTO.class, categoryDTOs);
        Grid grid = new Grid(categoryDTOBeanItemContainer);
        grid.removeColumn("categoryId");
        grid.setColumnOrder("name", "description", "imageUrl", "associatedProductsCount");

        grid.setWidth("60%");
        VerticalLayout formContainer = createCategoryForm(categoryDTOBeanItemContainer, grid);

        categoryTable.addComponent(grid);
        categoryTable.addComponent(formContainer);
        categoryTable.setExpandRatio(grid, 1);

        setNewCategory(categoryTable);
    }

    private VerticalLayout createCategoryForm(BeanItemContainer<CategoryDTO> categoryDTOBeanItemContainer, Grid grid) {
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<CategoryDTO> categoryDTOBeanItem = categoryDTOBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(categoryDTOBeanItem.getBean(), EDIT_MODE));
            }
        });

        return formContainer;
    }

    public void setNewCategory(VerticalLayout verticalLayout) {
        VerticalLayout createNewCategoryLayout = new VerticalLayout();

        Button createButton = new Button("New Category");

        createButton.addClickListener((Button.ClickListener) event -> {
            createNewCategoryLayout.removeAllComponents();
            createNewCategoryLayout.addComponent(
                    createForm(new CategoryDTO(), NEW_MODE)
            );
        });

        verticalLayout.addComponent(createButton);
        verticalLayout.addComponent(createNewCategoryLayout);
    }

    private void setNewForm(PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        form.addComponent(binder.buildAndBind(CATEGORY_ID));
        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));

        form.addComponent(createCancelButton(form, binder));
        form.addComponent(createSaveButton(binder));
    }

    private void setEditForm(CategoryDTO categoryDTO, PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(categoryDTO, item);

        binder.buildAndBind(CATEGORY_ID);
        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));

        form.addComponent(createDeleteButton(categoryDTO));
        form.addComponent(createSaveButton(binder));
    }

    private void setItemPropertyEdit(PropertysetItem item) {
        item.addItemProperty(CATEGORY_ID, new ObjectProperty<>(""));
        item.addItemProperty(NAME, new ObjectProperty<>(""));
        item.addItemProperty(DESCRIPTION, new ObjectProperty<>(""));
        item.addItemProperty(IMAGE_URL, new ObjectProperty<>(""));
    }

    private void setItemPropertyEdit(CategoryDTO categoryDTO, PropertysetItem item) {
        item.addItemProperty(CATEGORY_ID, new ObjectProperty<>(categoryDTO.getCategoryId()));
        item.addItemProperty(NAME, new ObjectProperty<>(categoryDTO.getName()));
        item.addItemProperty(DESCRIPTION, new ObjectProperty<>(categoryDTO.getDescription()));
        item.addItemProperty(IMAGE_URL, new ObjectProperty<>(categoryDTO.getImageUrl()));
    }

    private Component createCancelButton(FormLayout form, FieldGroup binder) {
        Button cancelButton = new Button("Cancel", FontAwesome.TRASH_O);
        binder.discard();

        cancelButton.addClickListener((Button.ClickListener) event ->
                form.removeAllComponents()
        );

        return cancelButton;
    }

    private Button createSaveButton(FieldGroup categoryFieldGroup) {
        final Button saveButton = new Button("Save", FontAwesome.SAVE);

        saveButton.addClickListener((Button.ClickListener) event ->
                trySaveCategory(categoryFieldGroup));

        return saveButton;
    }

    public void trySaveCategory(FieldGroup categoryFieldGroup) {
        String categoryId = categoryFieldGroup.getField(CATEGORY_ID).getValue().toString();
        String name = categoryFieldGroup.getField(NAME).getValue().toString();
        String description = categoryFieldGroup.getField(DESCRIPTION).getValue().toString();
        String imageUrl = categoryFieldGroup.getField(IMAGE_URL).getValue().toString();

        try{
            CategoryDTO categoryDTO = new CategoryDTO(categoryId, name, description, imageUrl);
            iCategoryService.save(categoryDTO);
            Notification.show("New Category added!", Notification.Type.TRAY_NOTIFICATION);
        } catch (Exception err){ //I can't handle the correct Exception ConstraintViolationException
            Notification.show("Error adding new Category: " + err.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
    }

    private void bindForm(CategoryDTO categoryDTO, FormLayout form, PropertysetItem item, String mode) {
        form.addComponent(new Label(mode));
        FieldGroup binder = new FieldGroup(item);

        if (EDIT_MODE.equals(mode)){
            setEditForm(categoryDTO, item, form, binder);
        } else if (NEW_MODE.equals(mode)){
            setNewForm(item, form, binder);
        }
    }

    private void setFormStyle(FormLayout form) {
        form.setImmediate(true);
        form.addStyleName("category-view-form-container");
    }
}