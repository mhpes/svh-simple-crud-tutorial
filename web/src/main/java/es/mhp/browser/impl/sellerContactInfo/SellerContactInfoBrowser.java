package es.mhp.browser.impl.sellerContactInfo;

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

import java.util.Set;

/**
 * Created by Edu on 17/03/2016.
 */

@org.springframework.stereotype.Component(SellerContactInfoBrowser.BEAN_NAME)
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
    }

    @Override
    public void buildBrowser() {
        gridBrowser.updateGrid(sellerContactInfoService.findAll());
        gridBrowser.addDoubleClickListenerToGrid();

        this.addComponent((Component) formBrowser);
        this.addComponent((AbstractGridBrowser)gridBrowser);

        displayGridAndHideForm();
    }

    @Override
    public void createAndDisplayForm(String mode) {
        displayFormAndHideGrid();
        formBrowser.createFormBrowser((AbstractDTO) gridBrowser.getSelectedGridRow(), mode);
    }

    @Override
    public void saveItemAndUpdateGrid() throws UIException {
        SellerContactInfoDTO sellerContactInfoDTO;
        try {
            sellerContactInfoDTO = (SellerContactInfoDTO) formBrowser.extractBean();
        } catch (FieldGroup.CommitException e) {
            throw new UIException("Entity has not been saved", e);
        }
        sellerContactInfoService.save(sellerContactInfoDTO);
        gridBrowser.updateAndDisplayGrid(sellerContactInfoDTO);
    }

    @Override
    public void updateGrid(Set<AbstractDTO> newDataSource) {
        gridBrowser.updateGrid(newDataSource);
        ((AbstractView)this.getParent()).updateToolbar(StateType.INITIAL);
    }

    @Override
    public void deleteItemAndUpdateGrid() throws UIException {
        try{
            sellerContactInfoService.delete(((SellerContactInfoDTO) gridBrowser.getSelectedGridRow()).getId());
            gridBrowser.deleteEntry();
            gridBrowser.updateGrid();
        } catch (Exception err){
            throw new UIException("Error deleting entry", err);
        }
    }

    @Override
    public void displayGridAndHideForm() {
        ((AbstractFormBrowser)formBrowser).setVisible(false);
        ((AbstractGridBrowser)gridBrowser).setVisible(true);
    }

    @Override
    public void displayFormAndHideGrid() {
        ((AbstractGridBrowser)gridBrowser).setVisible(false);
        ((AbstractFormBrowser)formBrowser).setVisible(true);
    }
}
