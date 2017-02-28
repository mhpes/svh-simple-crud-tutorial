package es.mhp.browser.impl.item;

import com.vaadin.ui.ComboBox;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.item.presenter.ItemFormBrowserPresenter;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.dto.ItemDTO;
import es.mhp.services.dto.ProductDTO;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static es.mhp.views.utils.ItemViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ItemFormBrowser.BEAN_NAME)
@Scope("prototype")
public class ItemFormBrowser extends AbstractFormBrowser<ItemDTO> {

    public static final String BEAN_NAME = "item_form_browser";

    @Autowired
    private ItemFormBrowserPresenter itemFormBrowserPresenter;

    public ItemFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        ItemDTO itemDTO = new ItemDTO();
        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            itemDTO = (ItemDTO) dto;
        }
        bindForm(itemDTO, mode);
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindProductComboBox((ItemDTO) dto));
        form.addComponent(buildAndBindAddressComboBox((ItemDTO) dto));
        form.addComponent(buildAndBindSellerComboBox((ItemDTO) dto));
        form.addComponent(buildAndBindTextField(NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(DESCRIPTION_FIELD, true));
        form.addComponent(buildAndBindTextField(IMAGEURL_FIELD, true));
        form.addComponent(buildAndBindTextField(IMAGETHUMBURL_FIELD, true));
        form.addComponent(buildAndBindTextField(PRICE_FIELD, true));
        form.addComponent(buildAndBindTextField(TOTALSCORE_FIELD, true));
        form.addComponent(buildAndBindTextField(NUMBEROFVOTES_FIELD, true));
        form.addComponent(buildAndBindTextField(DISABLED_FIELD, true));
    }

    private ComboBox buildAndBindProductComboBox(ItemDTO itemDTO) {
        Set<ProductDTO> products = itemFormBrowserPresenter.findAllProducts();

        ComboBox<ProductDTO> productCombobox = new ComboBox();
        productCombobox.setItems(products);
        productCombobox.setItemCaptionGenerator(p -> p.getName());
        binder.bind(productCombobox, PRODUCT_FIELD);

        selectCurrentProduct(itemDTO, products, productCombobox);
        return productCombobox;
    }

    private ComboBox buildAndBindAddressComboBox(ItemDTO itemDTO) {
        Set<AddressDTO> productLocationContainer = itemFormBrowserPresenter.findAllAddresses();

        ComboBox<AddressDTO> addressCombobox = new ComboBox(ADDRESS);
        addressCombobox.setItems(productLocationContainer);
        addressCombobox.setItemCaptionGenerator(p -> p.getId().toString());
        addressCombobox.setRequiredIndicatorVisible(true);
        binder.bind(addressCombobox, ADDRESS_FIELD);

        selectCurrentAddress(itemDTO, productLocationContainer, addressCombobox);
        return addressCombobox;
    }

    private ComboBox buildAndBindSellerComboBox(ItemDTO itemDTO) {
        Set<SellerContactInfoDTO> sellerContactInfo = itemFormBrowserPresenter.findAllSellerContactInfo();

        ComboBox<SellerContactInfoDTO> sellerCombobox = new ComboBox(SELLERCONTACTINFO);
        sellerCombobox.setItems(sellerContactInfo);
        sellerCombobox.setItemCaptionGenerator(p -> p.getId().toString());
        sellerCombobox.setRequiredIndicatorVisible(true);
        binder.bind(sellerCombobox, SELLERCONTACTINFO_FIELD);

        selectCurrentSellerContactInfo(itemDTO, sellerContactInfo, sellerCombobox);
        return sellerCombobox;
    }

    private void selectCurrentProduct(ItemDTO itemDTO, Set<ProductDTO> productLocationContainer, ComboBox productCombobox) {
        if (itemDTO.getProductDTO() != null) {
            Optional<ProductDTO> productLocationDTOOptional = productLocationContainer.stream()
              .filter(dto -> Objects.equals(dto.getProductId(), itemDTO.getProductDTO().getProductId())).findFirst();
            if (productLocationDTOOptional.isPresent()) {
                productCombobox.setValue(productLocationDTOOptional.get());
            }
        }
    }

    private void selectCurrentSellerContactInfo(ItemDTO itemDTO, Set<SellerContactInfoDTO> sellerContactInfoContainer, ComboBox sellerCombobox) {
        if (itemDTO.getSellerContactInfoDTO() != null) {
            Optional<SellerContactInfoDTO> sellerLocationDTOOptional = sellerContactInfoContainer.stream()
              .filter(dto -> dto.getSellerId() == itemDTO.getSellerContactInfoDTO().getSellerId()).findFirst();
            if (sellerLocationDTOOptional.isPresent()) {
                sellerCombobox.setValue(sellerLocationDTOOptional.get());
            }
        }
    }

    private void selectCurrentAddress(ItemDTO itemDTO, Set<AddressDTO> productLocationContainer, ComboBox addressCombobox) {
        if (itemDTO.getAddressDTO() != null) {
            Optional<AddressDTO> addressLocationDTOOptional = productLocationContainer.stream()
              .filter(dto -> dto.getAddressId() == itemDTO.getAddressDTO().getAddressId()).findFirst();
            if (addressLocationDTOOptional.isPresent()) {
                addressCombobox.setValue(addressLocationDTOOptional.get());
            }
        }
    }
}
