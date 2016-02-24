package es.mhp.services;

import entities.Item;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IItemService {
    List<Item> findAllItems();
    List<Item> findAllItems(Item item);
    List<Item> findAnyItem(Item item);
    Item update(Item item);
    void delete(Item item);
    Item findItemById(long id);
}
