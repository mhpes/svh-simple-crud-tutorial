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
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@SpringView(name = ZipLocationView.VIEW_NAME)
public class ZipLocationView extends AbtractView<ZipLocationDTO> {
    public static final String VIEW_NAME = "ZipLocation";
    public static final String ZIPCODE = "Zipcode";
    public static final String CITY = "City";
    public static final String STATE = "State";

    @Autowired
    private IZipLocationService iZipLocationService;

    public ZipLocationView(){
        setSizeFull();
        this.addStyleName("zip-view");
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

        Set<ZipLocationDTO> zipLocationDTOs = iZipLocationService.findAllZipLocations();

        BeanItemContainer<ZipLocationDTO> itemBeanItemContainer = new BeanItemContainer<>(ZipLocationDTO.class, zipLocationDTOs);
        Grid grid = new Grid(itemBeanItemContainer);
        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<ZipLocationDTO> zipLocationDTOBeanItem = itemBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(zipLocationDTOBeanItem.getBean()));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(ZipLocationDTO zipLocationDTO) {
        FormLayout form = new FormLayout();
        form.setImmediate(true);
        form.addStyleName("zipLocation-view-form-container");
        PropertysetItem item = new PropertysetItem();

        item.addItemProperty(ZIPCODE, new ObjectProperty(zipLocationDTO.getZipCodeId()));
        item.addItemProperty(CITY, new ObjectProperty(zipLocationDTO.getCity()));
        item.addItemProperty(STATE, new ObjectProperty(zipLocationDTO.getState()));

        FieldGroup binder = new FieldGroup(item);
        form.addComponent(binder.buildAndBind(ZIPCODE));
        form.addComponent(binder.buildAndBind(CITY));
        form.addComponent(binder.buildAndBind(STATE));

        form.addComponent(createSavebutton(binder));
        form.addComponent(createDeleteButton(binder));

        return form;
    }

    private Button createSavebutton(FieldGroup addressFieldGroup) {
        final Button saveButton = new Button("Save");

        saveButton.addClickListener((Button.ClickListener) event -> {
            Integer zipcode = Integer.parseInt(addressFieldGroup.getField(ZIPCODE).getValue().toString());
            String city = addressFieldGroup.getField(CITY).getValue().toString();
            String state = addressFieldGroup.getField(STATE).getValue().toString();

            ZipLocationDTO addressDTO = new ZipLocationDTO(zipcode, city, state);
            iZipLocationService.save(addressDTO);
        });

        return saveButton;
    }

    private Button createDeleteButton(FieldGroup addressFieldGroup){
        final Button deleteButton = new Button("Delete entry");

        deleteButton.addClickListener((Button.ClickListener) event ->
        {
            Integer zipcode = Integer.parseInt(addressFieldGroup.getField(ZIPCODE).getValue().toString());
            iZipLocationService.delete(zipcode);
        });

        return deleteButton;
    }
}

