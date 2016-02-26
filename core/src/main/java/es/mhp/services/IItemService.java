package es.mhp.services;

import es.mhp.entities.Item;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IItemService {
    Set<Item> findAllItems();
    Set<Item> findAllItems(Item item);
    Set<Item> findAnyItem(Item item);
    Item update(Item item);
    void delete(Item item);
    Item findItemById(long id);
}
