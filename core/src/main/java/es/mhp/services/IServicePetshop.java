package es.mhp.services;

import es.mhp.entities.*;
import es.mhp.services.dto.*;

import java.util.Set;

/**
 * Created by Edu on 15/02/2016.
 */
public interface IServicePetshop {

    Set<TagDTO> findAllTags();
    Set<ItemDTO> findAllItems();
    Set<ProductDTO> findAllProducts();
    Set<AddressDTO> findAllAddresses();
    Set<CategoryDTO> findAllCategories();
    Set<ZipLocationDTO> findAllZipLocations();
    Set<SellerContactInfoDTO> findAllSellers();

    Set<TagDTO> findAllTags(Tag tag);
    Set<ItemDTO> findAllItems(Item item);
    Set<ProductDTO> findAllProducts(Product product);
    Set<AddressDTO> findAllAddresses(Address address);
    Set<CategoryDTO> findAllCategories(Category category);
    Set<ZipLocationDTO> findAllZipLocations(ZipLocation zipLocation);
    Set<SellerContactInfoDTO> findAllSellers(SellerContactInfo sellerContactInfo);

    Set<TagDTO> findAnyTag(Tag tag);
    Set<ItemDTO> findAnyItem(Item item);
    Set<ProductDTO> findAnyProduct(Product product);
    Set<AddressDTO> findAnyAddresse(Address address);
    Set<CategoryDTO> findAnyCategorie(Category category);
    Set<ZipLocationDTO> findAnyZipLocation(ZipLocation zipLocation);
    Set<SellerContactInfoDTO> findAnySeller(SellerContactInfo sellerContactInfo);

    TagDTO update(Tag tag);
    ItemDTO update(Item item);
    ProductDTO update(Product product);
    AddressDTO update(Address address);
    CategoryDTO update(Category category);
    ZipLocationDTO update(ZipLocation zipLocation);
    SellerContactInfoDTO update(SellerContactInfo sellerContactInfo);

    void delete(Tag tag);
    void delete(Item item);
    void delete(Product product);
    void delete(Address address);
    void delete(Category category);
    void delete(ZipLocation zipLocation);
    void delete(SellerContactInfo sellerContactInfo);

    TagDTO findTagById(long id);
    ItemDTO findItemById(long id);
    ProductDTO findProductById(long id);
    AddressDTO findAddressById(long id);
    CategoryDTO findCategoryById(long id);
    ZipLocationDTO findZipById(long id);
    SellerContactInfoDTO findSellerById(long id);
}
