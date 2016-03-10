/*
package es.mhp.services.impl;

import es.mhp.dao.*;
import es.mhp.entities.*;
import es.mhp.services.IServicePetshop;
import es.mhp.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

*/
/**
 * Created by Edu on 15/02/2016.
 *//*

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
    private ZipLocationRepository iZiplocationDao;

    public Set<TagDTO> findAllTags() { return iTagDao.findAll(); }

    public Set<ItemDTO> findAllItems() {
        return iItemDao.findAll();
    }

    public Set<ProductDTO> findAllProducts() {
        return iProductDao.findAll();
    }

    public Set<AddressDTO> findAllAddresses() { return iAddressDao.findAll(); }

    public Set<CategoryDTO> findAllCategories() {
        return iCategoryDao.findAll();
    }

    public Set<ZipLocationDTO> findAllZipLocations() {
        return iZiplocationDao.findAll();
    }

    public Set<SellerContactInfoDTO> findAllSellers() {
        return iSellerDao.findAll();
    }

    public Set<TagDTO> findAllTags(Tag tag) {
        return iTagDao.findAll(tag);
    }

    public Set<ItemDTO> findAllItems(Item item) {
        return iItemDao.findAll(item);
    }

    public Set<ProductDTO> findAllProducts(Product product) {
        return iProductDao.findAll(product);
    }

    public Set<AddressDTO> findAllAddresses(Address address) {
        return iAddressDao.findAll(address);
    }

    public Set<CategoryDTO> findAllCategories(Category category) {
        return iCategoryDao.findAll(category);
    }

    public Set<ZipLocationDTO> findAllZipLocations(ZipLocation zipLocation) {
        return iZiplocationDao.findAll(zipLocation);
    }

    public Set<SellerContactInfoDTO> findAllSellers(SellerContactInfo sellerContactInfo) {
        return iSellerDao.findAll(sellerContactInfo);
    }

    public Set<TagDTO> findAnyTag(Tag tag) {
        return iTagDao.findAny(tag);
    }

    public Set<ItemDTO> findAnyItem(Item item) {
        return iItemDao.findAny(item);
    }

    public Set<ProductDTO> findAnyProduct(Product product) {
        return iProductDao.findAny(product);
    }

    public Set<AddressDTO> findAnyAddresse(Address address) {
        return iAddressDao.findAny(address);
    }

    public Set<CategoryDTO> findAnyCategorie(Category category) {
        return iCategoryDao.findAny(category);
    }

    public Set<ZipLocationDTO> findAnyZipLocation(ZipLocation zipLocation) {
        return iZiplocationDao.findAny(zipLocation);
    }

    public Set<SellerContactInfoDTO> findAnySeller(SellerContactInfo sellerContactInfo) {
        return iSellerDao.findAny(sellerContactInfo);
    }

    public TagDTO update(Tag tag) {
        return iTagDao.update(tag);
    }

    public ItemDTO update(Item item) {
        return iItemDao.update(item);
    }

    public ProductDTO update(Product product) {
        return iProductDao.update(product);
    }

    public AddressDTO update(Address address) {
        return iAddressDao.update(address);
    }

    public CategoryDTO update(Category category) {
        return iCategoryDao.update(category);
    }

    public ZipLocationDTO update(ZipLocation zipLocation) {
        return iZiplocationDao.update(zipLocation);
    }

    public SellerContactInfoDTO update(SellerContactInfo sellerContactInfo) {
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

    public TagDTO findTagById(long id) {
        return iTagDao.findById(id);
    }

    public ItemDTO findItemById(long id) {
        return iItemDao.findById(id);
    }

    public ProductDTO findProductById(long id) {
        return iProductDao.findById(id);
    }

    public AddressDTO findAddressById(long id) {
        return iAddressDao.findById(id);
    }

    public CategoryDTO findCategoryById(long id) {
        return iCategoryDao.findById(id);
    }

    public ZipLocationDTO findZipById(long id) {
        return iZiplocationDao.findById(id);
    }

    public SellerContactInfoDTO findSellerById(long id) {
        return iSellerDao.findById(id);
    }
}
*/
