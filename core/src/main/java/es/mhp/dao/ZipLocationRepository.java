package es.mhp.dao;

import es.mhp.entities.ZipLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

@Repository
public interface ZipLocationRepository extends CrudRepository<ZipLocation, Integer> {
    /*void deleteById(int id);*/
    ZipLocation findByzipCodeId(int zipCodeId);
    List<ZipLocation> findByCity(String city);
}
