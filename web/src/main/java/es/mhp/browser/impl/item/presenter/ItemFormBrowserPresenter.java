package es.mhp.browser.impl.item.presenter;

import com.vaadin.data.util.BeanItemContainer;
import es.mhp.services.IAddressService;
import es.mhp.services.IProductService;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.dto.ProductDTO;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Edu on 30/03/2016.
 */

@Component
@Scope("session")
public class ItemFormBrowserPresenter {

    @Autowired
    private ISellerContactInfoService sellerContactInfoService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IAddressService addressService;

    public BeanItemContainer<SellerContactInfoDTO> findAllSellerContactInfo() {
        Set<SellerContactInfoDTO> sellerSet = (Set<SellerContactInfoDTO>)(Set<?>) sellerContactInfoService.findAll();
        return new BeanItemContainer<>(SellerContactInfoDTO.class, sellerSet);
    }

    public BeanItemContainer<ProductDTO> findAllProducts() {
        Set<ProductDTO> productSet = (Set<ProductDTO>)(Set<?>) productService.findAll();
        return new BeanItemContainer<>(ProductDTO.class, productSet);
    }

    public BeanItemContainer<AddressDTO> findAllAddresses() {
        Set<AddressDTO> addressSet = (Set<AddressDTO>)(Set<?>) addressService.findAll();
        return new BeanItemContainer<>(AddressDTO.class, addressSet);
    }
}
