package es.mhp.search.impl.address;

import com.vaadin.ui.*;
import es.mhp.browser.IBrowser;
import es.mhp.search.impl.AbstractSearchForm;
import es.mhp.search.impl.address.presenter.AddressSearchFormPresenter;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static es.mhp.views.utils.AddressViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Scope("prototype")
@Component(AddressSearchForm.BEAN_NAME)
public class AddressSearchForm extends AbstractSearchForm {

    public static final String BEAN_NAME = "address_search_form";

    @Autowired
    private AddressSearchFormPresenter presenter;

    private TextField mainStreetTextField;
    private TextField secondaryStreetTextField;
    private ComboBox cityComboBox;
    private ComboBox stateComboBox;
    private OptionGroup browserWayOptionGroup;
    private Button browserButton;

    public AddressSearchForm() {
        super();
        initializeComponents();
    }

    @Override
    public void buildSearchForm(IBrowser browser, IToolbar toolbar) {
        searchForm.removeAllComponents();
        presenter.fillCitiesComboBox(cityComboBox);
        presenter.fillStatesComboBoxOrderer(stateComboBox);
        buildBrowserWayOptionGroup(browserWayOptionGroup);
        presenter.buildSearchForm(browser, toolbar, this);

        searchForm.addComponents(mainStreetTextField, secondaryStreetTextField, cityComboBox,
                                 stateComboBox, browserWayOptionGroup, browserButton);

        addComponent(searchForm);
    }

    private void initializeComponents() {
        mainStreetTextField = new TextField(MAIN_STREET);
        secondaryStreetTextField = new TextField(SECONDARY_STREET);
        cityComboBox = new ComboBox(CITY);
        stateComboBox = new ComboBox(STATE);
        browserWayOptionGroup = new OptionGroup();
        browserButton = new Button(ADDRESS_SEARCH);
    }

    public void setSearchForm(FormLayout searchForm) {
        this.searchForm = searchForm;
    }

    private void buildBrowserWayOptionGroup(OptionGroup browserWayOptionGroup) {
        browserWayOptionGroup.addItems(ALL, ANY);
        browserWayOptionGroup.select(ALL);
    }

    public TextField getMainStreetTextField() {
        return mainStreetTextField;
    }

    public void setMainStreetTextField(TextField mainStreetTextField) {
        this.mainStreetTextField = mainStreetTextField;
    }

    public TextField getSecondaryStreetTextField() {
        return secondaryStreetTextField;
    }

    public void setSecondaryStreetTextField(TextField secondaryStreetTextField) {
        this.secondaryStreetTextField = secondaryStreetTextField;
    }

    public ComboBox getCityComboBox() {
        return cityComboBox;
    }

    public void setCityComboBox(ComboBox cityComboBox) {
        this.cityComboBox = cityComboBox;
    }

    public ComboBox getStateComboBox() {
        return stateComboBox;
    }

    public void setStateComboBox(ComboBox stateComboBox) {
        this.stateComboBox = stateComboBox;
    }

    public OptionGroup getBrowserWayOptionGroup() {
        return browserWayOptionGroup;
    }

    public void setBrowserWayOptionGroup(OptionGroup browserWayOptionGroup) {
        this.browserWayOptionGroup = browserWayOptionGroup;
    }

    public Button getBrowserButton() {
        return browserButton;
    }

    public void setBrowserButton(Button browserButton) {
        this.browserButton = browserButton;
    }

    public FormLayout getSearchForm() {
        return searchForm;
    }

    public AddressSearchFormPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(AddressSearchFormPresenter presenter) {
        this.presenter = presenter;
    }
}
