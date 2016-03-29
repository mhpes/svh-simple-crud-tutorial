package es.mhp.search.impl.address;

import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.address.AddressBrowser;
import es.mhp.search.impl.address.presenter.AddressSearchFormPresenter;
import es.mhp.services.IAddressService;
import es.mhp.services.dto.AddressDTO;
import es.mhp.toolbar.IToolbar;
import es.mhp.views.utils.AddressViewConstants;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 29/03/2016.
 */

@WebAppConfiguration
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class AddressSearchFormTest extends AbstractTestNGSpringContextTests {

    public Set<String> addressStateList;

    @Autowired
    @Qualifier(AddressBrowser.BEAN_NAME)
    private IBrowser browser;

    @Autowired
    private IToolbar toolbar;

    @InjectMocks
    private AddressSearchForm addressSearchForm;

    @Spy
    @InjectMocks
    private AddressSearchFormPresenter addressSearchFormPresenter;

    @Mock
    private IAddressService addressService;

    @BeforeMethod
    private void initMocks() {
        MockitoAnnotations.initMocks(this);

        addressStateList = new HashSet<>(Arrays.asList(
                "La Laguna",
                "Santa Cruz",
                "Puerto de la Cruz"
        ));
    }

    @Test
    public void stateComboboxTest() {
        initializeMocksAndBuildSearchForm();

        Assert.assertEquals(addressSearchForm.getStateComboBox().isEmpty(), false);
        Assert.assertEquals(addressSearchForm.getStateComboBox().isVisible(), true);
        Assert.assertEquals(addressSearchForm.getStateComboBox().getItemIds(), addressStateList);
    }

    @Test
    public void browserWayOptionGroupTest() {
        initializeMocksAndBuildSearchForm();

        Assert.assertEquals(addressSearchForm.getBrowserWayOptionGroup().isEmpty(), false);
        Assert.assertEquals(addressSearchForm.getBrowserWayOptionGroup().isVisible(), true);
        Assert.assertEquals(addressSearchForm.getBrowserWayOptionGroup().getValue(), AddressViewConstants.ALL);
    }

    private void initializeMocksAndBuildSearchForm() {
        Mockito.when(addressService.getStateList()).thenReturn(addressStateList);
        addressSearchForm.buildSearchForm(browser, toolbar);
    }
}
