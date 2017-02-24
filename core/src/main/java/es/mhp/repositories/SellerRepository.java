package es.mhp.repositories;

import es.mhp.entities.SellerContactInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */


@Repository
public interface SellerRepository extends CrudRepository<SellerContactInfo, Integer> {
    @Query(value = "SELECT a from SellerContactInfo a" +
            " where FIRSTNAME like %?1%" +
            " OR LASTNAME like %?1%" +
            " OR EMAIL like %?1%")
    List<SellerContactInfo> findByValue(String value);
}