package es.mhp.repositories;

import es.mhp.entities.Address;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Set;

/**
 * Created by Edu on 11/03/2016.
 */

@NoRepositoryBean
public interface ICustomAddressRepository {
    Set<Address> findAny(Address address);
    Set<Address> findAll(Address address);
}
