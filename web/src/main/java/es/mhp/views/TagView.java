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
import es.mhp.services.ITagService;
import es.mhp.services.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/*
 * Created by Edu on 23/02/2016.
*/

@SpringView(name = TagView.VIEW_NAME)
public class TagView extends AbtractView<TagDTO> {
    public static final String VIEW_NAME = "Tags";
    private static final String TAG_ID = "Tag Id";
    private static final String TAG = "Tag";
    private static final String REF_COUNT = "Reference Count";

    private VerticalLayout tagLayout;
    private VerticalLayout tagTable;


    @Autowired
    private ITagService iTagService;

    public TagView(){
        setSizeFull();
        tagLayout = new VerticalLayout();
        tagTable = new VerticalLayout();
        this.addStyleName("tag-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    @Override
    protected Layout createTable() {
        setTableSyle(tagLayout);
        createFilter();
        tagLayout.addComponent(tagTable);
        return tagLayout;
    }

    @Override
    protected Layout createForm(TagDTO tagDTO, String mode) {
        FormLayout form = new FormLayout();
        setFormStyle(form);

        PropertysetItem item = new PropertysetItem();
        bindForm(tagDTO, form, item, mode);

        return form;
    }

    private Button createDeleteButton(TagDTO tagDTO){
        final Button deleteButton = new Button("Delete entry", FontAwesome.TRASH_O);

        deleteButton.addClickListener((Button.ClickListener) event ->
                iTagService.delete(tagDTO));

        return deleteButton;
    }

    private void setTableSyle(VerticalLayout layout) {
        layout.setSizeFull();
        layout.setMargin(true);
    }

    private void createFilter() {
        tagLayout.removeAllComponents();
        TextField filter = new TextField();
        tagLayout.addComponent(filter);

        filter.setInputPrompt("Filter tags...");
        fillTagTable(iTagService.findAllTags());
        filter.addTextChangeListener(e ->
                fillTagTable(iTagService.findAnyTags(e.getText())));
    }

    private void fillTagTable(Set<TagDTO> tagDTOs) {
        tagTable.removeAllComponents();

        BeanItemContainer<TagDTO> tagDTOBeanItemContainer = new BeanItemContainer<>(TagDTO.class, tagDTOs);
        Grid grid = new Grid(tagDTOBeanItemContainer);
        grid.removeColumn("tagId");
        grid.setColumnOrder("tag", "refCount");

        grid.setWidth("60%");
        VerticalLayout formContainer = createTagForm(tagDTOBeanItemContainer, grid);

        tagTable.addComponent(grid);
        tagTable.addComponent(formContainer);
        tagTable.setExpandRatio(grid, 1);

        setNewTag(tagTable);
    }

    private VerticalLayout createTagForm(BeanItemContainer<TagDTO> tagDTOBeanItemContainer, Grid grid) {
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<TagDTO> tagDTOBeanItem = tagDTOBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(tagDTOBeanItem.getBean(), EDIT_MODE));
            }
        });

        return formContainer;
    }

    public void setNewTag(VerticalLayout verticalLayout) {
        VerticalLayout createNewTagLayout = new VerticalLayout();

        Button createButton = new Button("New Tag");

        createButton.addClickListener((Button.ClickListener) event -> {
            createNewTagLayout.removeAllComponents();
            createNewTagLayout.addComponent(
                    createForm(new TagDTO(), NEW_MODE)
            );
        });

        verticalLayout.addComponent(createButton);
        verticalLayout.addComponent(createNewTagLayout);
    }

    private void setNewForm(PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        binder.buildAndBind(TAG_ID);
        form.addComponent(binder.buildAndBind(TAG));
        form.addComponent(binder.buildAndBind(REF_COUNT));

        form.addComponent(createCancelButton(form, binder));
        form.addComponent(createSaveButton(binder));
    }

    private void setEditForm(TagDTO tagDTO, PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(tagDTO, item);

        binder.buildAndBind(TAG_ID);
        form.addComponent(binder.buildAndBind(TAG));
        form.addComponent(binder.buildAndBind(REF_COUNT));

        form.addComponent(createDeleteButton(tagDTO));
        form.addComponent(createSaveButton(binder));
    }

    private void setItemPropertyEdit(PropertysetItem item) {
        item.addItemProperty(TAG_ID, new ObjectProperty<>(0));
        item.addItemProperty(TAG, new ObjectProperty<>(""));
        item.addItemProperty(REF_COUNT, new ObjectProperty<>(""));
    }

    private void setItemPropertyEdit(TagDTO tagDTO, PropertysetItem item) {
        item.addItemProperty(TAG_ID, new ObjectProperty<>(tagDTO.getTagId()));
        item.addItemProperty(TAG, new ObjectProperty<>(tagDTO.getTag()));
        item.addItemProperty(REF_COUNT, new ObjectProperty<>(tagDTO.getRefCount()));
    }

    private Component createCancelButton(FormLayout form, FieldGroup binder) {
        Button cancelButton = new Button("Cancel", FontAwesome.TRASH_O);
        binder.discard();

        cancelButton.addClickListener((Button.ClickListener) event ->
                form.removeAllComponents()
        );

        return cancelButton;
    }

    private Button createSaveButton(FieldGroup tagFieldGroup) {
        final Button saveButton = new Button("Save", FontAwesome.SAVE);

        saveButton.addClickListener((Button.ClickListener) event ->
                trySaveTag(tagFieldGroup));

        return saveButton;
    }

    public void trySaveTag(FieldGroup tagFieldGroup) {
        int tagId = Integer.parseInt(tagFieldGroup.getField(TAG_ID).getValue().toString());
        String name = tagFieldGroup.getField(TAG).getValue().toString();
        int refCount = Integer.parseInt(tagFieldGroup.getField(REF_COUNT).getValue().toString());

        try{
            TagDTO tagDTO = new TagDTO(tagId, name, refCount);
            iTagService.save(tagDTO);
            Notification.show("New Tag added!", Notification.Type.TRAY_NOTIFICATION);
        } catch (Exception err){ //I can't handle the correct Exception ConstraintViolationException
            Notification.show("Error adding new Tag: " + err.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
    }

    private void bindForm(TagDTO tagDTO, FormLayout form, PropertysetItem item, String mode) {
        form.addComponent(new Label(mode));
        FieldGroup binder = new FieldGroup(item);

        if (EDIT_MODE.equals(mode)){
            setEditForm(tagDTO, item, form, binder);
        } else if (NEW_MODE.equals(mode)){
            setNewForm(item, form, binder);
        }
    }

    private void setFormStyle(FormLayout form) {
        form.setImmediate(true);
        form.addStyleName("tag-view-form-container");
    }
}