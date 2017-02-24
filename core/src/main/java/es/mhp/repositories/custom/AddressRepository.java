package es.mhp.repositories.custom;


import es.mhp.entities.Address;
import es.mhp.repositories.IAddressRepositoryCustom;
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
            "where STREET1 like %?1% " +
            "OR STREET2 like %?1% " +
            "OR CITY like %?1% " +
            "OR STATE like %?1%")
    Set<Address> findAny(String text);

    @Query(value = "SELECT DISTINCT STATE FROM Address a", nativeQuery = true)
    Set<String> findAllStates();
}