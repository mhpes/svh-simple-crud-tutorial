package es.mhp.repositories;

import es.mhp.entities.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer>{
    @Query(value = "SELECT a from Item a" +
            " where PRODUCTID like %?1%" +
            " OR NAME like %?1%" +
            " OR DESCRIPTION like %?1%" +
            " OR IMAGEURL like %?1%")
    List<Item> findByValue(String value);
}