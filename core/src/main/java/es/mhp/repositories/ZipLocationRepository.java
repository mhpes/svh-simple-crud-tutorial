package es.mhp.repositories;

import es.mhp.entities.ZipLocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

@Repository
public interface ZipLocationRepository extends CrudRepository<ZipLocation, Integer> {
    @Query(value = "SELECT a from ZipLocation a " +
            "where ZIPCODE like %?1% " +
            "OR CITY like %?1% " +
            "OR STATE like %?1%")
    List<ZipLocation> findByValue(String value);
}


