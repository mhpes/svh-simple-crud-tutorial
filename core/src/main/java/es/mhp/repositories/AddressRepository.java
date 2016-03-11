package es.mhp.repositories;


import es.mhp.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>, IAddressRepositoryCustom {
    @Query(value = "SELECT a from Address a " +
            "where a.STREET1 like %?1% " +
            "OR a.STREET2 like %?1% " +
            "OR a.CITY like %?1% " +
            "OR a.STATE like %?1%", nativeQuery = true)
    Set<Address> findAny(String text);

    @Query(value = "SELECT DISTINCT a.state FROM Address a", nativeQuery = true)
    Set<String> findAllStates();
}