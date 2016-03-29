package es.mhp.browser.impl.sellercontactinfo;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;
import es.mhp.browser.IFormBrowser;
import es.mhp.browser.IGridBrowser;
import es.mhp.browser.impl.AbstractBrowser;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.AbstractGridBrowser;
import es.mhp.browser.utils.StateType;
import es.mhp.exceptions.UIException;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.SellerContactInfoDTO;
import es.mhp.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(SellerContactInfoBrowser.BEAN_NAME)
@Scope("prototype")
public class SellerContactInfoBrowser extends AbstractBrowser {

    public static final String BEAN_NAME = "sellerContactInfo_browser";

    @Autowired
    @Qualifier(SellerContactInfoGridBrowser.BEAN_NAME)
    private IGridBrowser gridBrowser;

    @Autowired
    @Qualifier(SellerContactInfoFormBrowser.BEAN_NAME)
    private IFormBrowser formBrowser;

    @Autowired
    private ISellerContactInfoService sellerContactInfoService;

    public SellerContactInfoBrowser() {
        super();
    }

    @Override
    public void buildBrowser() {
        gridBrowser.updateGrid(sellerContactInfoService.findAll());
        gridBrowser.configure();

        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        displayGridAndHideForm();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        displayFormAndHideGrid();
        formBrowser.createFormBrowser((AbstractDTO) gridBrowser.getSelectedGridRow(), mode);
    }

    //@Override
    public boolean saveItemAndUpdateGrid() throws UIException {
        try {
            if (formBrowser.isModified()) {
                formBrowser.commit();
                SellerContactInfoDTO sellerContactInfoDTO = (SellerContactInfoDTO) formBrowser.extractBean();
                // Service returns the dto updated (with the id included if it is a new item)
                SellerContactInfoDTO sellerContactInfoDTOUpdated = sellerContactInfoService.save(sellerContactInfoDTO);
                gridBrowser.updateGrid(sellerContactInfoDTOUpdated);
                displayGridAndHideForm();
                return true;
            } else {
                return false;
            }
        } catch (FieldGroup.CommitException e) {
            throw new UIException("Error! Entity cannot been saved.", e);
        }
    }

    //@Override
    public void updateAndDisplayGrid(Set<AbstractDTO> newDataSource) {
        gridBrowser.updateGrid(newDataSource);
        ((AbstractView)this.getParent()).updateToolbar(StateType.INITIAL);
    }

    //@Override
    public void deleteItemAndUpdateGrid() throws UIException {
        try{
            sellerContactInfoService.delete(((SellerContactInfoDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err){
            throw new UIException("Error deleting entry", err);
        }
    }

    //@Override
    public void displayGridAndHideForm() {
        ((AbstractFormBrowser)formBrowser).setVisible(false);
        ((AbstractGridBrowser)gridBrowser).setVisible(true);
    }

    //@Override
    public void displayFormAndHideGrid() {
        ((AbstractGridBrowser)gridBrowser).setVisible(false);
        ((AbstractFormBrowser)formBrowser).setVisible(true);
    }
}
