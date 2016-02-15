package es.mhp.dao.impl;


import entities.Address;
import es.mhp.dao.IAddressDao;
import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

public class AddressDaoImpl extends AbstractPetshopGenericDao<Address> implements IAddressDao {

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

    public Address update(Address address) {
        entityManager.merge(address);
        return address;
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
        if (address != null) {
            String queryParameters = "SELECT a FROM ADDRESS a WHERE ";

            if (!StringUtils.isEmpty(address.getMainStreet())) {
                queryParameters = "STREET1 = " + address.getMainStreet() + " AND ";
            }

            if (!StringUtils.isEmpty(address.getSecondaryStreet())) {
                queryParameters = "STREET2 = " + address.getSecondaryStreet() + " AND ";
            }

            if (!StringUtils.isEmpty(address.getCity())) {
                queryParameters = "CITY = " + address.getCity() + " AND ";
            }

            if (!StringUtils.isEmpty(address.getState())) {
                queryParameters = "STATE = " + address.getState() + " AND ";
            }

            if (!StringUtils.isEmpty(address.getLatitude()) && !StringUtils.isEmpty(address.getLongitude())) {
                queryParameters = "LATITUDE = " + address.getLatitude() +
                        "AND LONGITUDE = " + address.getLongitude() + " AND ";
            }

            if (!StringUtils.isEmpty(address.getLatitude()) && !StringUtils.isEmpty(address.getLongitude())) {
                queryParameters = "LATITUDE = " + address.getLatitude() +
                        "AND LONGITUDE = " + address.getLongitude() + " AND ";
            }

            queryParameters = "SELECT a FROM ADDRESS a WHERE " + replaceLast(queryParameters, " AND ", "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<Address>) query.getResultList();
        }
        return Collections.<Address>emptyList();
    }

    public List<Address> findAny(Address address) {
        return null;
    }

    private String replaceLast(String string, String substring, String replacement)
    {
        int index = string.lastIndexOf(substring);
        if (index == -1)
            return string;
        return string.substring(0, index) + replacement
                + string.substring(index+substring.length());
    }


    @Override
    protected String getQuerySelectFromEntity() {
        return null;
    }

    @Override
    protected Long count(Address address, Address t1, EntityMatchType entityMatchType, OperandType operandType, Boolean aBoolean) {
        return null;
    }

    @Override
    protected List<Address> find(Address address, Address t1, Address t2, EntityMatchType entityMatchType, OperandType operandType, Boolean aBoolean, QuerySortOrder querySortOrder, Integer integer, Integer integer1) {
        return null;
    }

    @Override
    protected String getSelectFrom() {
        return null;
    }

    @Override
    protected boolean isAllNull(Address address) {
        return false;
    }

    @Override
    protected String getSearchEqualWhereQueryChunk(Address address, boolean b, boolean b1) {
        return null;
    }

    @Override
    protected String findWhere(Address address, boolean b, boolean b1, OperandType operandType, Boolean aBoolean) {
        return null;
    }

    @Override
    protected String findOrder(Address address, QuerySortOrder querySortOrder) {
        return null;
    }

    @Override
    protected Address assignBlankToNull(Address address) {
        return null;
    }

    public List<Address> list(Address address, Address t1, QuerySortOrder querySortOrder) {
        return null;
    }
}
