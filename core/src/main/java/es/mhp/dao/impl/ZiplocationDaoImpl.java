package es.mhp.dao.impl;

import entities.Ziplocation;
import es.mhp.dao.IZiplocationDao;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
public class ZiplocationDaoImpl extends AbstractPetshopGenericDao<Ziplocation> implements IZiplocationDao {

    public List<Ziplocation> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM ZIPLOCATION a");
        return query.getResultList();
    }

    @Override
    protected Ziplocation findById(long id) {
        return null;
    }

    public List<Ziplocation> findAll(Ziplocation ziplocation) {
        return findAll(ziplocation, true);
    }

    public List<Ziplocation> findAny(Ziplocation ziplocation) {
        return findAll(ziplocation, false);
    }

    protected List<Ziplocation> findAll(Ziplocation ziplocation, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (ziplocation != null) {
            String queryParameters = "SELECT a FROM ZIPLOCATION a WHERE ";

            if (!StringUtils.isEmpty(ziplocation.getZipCode())) {
                queryParameters = "ZIPCODE = " + ziplocation.getZipCode() + contatenator;
            }

            if (!StringUtils.isEmpty(ziplocation.getCity())) {
                queryParameters = "CITY = " + ziplocation.getCity() + contatenator;
            }

            if (!StringUtils.isEmpty(ziplocation.getState())) {
                queryParameters = "STATE = " + ziplocation.getState() + contatenator;
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<Ziplocation>) query.getResultList();
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
}



