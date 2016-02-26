package es.mhp.services.impl;

import es.mhp.dao.IItemDao;
import es.mhp.entities.Item;
import es.mhp.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
public class ServiceItemImpl implements IItemService {

    @Autowired
    private IItemDao iItemDao;

    public Set<Item> findAllItems() {
        return iItemDao.findAll();
    }

    public Set<Item> findAllItems(Item item) {
        return iItemDao.findAll(item);
    }

    public Set<Item> findAnyItem(Item item) {
        return iItemDao.findAny(item);
    }

    public Item update(Item item) {
        return iItemDao.update(item);
    }

    public void delete(Item item) {
        iItemDao.delete(item);
    }

    public Item findItemById(long id) {
        return iItemDao.findById(id);
    }
}
