package es.mhp.browser.impl.item;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.ComboBox;
import es.mhp.browser.impl.AbstractFormBrowser;
import es.mhp.browser.impl.item.presenter.ItemFormBrowserPresenter;
import es.mhp.browser.utils.FormBrowserUtils;
import es.mhp.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static es.mhp.views.utils.ItemViewConstants.*;

/**
 * Created by Edu on 18/03/2016.
 */

@Component(ItemFormBrowser.BEAN_NAME)
@Scope("prototype")
public class ItemFormBrowser extends AbstractFormBrowser {

    public static final String BEAN_NAME = "item_form_browser";

    @Autowired
    private ItemFormBrowserPresenter itemFormBrowserPresenter;

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
        beanItem.addItemProperty(ITEMID_FIELD, new ObjectProperty<>(itemDTO.getItemId()));
        beanItem.addItemProperty(PRODUCT_FIELD, new ObjectProperty<>(itemDTO.getProductDTO()));
        beanItem.addItemProperty(NAME_FIELD, new ObjectProperty<>(itemDTO.getName()));
        beanItem.addItemProperty(DESCRIPTION_FIELD, new ObjectProperty<>(itemDTO.getDescription()));
        beanItem.addItemProperty(IMAGEURL_FIELD, new ObjectProperty<>(itemDTO.getImageUrl()));
        beanItem.addItemProperty(IMAGETHUMBURL_FIELD, new ObjectProperty<>(itemDTO.getImageThumbUrl()));
        beanItem.addItemProperty(PRICE_FIELD, new ObjectProperty<>(itemDTO.getPrice()));
        beanItem.addItemProperty(ADDRESS_FIELD, new ObjectProperty<>(itemDTO.getAddressDTO()));
        beanItem.addItemProperty(SELLERCONTACTINFO_FIELD, new ObjectProperty<>(itemDTO.getSellerContactInfoDTO()));
        beanItem.addItemProperty(TOTALSCORE_FIELD, new ObjectProperty<>(itemDTO.getTotalScore()));
        beanItem.addItemProperty(NUMBEROFVOTES_FIELD, new ObjectProperty<>(itemDTO.getNumberOfVotes()));
        beanItem.addItemProperty(DISABLED_FIELD, new ObjectProperty<>(itemDTO.getDisabled()));
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
        BeanItemContainer<ProductDTO> productLocationContainer = itemFormBrowserPresenter.findAllProducts();

        ComboBox productCombobox = new ComboBox(PRODUCT);
        productCombobox.setContainerDataSource(productLocationContainer);
        productCombobox.setItemCaptionPropertyId(PRODUCTID_FIELD);
        productCombobox.setNullSelectionAllowed(false);
        productCombobox.setRequired(true);
        fieldGroup.bind(productCombobox, PRODUCT_FIELD);

        selectCurrentProduct(itemDTO, productLocationContainer, productCombobox);
        return productCombobox;
    }

    private ComboBox buildAndBindAddressComboBox(ItemDTO itemDTO) {
        BeanItemContainer<AddressDTO> productLocationContainer = itemFormBrowserPresenter.findAllAddresses();

        ComboBox addressCombobox = new ComboBox(ADDRESS);
        addressCombobox.setContainerDataSource(productLocationContainer);
        addressCombobox.setItemCaptionPropertyId(ADDRESSID_FIELD);
        addressCombobox.setNullSelectionAllowed(false);
        addressCombobox.setRequired(true);
        fieldGroup.bind(addressCombobox, ADDRESS_FIELD);

        selectCurrentAddress(itemDTO, productLocationContainer, addressCombobox);
        return addressCombobox;
    }

    private ComboBox buildAndBindSellerComboBox(ItemDTO itemDTO) {
        BeanItemContainer<SellerContactInfoDTO> sellerContactInfoContainer = itemFormBrowserPresenter.findAllSellerContactInfo();

        ComboBox sellerCombobox = new ComboBox(SELLERCONTACTINFO);
        sellerCombobox.setContainerDataSource(sellerContactInfoContainer);
        sellerCombobox.setItemCaptionPropertyId(SELLERCONTACTINFOID_FIELD);
        sellerCombobox.setNullSelectionAllowed(false);
        sellerCombobox.setRequired(true);
        fieldGroup.bind(sellerCombobox, SELLERCONTACTINFO_FIELD);

        selectCurrentSellerContactInfo(itemDTO, sellerContactInfoContainer, sellerCombobox);
        return sellerCombobox;
    }

    private void selectCurrentProduct(ItemDTO itemDTO, BeanItemContainer<ProductDTO> productLocationContainer, ComboBox productCombobox) {
        if (itemDTO.getProductDTO() != null) {
            Optional<ProductDTO> productLocationDTOOptional = productLocationContainer.getItemIds().stream()
                    .filter(dto -> Objects.equals(dto.getProductId(), itemDTO.getProductDTO().getProductId())).findFirst();
            if (productLocationDTOOptional.isPresent()) {
                productCombobox.setValue(productLocationDTOOptional.get());
            }
        }
    }

    private void selectCurrentSellerContactInfo(ItemDTO itemDTO, BeanItemContainer<SellerContactInfoDTO> sellerContactInfoContainer, ComboBox sellerCombobox) {
        if (itemDTO.getSellerContactInfoDTO() != null) {
            Optional<SellerContactInfoDTO> sellerLocationDTOOptional = sellerContactInfoContainer.getItemIds().stream()
                    .filter(dto -> dto.getSellerId() == itemDTO.getSellerContactInfoDTO().getSellerId()).findFirst();
            if (sellerLocationDTOOptional.isPresent()) {
                sellerCombobox.setValue(sellerLocationDTOOptional.get());
            }
        }
    }

    private void selectCurrentAddress(ItemDTO itemDTO, BeanItemContainer<AddressDTO> productLocationContainer, ComboBox addressCombobox) {
        if (itemDTO.getAddressDTO() != null) {
            Optional<AddressDTO> addressLocationDTOOptional = productLocationContainer.getItemIds().stream()
                    .filter(dto -> dto.getAddressId() == itemDTO.getAddressDTO().getAddressId()).findFirst();
            if (addressLocationDTOOptional.isPresent()) {
                addressCombobox.setValue(addressLocationDTOOptional.get());
            }
        }
    }
}
