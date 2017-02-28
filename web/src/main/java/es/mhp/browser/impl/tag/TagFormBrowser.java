package es.mhp.browser.impl.tag;

import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.TagDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static es.mhp.views.utils.TagViewConstants.REFCOUNT_FIELD;
import static es.mhp.views.utils.TagViewConstants.TAG_FIELD;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(TagFormBrowser.BEAN_NAME)
@Scope("prototype")
public class TagFormBrowser extends AbstractFormBrowser<TagDTO> {

    public static final String BEAN_NAME = "tag_form_browser";

    public TagFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        TagDTO tagDTO = new TagDTO();

        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            tagDTO = (TagDTO) dto;
        }

        bindForm(tagDTO, mode);
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindTextField(TAG_FIELD, true));
        form.addComponent(buildAndBindTextField(REFCOUNT_FIELD, true));
    }
}
