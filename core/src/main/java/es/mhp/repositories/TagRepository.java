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
    @Query(value = "SELECT * from Tag " +
            "where FIRSTNAME like %?1%" +
            " OR LASTTNAME like %?1%" +
            " OR EMAIL like %?1%", nativeQuery = true)
    List<Tag> findByValue(String value);
}