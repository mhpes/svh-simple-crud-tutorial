package es.mhp.repositories;

import es.mhp.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
    @Query(value = "SELECT * from Category" +
            " where CATEGORYID like %?1%" +
            " OR NAME like %?1%" +
            " OR DESCRIPTION like %?1%" +
            " OR IMAGEURL like %?1%", nativeQuery = true)
    List<Category> findByValue(String value);
}