package es.mhp.browser.impl.item;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.ComboBox;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.IAddressService;
import es.mhp.services.IProductService;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

import static es.mhp.views.utils.ItemViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ItemFormBrowser.BEAN_NAME)
@Scope("prototype")
public class ItemFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "item_form_browser";

    @Autowired
    private IProductService productService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ISellerContactInfoService sellerContactInfoService;

    public ItemFormBrowser() {
        super();
    }

    @Override
    public void createFormBrowser(Object dto, String mode) {
        ItemDTO itemDTO = new ItemDTO();
        BeanItem<ItemDTO> beanItem;
        if (dto != null && FormBrowserUtils.EDIT_MODE.equals(mode)) {
            itemDTO = (ItemDTO) dto;
            beanItem = createBeanItem(itemDTO);
        } else {
            beanItem = new BeanItem<>(itemDTO);
        }
        createFieldGroup(beanItem);
        bindForm(itemDTO, mode);
        fieldGroup.bindMemberFields(form);
    }

    @Override
    protected BeanItem createBeanItem(AbstractDTO dto) {
        ItemDTO itemDTO = (ItemDTO) dto;
        BeanItem<ItemDTO> beanItem = new BeanItem<>(itemDTO);
        beanItem.addItemProperty(ITEMID_FIELD, new ObjectProperty(itemDTO.getItemId()));
        beanItem.addItemProperty(PRODUCT_FIELD, new ObjectProperty(itemDTO.getProductDTO()));
        beanItem.addItemProperty(NAME_FIELD, new ObjectProperty(itemDTO.getName()));
        beanItem.addItemProperty(DESCRIPTION_FIELD, new ObjectProperty(itemDTO.getDescription()));
        beanItem.addItemProperty(IMAGEURL_FIELD, new ObjectProperty(itemDTO.getImageUrl()));
        beanItem.addItemProperty(IMAGETHUMBURL_FIELD, new ObjectProperty(itemDTO.getImageThumbUrl()));
        beanItem.addItemProperty(PRICE_FIELD, new ObjectProperty(itemDTO.getPrice()));
        beanItem.addItemProperty(ADDRESS_FIELD, new ObjectProperty(itemDTO.getAddressDTO()));
        beanItem.addItemProperty(SELLERCONTACTINFO_FIELD, new ObjectProperty(itemDTO.getSellerContactInfoDTO()));
        beanItem.addItemProperty(TOTALSCORE_FIELD, new ObjectProperty(itemDTO.getTotalScore()));
        beanItem.addItemProperty(NUMBEROFVOTES_FIELD, new ObjectProperty(itemDTO.getNumberOfVotes()));
        beanItem.addItemProperty(DISABLED_FIELD, new ObjectProperty(itemDTO.getDisabled()));
        return beanItem;
    }

    @Override
    protected void bindForm(Object dto, String mode) {
        form.removeAllComponents();
        form.addComponent(buildAndBindProductComboBox((ItemDTO) dto));
        form.addComponent(buildAndBindAddressComboBox((ItemDTO)dto));
        form.addComponent(buildAndBindSellerComboBox((ItemDTO)dto));
        form.addComponent(buildAndBindTextField(NAME_FIELD, true));
        form.addComponent(buildAndBindTextField(DESCRIPTION_FIELD, true));
        form.addComponent(buildAndBindTextField(IMAGEURL_FIELD, true));
        form.addComponent(buildAndBindTextField(IMAGETHUMBURL_FIELD, true));
        form.addComponent(buildAndBindTextField(PRICE_FIELD, true));
        form.addComponent(buildAndBindTextField(TOTALSCORE_FIELD, true));
        form.addComponent(buildAndBindTextField(NUMBEROFVOTES_FIELD, true));
        form.addComponent(buildAndBindTextField(DISABLED_FIELD, true));

        // Set the form to act immediately on user input. This is necessary for the validation of the fields to occur immediately
        // when the input focus changes and not just on commit.
        form.setImmediate(true);
    }

    private ComboBox buildAndBindProductComboBox(ItemDTO itemDTO) {
        Set<ProductDTO> productSet = (Set<ProductDTO>)(Set<?>) productService.findAll();
        BeanItemContainer<ProductDTO> productLocationContainer = new BeanItemContainer<>(ProductDTO.class, productSet);
        ComboBox productCombobox = new ComboBox(PRODUCT, productSet);
        productCombobox.setContainerDataSource(productLocationContainer);
        productCombobox.setItemCaptionPropertyId(PRODUCTID_FIELD);
        productCombobox.setNullSelectionAllowed(false);
        productCombobox.setRequired(true);
        fieldGroup.bind(productCombobox, PRODUCT_FIELD);

        if (itemDTO.getProductDTO() != null) {
            Optional<ProductDTO> productLocationDTOOptional = productLocationContainer.getItemIds().stream()
                    .filter(dto -> dto.getProductId() == itemDTO.getProductDTO().getProductId()).findFirst();
            if (productLocationDTOOptional.isPresent()) {
                productCombobox.setValue(productLocationDTOOptional.get());
            }
        }
        return productCombobox;
    }

    private ComboBox buildAndBindAddressComboBox(ItemDTO itemDTO) {
        Set<AddressDTO> addressSet = (Set<AddressDTO>)(Set<?>) addressService.findAll();

        BeanItemContainer<AddressDTO> productLocationContainer = new BeanItemContainer<>(AddressDTO.class, addressSet);
        ComboBox addressCombobox = new ComboBox(ADDRESS, addressSet);
        addressCombobox.setContainerDataSource(productLocationContainer);
        addressCombobox.setItemCaptionPropertyId(ADDRESSID_FIELD);
        addressCombobox.setNullSelectionAllowed(false);
        addressCombobox.setRequired(true);
        fieldGroup.bind(addressCombobox, ADDRESS_FIELD);

        if (itemDTO.getAddressDTO() != null) {
            Optional<AddressDTO> addressLocationDTOOptional = productLocationContainer.getItemIds().stream()
                    .filter(dto -> dto.getAddressId() == itemDTO.getAddressDTO().getAddressId()).findFirst();
            if (addressLocationDTOOptional.isPresent()) {
                addressCombobox.setValue(addressLocationDTOOptional.get());
            }
        }
        return addressCombobox;
    }

    private ComboBox buildAndBindSellerComboBox(ItemDTO itemDTO) {
        Set<SellerContactInfoDTO> zipSet = (Set<SellerContactInfoDTO>)(Set<?>) sellerContactInfoService.findAll();

        BeanItemContainer<SellerContactInfoDTO> selllerLocationContainer = new BeanItemContainer<>(SellerContactInfoDTO.class, zipSet);
        ComboBox sellerCombobox = new ComboBox(SELLERCONTACTINFO, zipSet);
        sellerCombobox.setContainerDataSource(selllerLocationContainer);
        sellerCombobox.setItemCaptionPropertyId(SELLERCONTACTINFOID_FIELD);
        sellerCombobox.setNullSelectionAllowed(false);
        sellerCombobox.setRequired(true);
        fieldGroup.bind(sellerCombobox, SELLERCONTACTINFO_FIELD);

        if (itemDTO.getSellerContactInfoDTO() != null) {
            Optional<SellerContactInfoDTO> sellerLocationDTOOptional = selllerLocationContainer.getItemIds().stream()
                    .filter(dto -> dto.getSellerId() == itemDTO.getSellerContactInfoDTO().getSellerId()).findFirst();
            if (sellerLocationDTOOptional.isPresent()) {
                sellerCombobox.setValue(sellerLocationDTOOptional.get());
            }
        }
        return sellerCombobox;
    }
}
