package es.mhp.views;

/**
 * Created by Edu on 24/02/2016.
 * */



import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import es.mhp.entities.SellerContactInfo;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


@SpringView(name = SellerContactInfoView.VIEW_NAME)
public class SellerContactInfoView extends AbtractView<SellerContactInfoDTO> {
    public static final String VIEW_NAME = "SellerContactInfos";
    public static final String CONTACTINFO_ID = "Seller Id";
    public static final String LAST_NAME = "Last name";
    public static final String FIRST_NAME = "First name";
    public static final String EMAIL = "Email";

    @Autowired
    private ISellerContactInfoService iSellerContactInfoService;

    public SellerContactInfoView(){
        setSizeFull();
        this.addStyleName("seller-view");
    }

    @Override
    protected Layout createTable() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addStyleName("seller-view-table-container");
        verticalLayout.setMargin(true);

        Set<SellerContactInfoDTO> sellerContactInfoDTOs = iSellerContactInfoService.findAllSellers();

        BeanItemContainer<SellerContactInfoDTO> sellerContactInfoDTOBeanItemContainer = new BeanItemContainer<>(SellerContactInfoDTO.class, sellerContactInfoDTOs);
        sellerContactInfoDTOBeanItemContainer.removeItem("itemCount");
        Grid grid = new Grid(sellerContactInfoDTOBeanItemContainer);
        grid.removeColumn("contactInfoId");
        grid.setColumnOrder("firstName", "lastName", "email");

        grid.setWidth("60%");
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<SellerContactInfoDTO> sellerContactInfoDTOBeanItem = sellerContactInfoDTOBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(sellerContactInfoDTOBeanItem.getBean(), EDIT_MODE + SellerContactInfo.class));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(SellerContactInfoDTO sellerContactInfoDTO, String mode) {
        FormLayout form = new FormLayout();
        form.setImmediate(true);
        form.addStyleName("seller-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty(CONTACTINFO_ID, new ObjectProperty(sellerContactInfoDTO.getContactInfoId()));
        item.addItemProperty(LAST_NAME, new ObjectProperty(sellerContactInfoDTO.getLastName()));
        item.addItemProperty(FIRST_NAME, new ObjectProperty(sellerContactInfoDTO.getFirstName()));
        item.addItemProperty(EMAIL, new ObjectProperty(sellerContactInfoDTO.getEmail()));

        FieldGroup binder = new FieldGroup(item);
        binder.buildAndBind(CONTACTINFO_ID);
        form.addComponent(binder.buildAndBind(LAST_NAME));
        form.addComponent(binder.buildAndBind(FIRST_NAME));
        form.addComponent(binder.buildAndBind(EMAIL));

        form.addComponent(createDeleteButton(sellerContactInfoDTO));
        form.addComponent(createSavebutton(binder));

        return form;
    }

    private Button createSavebutton(FieldGroup sellerFieldGroup) {
        final Button saveButton = new Button("Save");

        saveButton.addClickListener((Button.ClickListener) event -> {
            int contactInfoId = Integer.parseInt(sellerFieldGroup.getField(CONTACTINFO_ID).getValue().toString());
            String lastName = sellerFieldGroup.getField(LAST_NAME).getValue().toString();
            String firstName = sellerFieldGroup.getField(FIRST_NAME).getValue().toString();
            String email = sellerFieldGroup.getField(EMAIL).getValue().toString();

            SellerContactInfoDTO sellerContactInfoDTO = new SellerContactInfoDTO(contactInfoId, lastName, firstName, email);
            iSellerContactInfoService.save(sellerContactInfoDTO);
        });

        return saveButton;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(createTable());
    }

    private Button createDeleteButton(SellerContactInfoDTO sellerContactInfoDTO){
        final Button deleteButton = new Button("Delete entry");

        deleteButton.addClickListener((Button.ClickListener) event -> iSellerContactInfoService.delete(sellerContactInfoDTO));

        return deleteButton;
    }
}

