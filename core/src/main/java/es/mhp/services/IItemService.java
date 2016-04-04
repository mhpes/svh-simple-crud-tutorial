package es.mhp.services;

import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ItemDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IItemService extends AbstractService{
    Set<AbstractDTO> findAnyItems(String text);
    ItemDTO save(ItemDTO itemDTO);
}
