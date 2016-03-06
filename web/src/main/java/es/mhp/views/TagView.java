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
import es.mhp.entities.Tag;
import es.mhp.services.ITagService;
import es.mhp.services.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@SpringView(name = TagView.VIEW_NAME)
public class TagView extends AbtractView<TagDTO> {
    public static final String VIEW_NAME = "Tags";
    private static final String TAG_ID = "Tag Id";
    private static final String TAG = "Tag";
    private static final String REF_COUNT = "Reference Count";

    @Autowired
    private ITagService iTagService;

    public TagView(){
        setSizeFull();
        this.addStyleName("tag-view");
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

        Set<TagDTO> zipLocationDTOs = iTagService.findAllTags();

        BeanItemContainer<TagDTO> itemBeanItemContainer = new BeanItemContainer<>(TagDTO.class, zipLocationDTOs);
        Grid grid = new Grid(itemBeanItemContainer);
        grid.removeColumn("tagId");
        grid.setWidth("60%");
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<TagDTO> zipLocationDTOBeanItem = itemBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(zipLocationDTOBeanItem.getBean(), EDIT_MODE + Tag.class));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(TagDTO tagDTO, String mode) {
        FormLayout form = new FormLayout();
        form.setImmediate(true);
        form.addStyleName("zipLocation-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty(TAG_ID, new ObjectProperty(tagDTO.getTagId()));
        item.addItemProperty(TAG, new ObjectProperty(tagDTO.getTag()));
        item.addItemProperty(REF_COUNT, new ObjectProperty(tagDTO.getRefCount()));

        FieldGroup binder = new FieldGroup(item);
        binder.buildAndBind(TAG_ID);
        form.addComponent(binder.buildAndBind(TAG));
        form.addComponent(binder.buildAndBind(REF_COUNT));

        form.addComponent(createDeleteButton(tagDTO));
        form.addComponent(createSavebutton(binder));

        return form;
    }

    private Button createSavebutton(FieldGroup tagFieldGroup) {
        final Button saveButton = new Button("Save");

        saveButton.addClickListener((Button.ClickListener) event -> {
            Integer tagId = Integer.parseInt(tagFieldGroup.getField(TAG_ID).getValue().toString());
            String tagDescription = tagFieldGroup.getField(TAG).getValue().toString();
            Integer refCount = Integer.parseInt(tagFieldGroup.getField(REF_COUNT).getValue().toString());

            TagDTO addressDTO = new TagDTO(tagId, tagDescription, refCount);
            iTagService.save(addressDTO);
        });

        return saveButton;
    }

    private Button createDeleteButton(TagDTO tagDTO){
        final Button deleteButton = new Button("Delete entry");

        deleteButton.addClickListener((Button.ClickListener) event -> iTagService.delete(tagDTO));

        return deleteButton;
    }
}

