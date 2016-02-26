package es.mhp.services;

import es.mhp.entities.*;

import java.util.Set;

/**
 * Created by Edu on 15/02/2016.
 */
public interface IServicePetshop {

    Set<Tag> findAllTags();
    Set<Item> findAllItems();
    Set<Product> findAllProducts();
    Set<Address> findAllAddresses();
    Set<Category> findAllCategories();
    Set<ZipLocation> findAllZipLocations();
    Set<SellerContactInfo> findAllSellers();

    Set<Tag> findAllTags(Tag tag);
    Set<Item> findAllItems(Item item);
    Set<Product> findAllProducts(Product product);
    Set<Address> findAllAddresses(Address address);
    Set<Category> findAllCategories(Category category);
    Set<ZipLocation> findAllZipLocations(ZipLocation zipLocation);
    Set<SellerContactInfo> findAllSellers(SellerContactInfo sellerContactInfo);

    Set<Tag> findAnyTag(Tag tag);
    Set<Item> findAnyItem(Item item);
    Set<Product> findAnyProduct(Product product);
    Set<Address> findAnyAddresse(Address address);
    Set<Category> findAnyCategorie(Category category);
    Set<ZipLocation> findAnyZipLocation(ZipLocation zipLocation);
    Set<SellerContactInfo> findAnySeller(SellerContactInfo sellerContactInfo);

    Tag update(Tag tag);
    Item update(Item item);
    Product update(Product product);
    Address update(Address address);
    Category update(Category category);
    ZipLocation update(ZipLocation zipLocation);
    SellerContactInfo update(SellerContactInfo sellerContactInfo);

    void delete(Tag tag);
    void delete(Item item);
    void delete(Product product);
    void delete(Address address);
    void delete(Category category);
    void delete(ZipLocation zipLocation);
    void delete(SellerContactInfo sellerContactInfo);

    Tag findTagById(long id);
    Item findItemById(long id);
    Product findProductById(long id);
    Address findAddressById(long id);
    Category findCategoryById(long id);
    ZipLocation findZipById(long id);
    SellerContactInfo findSellerById(long id);
}
