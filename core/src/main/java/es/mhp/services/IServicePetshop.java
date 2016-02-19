package es.mhp.services;

import entities.*;

import java.util.List;

/**
 * Created by Edu on 15/02/2016.
 */
public interface IServicePetshop {

    String test();

    List<Tag> findAllTags();
    List<Item> findAllItems();
    List<Product> findAllProducts();
    List<Address> findAllAddresses();
    List<Category> findAllCategories();
    List<Ziplocation> findAllZipLocations();
    List<SellerContactInfo> findAllSellers();

    List<Tag> findAllTags(Tag tag);
    List<Item> findAllItems(Item item);
    List<Product> findAllProducts(Product product);
    List<Address> findAllAddresses(Address address);
    List<Category> findAllCategories(Category category);
    List<Ziplocation> findAllZipLocations(Ziplocation ziplocation);
    List<SellerContactInfo> findAllSellers(SellerContactInfo sellerContactInfo);

    List<Tag> findAnyTag(Tag tag);
    List<Item> findAnyItem(Item item);
    List<Product> findAnyProduct(Product product);
    List<Address> findAnyAddresse(Address address);
    List<Category> findAnyCategorie(Category category);
    List<Ziplocation> findAnyZipLocation(Ziplocation ziplocation);
    List<SellerContactInfo> findAnySeller(SellerContactInfo sellerContactInfo);

    Tag update(Tag tag);
    Item update(Item item);
    Product update(Product product);
    Address update(Address address);
    Category update(Category category);
    Ziplocation update(Ziplocation ziplocation);
    SellerContactInfo update(SellerContactInfo sellerContactInfo);

    void delete(Tag tag);
    void delete(Item item);
    void delete(Product product);
    void delete(Address address);
    void delete(Category category);
    void delete(Ziplocation ziplocation);
    void delete(SellerContactInfo sellerContactInfo);

    Tag findTagById(long id);
    Item findItemById(long id);
    Product findProductById(long id);
    Address findAddressById(long id);
    Category findCategoryById(long id);
    Ziplocation findZipById(long id);
    SellerContactInfo findSellerById(long id);
}
