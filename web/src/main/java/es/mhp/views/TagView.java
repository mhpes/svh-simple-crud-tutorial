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
import es.mhp.entities.Tag;
import es.mhp.services.ITagService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Edu on 23/02/2016.
 */

@SpringView(name = TagView.VIEW_NAME)
public class TagView extends AbtractView<Tag> {
    public static final String VIEW_NAME = "Tags";

    @Autowired
    private ITagService tagService;

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
        verticalLayout.addStyleName("tag-view-table-container");
        verticalLayout.setMargin(true);

        List<Tag> tags = tagService.findAllTags();

        BeanItemContainer<Tag> itemBeanItemContainer = new BeanItemContainer<>(Tag.class, tags);
        Grid grid = new Grid(itemBeanItemContainer);
        grid.setSizeFull();
        VerticalLayout formContainer = new VerticalLayout();

        grid.addSelectionListener((SelectionEvent.SelectionListener) event -> {
            if (grid.getSelectedRow() != null){
                formContainer.removeAllComponents();
                BeanItem<Tag> tagBeanItem = itemBeanItemContainer.getItem(grid.getSelectedRow());
                formContainer.addComponent(createForm(tagBeanItem.getBean()));
            }
        });

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(formContainer);
        verticalLayout.setExpandRatio(grid, 1);

        return verticalLayout;
    }

    @Override
    protected Layout createForm(Tag tag) {
        FormLayout form = new FormLayout();
        form.addStyleName("tag-view-form-container");
        PropertysetItem propertysetItemtem = new PropertysetItem();

        propertysetItemtem.addItemProperty("Tag Id", new ObjectProperty(tag.getTagId()));
        propertysetItemtem.addItemProperty("Description", new ObjectProperty(tag.getTagDescription()));
        propertysetItemtem.addItemProperty("Reference Count", new ObjectProperty(tag.getRefCount()));

        FieldGroup binder = new FieldGroup(propertysetItemtem);
        form.addComponent(binder.buildAndBind("Tag Id"));
        form.addComponent(binder.buildAndBind("Description"));
        form.addComponent(binder.buildAndBind("Reference Count"));

        return form;
    }
}

