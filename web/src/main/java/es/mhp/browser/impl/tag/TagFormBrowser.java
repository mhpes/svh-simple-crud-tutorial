package es.mhp.browser.impl.tag;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.TagDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.TagViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(TagFormBrowser.BEAN_NAME)
@Scope("prototype")
public class TagFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "tag_form_browser";

    public TagFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        TagDTO tagDTO = new TagDTO();
        BeanItem<TagDTO> beanItem = null;
        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            tagDTO = (TagDTO) dto;
            beanItem = createBeanItem(tagDTO);
        } else {
            beanItem = new BeanItem<>(tagDTO);
        }
        createFieldGroup(beanItem);
        bindForm(tagDTO, mode);
        fieldGroup.bindMemberFields(form);
    }

    @Override
    protected BeanItem createBeanItem(AbstractDTO dto) {
        TagDTO tagDTO = (TagDTO) dto;
        BeanItem<TagDTO> beanItem = new BeanItem<>(tagDTO);
        beanItem.addItemProperty(TAGID_FIELD, new ObjectProperty<>(tagDTO.getTagId()));
        beanItem.addItemProperty(TAG_FIELD, new ObjectProperty<>(tagDTO.getTag()));
        beanItem.addItemProperty(REFCOUNT_FIELD, new ObjectProperty<>(tagDTO.getRefCount()));
        return beanItem;
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(TAG_FIELD, true));
        form.addComponent(buildAndBindTextField(REFCOUNT_FIELD, true));

        // Set the form to act immediately on user input. This is necessary for the validation of the fields to occur immediately
        // when the input focus changes and not just on commit.
        form.setImmediate(true);
    }
}
