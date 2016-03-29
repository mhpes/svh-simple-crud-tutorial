package es.mhp.search.impl.address;

import es.mhp.browser.IBrowser;
import es.mhp.browser.impl.address.AddressBrowser;
import es.mhp.search.impl.address.presenter.AddressSearchFormPresenter;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.impl.ServiceAddressImpl;
import es.mhp.toolbar.IToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Edu on 29/03/2016.
 */

@WebAppConfiguration
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class AddressSearchFormTest extends AbstractTestNGSpringContextTests {

    public Set<AddressDTO> addressDTOList;
    public Set<String> addressStateList;

    @Autowired
    private AddressSearchFormPresenter addressSearchFormPresenter;

    @Autowired
    @Qualifier(AddressBrowser.BEAN_NAME)
    private IBrowser browser;

    @Autowired
    private IToolbar toolbar;

    @PostConstruct
    private void initialize() {
        addressDTOList = new HashSet<>(Arrays.asList(
                new AddressDTO("Main Street_1", "SecondaryStreet_1", "Tenerife_1"),
                new AddressDTO("Main Street_2", "SecondaryStreet_2", "Tenerife_2"),
                new AddressDTO("Main Street_3", "SecondaryStreet_3", "Tenerife_3")
        ));

        addressStateList = new HashSet<>(Arrays.asList(
                "La Laguna",
                "Santa Cruz",
                "Puerto de la Cruz"
        ));
    }

    @Test
    public void checkMockitoWorks() {
        ServiceAddressImpl serviceAddressMocked = mock(ServiceAddressImpl.class);
        when(serviceAddressMocked.getStateList()).thenReturn(addressStateList);

        Set<String> stateList = serviceAddressMocked.getStateList();

        addressSearchFormPresenter
    }
}
