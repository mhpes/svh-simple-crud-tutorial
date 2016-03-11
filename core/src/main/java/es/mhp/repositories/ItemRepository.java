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
    @Query(value = "SELECT * from Item" +
            " where CATEGORYID like %?1%" +
            " OR NAME like %?1%" +
            " OR DESCRIPTION like %?1% OR" +
            " OR PRICE like %?1% OR" +
            " OR TOTALSCORE like %?1% OR" +
            " OR NUMBEROFVOTES like %?1% OR" +
            " IMAGEURL like %?1%", nativeQuery = true)
    List<Item> findByValue(String value);
}