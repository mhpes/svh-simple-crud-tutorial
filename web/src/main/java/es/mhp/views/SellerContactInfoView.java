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
import es.mhp.entities.SellerContactInfo;
import es.mhp.services.ISellerContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = SellerContactInfoView.VIEW_NAME)
public class SellerContactInfoView extends AbtractView<SellerContactInfo> {
    public static final String VIEW_NAME = "SellerContactInfos";

    @Autowired
    private ISellerContactInfoService iSellerContactInfoService;

    public SellerContactInfoView(){
        setSizeFull();
        this.addStyleName("seller-view");
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

        List<SellerContactInfo> sellerContactInfos = iSellerContactInfoService.findAllSellers();

        BeanItemContainer<SellerContactInfo> itemBeanItemContainer = new BeanItemContainer<>(SellerContactInfo.class, sellerContactInfos);
        Grid grid = new Grid(itemBeanItemContainer);
        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<SellerContactInfo> sellerContactInfoBeanItem = itemBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(sellerContactInfoBeanItem.getBean()));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(SellerContactInfo sellerContactInfo) {
        FormLayout form = new FormLayout();
        form.addStyleName("seller-view-form-container");
        PropertysetItem propertysetItem = new PropertysetItem();

        propertysetItem.addItemProperty("Seller Id", new ObjectProperty(sellerContactInfo.getSellerId()));
        propertysetItem.addItemProperty("First Name", new ObjectProperty(sellerContactInfo.getFirstName()));
        propertysetItem.addItemProperty("Last Name", new ObjectProperty(sellerContactInfo.getLastName()));
        propertysetItem.addItemProperty("Email", new ObjectProperty(sellerContactInfo.getEmail()));

        FieldGroup binder = new FieldGroup(propertysetItem);
        form.addComponent(binder.buildAndBind("Seller Id"));
        form.addComponent(binder.buildAndBind("First Name"));
        form.addComponent(binder.buildAndBind("Last Name"));
        form.addComponent(binder.buildAndBind("Email"));

        return form;
    }
}

