package es.mhp.services.impl;

import es.mhp.dao.*;
import es.mhp.entities.*;
import es.mhp.services.IServicePetshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Edu on 15/02/2016.
 */
@Service
public class ServicePetshopImpl implements IServicePetshop {

    @Autowired
    private IAddressDao iAddressDao;

    @Autowired
    private ICategoryDao iCategoryDao;

    @Autowired
    private IItemDao iItemDao;

    @Autowired
    private IProductDao iProductDao;

    @Autowired
    private ISellerDao iSellerDao;

    @Autowired
    private ITagDao iTagDao;

    @Autowired
    private IZiplocationDao iZiplocationDao;

    public Set<Tag> findAllTags() { return iTagDao.findAll(); }

    public Set<Item> findAllItems() {
        return iItemDao.findAll();
    }

    public Set<Product> findAllProducts() {
        return iProductDao.findAll();
    }

    public Set<Address> findAllAddresses() { return iAddressDao.findAll(); }

    public Set<Category> findAllCategories() {
        return iCategoryDao.findAll();
    }

    public Set<ZipLocation> findAllZipLocations() {
        return iZiplocationDao.findAll();
    }

    public Set<SellerContactInfo> findAllSellers() {
        return iSellerDao.findAll();
    }

    public Set<Tag> findAllTags(Tag tag) {
        return iTagDao.findAll(tag);
    }

    public Set<Item> findAllItems(Item item) {
        return iItemDao.findAll(item);
    }

    public Set<Product> findAllProducts(Product product) {
        return iProductDao.findAll(product);
    }

    public Set<Address> findAllAddresses(Address address) {
        return iAddressDao.findAll(address);
    }

    public Set<Category> findAllCategories(Category category) {
        return iCategoryDao.findAll(category);
    }

    public Set<ZipLocation> findAllZipLocations(ZipLocation zipLocation) {
        return iZiplocationDao.findAll(zipLocation);
    }

    public Set<SellerContactInfo> findAllSellers(SellerContactInfo sellerContactInfo) {
        return iSellerDao.findAll(sellerContactInfo);
    }

    public Set<Tag> findAnyTag(Tag tag) {
        return iTagDao.findAny(tag);
    }

    public Set<Item> findAnyItem(Item item) {
        return iItemDao.findAny(item);
    }

    public Set<Product> findAnyProduct(Product product) {
        return iProductDao.findAny(product);
    }

    public Set<Address> findAnyAddresse(Address address) {
        return iAddressDao.findAny(address);
    }

    public Set<Category> findAnyCategorie(Category category) {
        return iCategoryDao.findAny(category);
    }

    public Set<ZipLocation> findAnyZipLocation(ZipLocation zipLocation) {
        return iZiplocationDao.findAny(zipLocation);
    }

    public Set<SellerContactInfo> findAnySeller(SellerContactInfo sellerContactInfo) {
        return iSellerDao.findAny(sellerContactInfo);
    }

    public Tag update(Tag tag) {
        return iTagDao.update(tag);
    }

    public Item update(Item item) {
        return iItemDao.update(item);
    }

    public Product update(Product product) {
        return iProductDao.update(product);
    }

    public Address update(Address address) {
        return iAddressDao.update(address);
    }

    public Category update(Category category) {
        return iCategoryDao.update(category);
    }

    public ZipLocation update(ZipLocation zipLocation) {
        return iZiplocationDao.update(zipLocation);
    }

    public SellerContactInfo update(SellerContactInfo sellerContactInfo) {
        return iSellerDao.update(sellerContactInfo);
    }

    public void delete(Tag tag) {
        iTagDao.delete(tag);
    }

    public void delete(Item item) {
        iItemDao.delete(item);
    }

    public void delete(Product product) {
        iProductDao.delete(product);
    }

    public void delete(Address address) {
        iAddressDao.delete(address);
    }

    public void delete(Category category) {
        iCategoryDao.delete(category);
    }

    public void delete(ZipLocation zipLocation) {
        iZiplocationDao.delete(zipLocation);
    }

    public void delete(SellerContactInfo sellerContactInfo) {
        iSellerDao.delete(sellerContactInfo);
    }

    public Tag findTagById(long id) {
        return iTagDao.findById(id);
    }

    public Item findItemById(long id) {
        return iItemDao.findById(id);
    }

    public Product findProductById(long id) {
        return iProductDao.findById(id);
    }

    public Address findAddressById(long id) {
        return iAddressDao.findById(id);
    }

    public Category findCategoryById(long id) {
        return iCategoryDao.findById(id);
    }

    public ZipLocation findZipById(long id) {
        return iZiplocationDao.findById(id);
    }

    public SellerContactInfo findSellerById(long id) {
        return iSellerDao.findById(id);
    }
}
