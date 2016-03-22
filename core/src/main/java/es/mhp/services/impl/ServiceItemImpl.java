package es.mhp.services.impl;

import es.mhp.entities.Item;
import es.mhp.repositories.ItemRepository;
import es.mhp.services.IItemService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
@Transactional
@Configuration
@EnableJpaRepositories
public class ServiceItemImpl implements IItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Set<AbstractDTO> findAnyItems(String text) {
        Iterable<Item> itemSet = itemRepository.findByValue(text);

        Set<AbstractDTO> itemDTOs = new HashSet<>();

        for (Item currentItem : itemSet) {
            itemDTOs.add(new ItemDTO(currentItem));
        }

        return itemDTOs;
    }

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
            return new ItemDTO(itemRepository.save(itemDTO.toEntity()));
    }

    @Override
    public Set<AbstractDTO> findAll() {
        Iterable<Item> items = itemRepository.findAll();

        Set<AbstractDTO> addressDTOs = new HashSet<>();

        for (Item item : items){
            addressDTOs.add(new ItemDTO(item));
        }

        return addressDTOs;
    }

    @Override
    public void delete(Object id) {
        itemRepository.delete((Integer) id);
    }
}
