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
import es.mhp.services.IItemService;
import es.mhp.services.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;

@SpringView(name = ItemView.VIEW_NAME)
public class ItemView extends AbtractView<ItemDTO> {
    public static final String VIEW_NAME = "Items";
    private static final String ITEM_ID = "Item Id";
    private static final String PRODUCT_ID = "Product Id";
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String IMAGE_URL = "Image URL";
    private static final String IMAGE_THUMB_URL = "Image Thumb URL";
    private static final String PRICE = "Pridce";
    private static final String ADDRESS_ID = "Address Id";
    private static final String CONTACTINFO_ID = "Contact Info Id";
    private static final String TOTAL_SCORE = "Total Score";
    private static final String NUMBER_OF_VOTES = "Number of Votes";
    private static final String DISABLED = "Disabled";

    @Autowired
    private IItemService itemService;

    public ItemView(){
        setSizeFull();
        this.addStyleName("item-view");
    }

    @Override
    protected Layout createTable() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addStyleName("item-view-table-container");
        verticalLayout.setMargin(true);

        Set<ItemDTO> itemDTOs = itemService.findAllItems();

        BeanItemContainer<ItemDTO> itemDTOBeanItemContainer = new BeanItemContainer<>(ItemDTO.class, itemDTOs);
        itemDTOBeanItemContainer.removeItem("itemCount");
        Grid grid = new Grid(itemDTOBeanItemContainer);

        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<ItemDTO> itemDTOBeanItem = itemDTOBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(itemDTOBeanItem.getBean()));
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
        form.setImmediate(true);
        form.addStyleName("item-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty(ITEM_ID, new ObjectProperty(itemDTO.getItemId()));
        item.addItemProperty(PRODUCT_ID, new ObjectProperty(itemDTO.getProductId()));
        item.addItemProperty(NAME, new ObjectProperty(itemDTO.getName()));
        item.addItemProperty(DESCRIPTION, new ObjectProperty(itemDTO.getDescription()));
        item.addItemProperty(IMAGE_URL, new ObjectProperty(itemDTO.getImageUrl()));
        item.addItemProperty(IMAGE_THUMB_URL, new ObjectProperty(itemDTO.getImageThumbUrl()));
        item.addItemProperty(PRICE, new ObjectProperty(itemDTO.getPrice()));
        item.addItemProperty(ADDRESS_ID, new ObjectProperty(itemDTO.getAddressId()));
        item.addItemProperty(CONTACTINFO_ID, new ObjectProperty(itemDTO.getContactInfoId()));
        item.addItemProperty(TOTAL_SCORE, new ObjectProperty(itemDTO.getTotalScore()));
        item.addItemProperty(NUMBER_OF_VOTES, new ObjectProperty(itemDTO.getNumberOfVotes()));
        item.addItemProperty(DISABLED, new ObjectProperty(itemDTO.getDisabled()));

        FieldGroup binder = new FieldGroup(item);
        form.addComponent(binder.buildAndBind(ADDRESS_ID));
        form.addComponent(binder.buildAndBind(PRODUCT_ID));
        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));
        form.addComponent(binder.buildAndBind(IMAGE_THUMB_URL));
        form.addComponent(binder.buildAndBind(PRICE));
        form.addComponent(binder.buildAndBind(CONTACTINFO_ID));
        form.addComponent(binder.buildAndBind(TOTAL_SCORE));
        form.addComponent(binder.buildAndBind(NUMBER_OF_VOTES));
        form.addComponent(binder.buildAndBind(DISABLED));

        form.addComponent(createDeleteButton(itemDTO));
        form.addComponent(createSavebutton(binder));

        return form;
    }

    private Button createSavebutton(FieldGroup itemFieldGroup) {
        final Button saveButton = new Button("Save");

        saveButton.addClickListener((Button.ClickListener) event -> {
            int addressId = Integer.parseInt(itemFieldGroup.getField(ADDRESS_ID).getValue().toString());
            int itemId = Integer.parseInt(itemFieldGroup.getField(ITEM_ID).getValue().toString());
            String productId = itemFieldGroup.getField(PRODUCT_ID).getValue().toString();
            String name = itemFieldGroup.getField(NAME).getValue().toString();
            String description = itemFieldGroup.getField(DESCRIPTION).getValue().toString();
            String imageUrl = itemFieldGroup.getField(IMAGE_URL).getValue().toString();
            String imageThumbUrl = itemFieldGroup.getField(IMAGE_THUMB_URL).getValue().toString();
            BigDecimal price = BigDecimal.ONE;
            int contactInfoId = Integer.parseInt(itemFieldGroup.getField(CONTACTINFO_ID).getValue().toString());
            int totalScore = Integer.parseInt(itemFieldGroup.getField(TOTAL_SCORE).getValue().toString());
            int numberOfVotes = Integer.parseInt(itemFieldGroup.getField(NUMBER_OF_VOTES).getValue().toString());
            int disabled = Integer.parseInt(itemFieldGroup.getField(DISABLED).getValue().toString());

            ItemDTO itemDTO = new ItemDTO(addressId, itemId, productId, name, description, imageUrl, imageThumbUrl, price, contactInfoId, totalScore, numberOfVotes, disabled);
            itemService.save(itemDTO);
        });

        return saveButton;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    private Button createDeleteButton(ItemDTO itemDTO){
        final Button deleteButton = new Button("Delete entry");

        deleteButton.addClickListener((Button.ClickListener) event -> itemService.delete(itemDTO));

        return deleteButton;
    }
}

