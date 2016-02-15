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
    public List findAll() {
        Query query = entityManager.createQuery("SELECT a FROM ADDRESS a");
        return query.getResultList();
    }

    public Object findById(int id) {
        return entityManager.find(Address.class, id);
    }

    public void create(Object entity) {
        if (entity != null){
            entityManager.persist(entity);
        }
    }

    public Object update(Object entity) {
        entityManager.merge(entity);
        return entity;
    }

    public void delete(Object entity) {
        entityManager.remove(entity);
    }

    public void save(Object entity) {
        if (entity != null){
            entityManager.persist(entity);
        }
    }

    public List findAll(Address address) {
        return findAll(address, true);
    }

    public List findAny(Address address) {
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
        return Collections.emptyList();
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
