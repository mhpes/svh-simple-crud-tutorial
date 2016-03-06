package es.mhp.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import es.mhp.entities.Category;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/*
 * Created by Edu on 23/02/2016.
*/

@SpringView(name = CategoryView.VIEW_NAME)
public class CategoryView extends AbtractView<CategoryDTO> {
    public static final String VIEW_NAME = "Categories";
    public static final String CATEGORY_ID = "Category Id";
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String IMAGE_URL = "Image URL";

    @Autowired
    private ICategoryService iCategoryService;

    public CategoryView(){
        setSizeFull();
        this.addStyleName("category-view");
    }

    @Override
    protected Layout createTable() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addStyleName("category-view-table-container");
        verticalLayout.setMargin(true);

        Set<CategoryDTO> categoryDTOs = iCategoryService.findAllCategories();

        BeanItemContainer<CategoryDTO> categoryDTOBeanItemContainer = new BeanItemContainer<>(CategoryDTO.class, categoryDTOs);
        Grid grid = new Grid(categoryDTOBeanItemContainer);
        grid.removeColumn("categoryId");
        grid.setColumnOrder("name", "description", "imageUrl", "associatedProductsCount");

        grid.setWidth("60%");
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<CategoryDTO> categoryDTOBeanItem = categoryDTOBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(categoryDTOBeanItem.getBean(), EDIT_MODE + Category.class));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(CategoryDTO categoryDTO, String mode) {
        FormLayout form = new FormLayout();
        form.setImmediate(true);
        form.addStyleName("address-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty(CATEGORY_ID, new ObjectProperty(categoryDTO.getCategoryId()));
        item.addItemProperty(NAME, new ObjectProperty(categoryDTO.getName()));
        item.addItemProperty(DESCRIPTION, new ObjectProperty(categoryDTO.getDescription()));
        item.addItemProperty(IMAGE_URL, new ObjectProperty(categoryDTO.getImageUrl()));


        FieldGroup binder = new FieldGroup(item);
        binder.buildAndBind(CATEGORY_ID);
        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));

        form.addComponent(createDeleteButton(categoryDTO));
        form.addComponent(createSavebutton(binder));

        return form;
    }

    private Button createSavebutton(FieldGroup itemFieldGroup) {
        final Button saveButton = new Button("Save");

        saveButton.addClickListener((Button.ClickListener) event -> {
            String categoryId = itemFieldGroup.getField(CATEGORY_ID).getValue().toString();
            String name = itemFieldGroup.getField(NAME).getValue().toString();
            String description = itemFieldGroup.getField(DESCRIPTION).getValue().toString();
            String imageUrl = itemFieldGroup.getField(IMAGE_URL).getValue().toString();

            CategoryDTO addressDTO = new CategoryDTO(categoryId, name, description, imageUrl);
            iCategoryService.save(addressDTO);
        });

        return saveButton;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    private Button createDeleteButton(CategoryDTO categoryDTO){
        final Button deleteButton = new Button("Delete entry");

        deleteButton.addClickListener((Button.ClickListener) event -> iCategoryService.delete(categoryDTO));

        return deleteButton;
    }
}
