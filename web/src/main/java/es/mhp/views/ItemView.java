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
import es.mhp.entities.Address;
import es.mhp.services.IAddressService;
import es.mhp.services.IItemService;
import es.mhp.services.IProductService;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;

/*
 * Created by Edu on 23/02/2016.
*/

@SpringView(name = ItemView.VIEW_NAME)
public class ItemView extends AbtractView<ItemDTO> {
    public static final String VIEW_NAME = "Items";
    private static final String ITEM_ID = "Item Id";
    private static final String PRODUCT_ID = "Product Id";
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String IMAGE_URL = "Image URL";
    private static final String IMAGE_THUMB_URL = "Image Thumb URL";
    private static final String PRICE = "Price";
    private static final String ADDRESS_ID = "Address Id";
    private static final String CONTACTINFO_ID = "Contact Info Id";
    private static final String TOTAL_SCORE = "Total Score";
    private static final String NUMBER_OF_VOTES = "Number of Votes";
    private static final String DISABLED = "Disabled";
    private static final String SELLER_CONTACT = "Seller Contact";

    private VerticalLayout itemLayout;
    private VerticalLayout itemTable;

    @Autowired
    private IItemService iItemService;

    @Autowired
    private IAddressService iAddressService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ISellerContactInfoService iSellerContactInfoService;

    public ItemView(){
        setSizeFull();
        itemLayout = new VerticalLayout();
        itemTable = new VerticalLayout();
        this.addStyleName("item-view");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        createTable();
    }

    @Override
    protected void createTable() {
        setTableSyle(itemLayout);
        createFilter();
        itemLayout.addComponent(itemTable);
        this.addComponent(itemLayout);
    }

    @Override
    protected Layout createForm(ItemDTO itemDTO, String mode) {
        FormLayout form = new FormLayout();
        setFormStyle(form);

        PropertysetItem item = new PropertysetItem();
        bindForm(itemDTO, form, item, mode);

        return form;
    }

    private Button createDeleteButton(ItemDTO itemDTO){
        final Button deleteButton = new Button("Delete entry", FontAwesome.TRASH_O);

        deleteButton.addClickListener((Button.ClickListener) event ->
                iItemService.delete(itemDTO));

        return deleteButton;
    }

    private void setTableSyle(VerticalLayout layout) {
        layout.setSizeFull();
        layout.setMargin(true);
    }

    private void createFilter() {
        itemLayout.removeAllComponents();
        TextField filter = new TextField();
        itemLayout.addComponent(filter);

        filter.setInputPrompt("Filter items...");
        fillItemTable(iItemService.findAllItems());
        filter.addTextChangeListener(e ->
                fillItemTable(iItemService.findAnyItems(e.getText())));
    }

    private void fillItemTable(Set<ItemDTO> itemDTOs) {
        itemTable.removeAllComponents();

        BeanItemContainer<ItemDTO> beanItemContainer = new BeanItemContainer<>(ItemDTO.class, itemDTOs);
        beanItemContainer.removeItem("itemCount");
        Grid grid = new Grid(beanItemContainer);
        grid.removeColumn("itemId");
        grid.removeColumn("productId");
        grid.removeColumn("addressId");
        grid.removeColumn("contactInfoId");
        grid.setColumnOrder("name", "description", "price", "imageUrl", "disabled", "totalScore", "productSummary", "addressSummary", "sellerContactSummary");

        grid.setWidth("60%");
        VerticalLayout formContainer = createItemForm(beanItemContainer, grid);

        itemTable.addComponent(grid);
        itemTable.addComponent(formContainer);
        itemTable.setExpandRatio(grid, 1);

        setNewItem(itemTable);
    }

    private VerticalLayout createItemForm(BeanItemContainer<ItemDTO> itemDTOBeanItemContainer, Grid grid) {
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<ItemDTO> itemDTOBeanItem = itemDTOBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(itemDTOBeanItem.getBean(), EDIT_MODE));
            }
        });

        return formContainer;
    }

    public void setNewItem(VerticalLayout verticalLayout) {
        VerticalLayout createNewItemLayout = new VerticalLayout();

        Button createButton = new Button("New Item");

        createButton.addClickListener((Button.ClickListener) event -> {
            createNewItemLayout.removeAllComponents();
            createNewItemLayout.addComponent(
                    createForm(new ItemDTO(), NEW_MODE)
            );
        });

        verticalLayout.addComponent(createButton);
        verticalLayout.addComponent(createNewItemLayout);
    }

    private void setNewForm(PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(item);

        Set<ProductDTO> productDTOSet = iProductService.findAllProducts();
        ComboBox selectProduct = new ComboBox("Product");

        BeanItemContainer<ProductDTO> categoryDTOBeanItemContainer = new BeanItemContainer<>(ProductDTO.class);
        categoryDTOBeanItemContainer.addAll(productDTOSet);

        selectProduct.setItemCaptionPropertyId("productId");
        selectProduct.setContainerDataSource(categoryDTOBeanItemContainer);
        selectProduct.setRequired(true);
        selectProduct.setImmediate(true);

        binder.buildAndBind(ADDRESS_ID);
        binder.buildAndBind(ITEM_ID);
        binder.buildAndBind(PRODUCT_ID);
        binder.buildAndBind(CONTACTINFO_ID);

        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));
        form.addComponent(binder.buildAndBind(IMAGE_THUMB_URL));
        form.addComponent(binder.buildAndBind(PRICE));
        form.addComponent(binder.buildAndBind(TOTAL_SCORE));
        form.addComponent(binder.buildAndBind(NUMBER_OF_VOTES));
        form.addComponent(binder.buildAndBind(DISABLED));

        form.addComponent(createCancelButton(form, binder));
        form.addComponent(createSaveButton(binder));
    }

    private void setEditForm(ItemDTO itemDTO, PropertysetItem item, FormLayout form, FieldGroup binder) {
        setItemPropertyEdit(itemDTO, item);

        binder.buildAndBind(ADDRESS_ID);
        binder.buildAndBind(ITEM_ID);
        binder.buildAndBind(PRODUCT_ID);
        binder.buildAndBind(CONTACTINFO_ID);

        form.addComponent(binder.buildAndBind(NAME));
        form.addComponent(binder.buildAndBind(DESCRIPTION));
        form.addComponent(binder.buildAndBind(IMAGE_URL));
        form.addComponent(binder.buildAndBind(IMAGE_THUMB_URL));
        form.addComponent(binder.buildAndBind(PRICE));
        form.addComponent(binder.buildAndBind(TOTAL_SCORE));
        form.addComponent(binder.buildAndBind(NUMBER_OF_VOTES));
        form.addComponent(binder.buildAndBind(DISABLED));

        form.addComponent(createDeleteButton(itemDTO));
        form.addComponent(createSaveButton(binder));
    }

    private void setItemPropertyEdit(PropertysetItem item) {
        item.addItemProperty(ITEM_ID, new ObjectProperty(""));
        item.addItemProperty(PRODUCT_ID, new ObjectProperty(""));
        item.addItemProperty(NAME, new ObjectProperty(""));
        item.addItemProperty(DESCRIPTION, new ObjectProperty(""));
        item.addItemProperty(IMAGE_URL, new ObjectProperty(""));
        item.addItemProperty(IMAGE_THUMB_URL, new ObjectProperty(""));
        item.addItemProperty(PRICE, new ObjectProperty(""));
        item.addItemProperty(ADDRESS_ID, new ObjectProperty(""));
        item.addItemProperty(CONTACTINFO_ID, new ObjectProperty(""));
        item.addItemProperty(TOTAL_SCORE, new ObjectProperty(""));
        item.addItemProperty(NUMBER_OF_VOTES, new ObjectProperty(""));
        item.addItemProperty(DISABLED, new ObjectProperty(""));
    }

    private void setItemPropertyEdit(ItemDTO itemDTO, PropertysetItem item) {
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
    }

    private Component createCancelButton(FormLayout form, FieldGroup binder) {
        Button cancelButton = new Button("Cancel", FontAwesome.TRASH_O);
        binder.discard();

        cancelButton.addClickListener((Button.ClickListener) event ->
                form.removeAllComponents()
        );

        return cancelButton;
    }

    private Button createSaveButton(FieldGroup itemFieldGroup) {
        final Button saveButton = new Button("Save", FontAwesome.SAVE);

        saveButton.addClickListener((Button.ClickListener) event ->
                trySaveItem(itemFieldGroup));

        return saveButton;
    }

    public void trySaveItem(FieldGroup itemFieldGroup) {
        int addressId = Integer.parseInt(itemFieldGroup.getField(ADDRESS_ID).getValue().toString());
        int itemId = Integer.parseInt(itemFieldGroup.getField(ITEM_ID).getValue().toString());
        String name = itemFieldGroup.getField(NAME).getValue().toString();
        String description = itemFieldGroup.getField(DESCRIPTION).getValue().toString();
        String imageUrl = itemFieldGroup.getField(IMAGE_URL).getValue().toString();
        String imageThumbUrl = itemFieldGroup.getField(IMAGE_THUMB_URL).getValue().toString();
        int totalScore = Integer.parseInt(itemFieldGroup.getField(TOTAL_SCORE).getValue().toString());
        BigDecimal price = BigDecimal.ONE;
        ProductDTO productDTO = (ProductDTO) itemFieldGroup.getField(PRODUCT_ID).getValue();
        AddressDTO addressDTO = (AddressDTO) itemFieldGroup.getField(ADDRESS_ID).getValue();
        SellerContactInfoDTO sellerContactInfoDTO = (SellerContactInfoDTO) itemFieldGroup.getField(SELLER_CONTACT).getValue();
        int numberOfVotes = Integer.parseInt(itemFieldGroup.getField(NUMBER_OF_VOTES).getValue().toString());
        int disabled = Integer.parseInt(itemFieldGroup.getField(DISABLED).getValue().toString());

        try{
            ItemDTO itemDTO = new ItemDTO(addressDTO.getAddressId(), itemId, productDTO.getProductId(),
                                            name, description, imageUrl, imageThumbUrl, price,
                                            sellerContactInfoDTO.getContactInfoId(), totalScore,
                                            numberOfVotes, disabled);
            iItemService.save(itemDTO);
            Notification.show("New Item added!", Notification.Type.TRAY_NOTIFICATION);
        } catch (Exception err){ //I can't handle the correct Exception ConstraintViolationException
            Notification.show("Error adding new Item: " + err.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
    }

    private void bindForm(ItemDTO itemDTO, FormLayout form, PropertysetItem item, String mode) {
        form.addComponent(new Label(mode));
        FieldGroup binder = new FieldGroup(item);

        if (EDIT_MODE.equals(mode)){
            setEditForm(itemDTO, item, form, binder);
        } else if (NEW_MODE.equals(mode)){
            setNewForm(item, form, binder);
        }
    }

    private void setFormStyle(FormLayout form) {
        form.setImmediate(true);
        form.addStyleName("item-view-form-container");
    }
}