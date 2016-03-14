package es.mhp.repositories;

import es.mhp.entities.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */


@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
    @Query(value = "SELECT a from Tag a " +
            "where TAG like %?1%")
    List<Tag> findByValue(String value);
}