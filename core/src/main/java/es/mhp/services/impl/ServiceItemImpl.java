package es.mhp.services.impl;

import es.mhp.dao.IItemDao;
import es.mhp.entities.Item;
import es.mhp.services.IItemService;
import es.mhp.services.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
public class ServiceItemImpl implements IItemService {

    @Autowired
    private IItemDao iItemDao;

    public Set<ItemDTO> findAllItems() {
        Set<Item> itemSet = iItemDao.findAll();

        Set<ItemDTO> categoryDTOs = new HashSet<>();

        for (Item item : itemSet){
            categoryDTOs.add(new ItemDTO(item));
        }

        return categoryDTOs;
    }

    public Set<ItemDTO> findAllItems(Item item) {
        Set<Item> itemSet = iItemDao.findAll(item);

        Set<ItemDTO> categoryDTOs = new HashSet<>();

        for (Item currentItem : itemSet){
            categoryDTOs.add(new ItemDTO(currentItem));
        }

        return categoryDTOs;
    }

    public Set<ItemDTO> findAnyItem(Item item) {
        Set<Item> itemSet = iItemDao.findAny(item);

        Set<ItemDTO> categoryDTOs = new HashSet<>();

        for (Item currentItem : itemSet){
            categoryDTOs.add(new ItemDTO(currentItem));
        }

        return categoryDTOs;
    }

    public ItemDTO save(ItemDTO itemDTO) {
        return new ItemDTO(iItemDao.update(itemDTO));
    }

    public void delete(ItemDTO itemDTO) {
        iItemDao.delete(itemDTO);
    }

    public ItemDTO findItemById(int id) {
        return new ItemDTO(iItemDao.findById(id));
    }
}
