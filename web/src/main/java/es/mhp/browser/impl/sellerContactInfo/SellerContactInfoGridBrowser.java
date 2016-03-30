package es.mhp.browser.impl.sellercontactinfo;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static es.mhp.views.utils.SellerContactInfoViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(SellerContactInfoGridBrowser.BEAN_NAME)
@Scope("prototype")
public class SellerContactInfoGridBrowser extends AbstractGridBrowser<SellerContactInfoDTO> {

    public static final String BEAN_NAME = "sellerContactInfo_grid_browser";

    public SellerContactInfoGridBrowser() {
        super();
    }

    @Override
    public void updateGrid(Collection<? extends AbstractDTO> newDataSource) {
        grid.removeAllColumns();

        BeanItemContainer<SellerContactInfoDTO> sellerContactInfoBeanItemContainer = new BeanItemContainer<>(SellerContactInfoDTO.class, (Collection<? extends SellerContactInfoDTO>) newDataSource);
        grid.setContainerDataSource(sellerContactInfoBeanItemContainer);
        setColumnProperties();

        this.addComponent(grid);
    }

    @Override
    protected void setColumnProperties() {
        grid.removeColumn(CONTACTINFO_FIELD);
        grid.setColumnOrder(FIRST_NAME_FIELD, LAST_NAME_FIELD, EMAIL_FIELD);
        grid.setWidth("60%");
    }
}
