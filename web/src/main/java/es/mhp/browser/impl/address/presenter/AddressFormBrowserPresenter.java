package es.mhp.browser.impl.address.presenter;

import com.vaadin.data.util.BeanItemContainer;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Edu on 30/03/2016.
 */

@Component
@Scope("session")
public class AddressFormBrowserPresenter{

    @Autowired
    private IZipLocationService zipLocationService;

    public BeanItemContainer<ZipLocationDTO> findAllZipLocation() {
        Set<ZipLocationDTO> zipSet = (Set<ZipLocationDTO>)(Set<?>) zipLocationService.findAll();
        return new BeanItemContainer<>(ZipLocationDTO.class, zipSet);
    }
}
