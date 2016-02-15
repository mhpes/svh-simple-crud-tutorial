package daosimplementation;

import daos.IAdressDao;
import entities.Address;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 15/02/2016.
 */

@Transactional
public class AdressDaoImpl implements IAdressDao{

    @PersistenceContext
    private EntityManager entityManager;

    public List<Address> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM ADDRESS a");
        return (List<Address>) query.getResultList();
    }

    public Address findById(int id) {
        return entityManager.find(Address.class, id);
    }

    //ToDo to change when we understand the differences
    public void create(Address address) {
        if (address != null){
            entityManager.persist(address);
        }
    }

    public void update(Address address) {
        entityManager.merge(address);
    }

    public void delete(Address address) {
        entityManager.remove(address);
    }

    public void save(Address address) {
        if (address != null){
            entityManager.persist(address);
        }
    }

    public List<Address> findAll(Address address) {
        return findAll(address, true);
    }

    public List<Address> findAny(Address address) {
        return findAll(address, false);
    }

    public List<Address> findAll(Address address, boolean type) {

        String contatenator = type ? " AND ": " OR ";

        if (address != null) {
            String queryParameters = "SELECT a FROM ADDRESS a WHERE ";

            if (!StringUtils.isEmpty(address.getMainStreet())) {
                queryParameters = "STREET1 = " + address.getMainStreet() + contatenator;
            }

            if (!StringUtils.isEmpty(address.getSecondaryStreet())) {
                queryParameters = "STREET2 = " + address.getSecondaryStreet() + contatenator;
            }

            if (!StringUtils.isEmpty(address.getCity())) {
                queryParameters = "CITY = " + address.getCity() + contatenator;
            }

            if (!StringUtils.isEmpty(address.getState())) {
                queryParameters = "STATE = " + address.getState() + contatenator;
            }

            if (!StringUtils.isEmpty(address.getLatitude()) && !StringUtils.isEmpty(address.getLongitude())) {
                queryParameters = "LATITUDE = " + address.getLatitude() +
                        "AND LONGITUDE = " + address.getLongitude() + " AND ";
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<Address>) query.getResultList();
        }
        return Collections.<Address>emptyList();
    }

    private String replaceLast(String string, String substring, String replacement)
    {
        int index = string.lastIndexOf(substring);
        if (index == -1)
            return string;
        return string.substring(0, index) + replacement
                + string.substring(index+substring.length());
    }
}
