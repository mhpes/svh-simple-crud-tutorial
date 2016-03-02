package es.mhp.dao.impl;

import es.mhp.dao.IItemDao;
import es.mhp.entities.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
public class ItemDaoImpl extends AbstractPetshopGenericDao<Item> implements IItemDao {

    @Override
    public Item findById(int id) {
        return entityManager.find(Item.class, id);
    }

    @Override
    public Set<Item> findAny(Item entity) {
        return findAll(entity, false);
    }

    @Override
    public Set<Item> findAll(Item entity) {
        return findAll(entity, true);
    }

    @Override
    public Set<Item> findAll() {
        return new HashSet<> (entityManager.createQuery("SELECT a FROM Item a").getResultList());
    }

    @Override
    protected Set<Item> findAll(Item entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM Item a WHERE ";

            if (!StringUtils.isEmpty(entity.getName())) {
                queryParameters += "NAME = " + entity.getName() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getDescription())) {
                queryParameters += "DESCRIPTION = " + entity.getDescription() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getImageUrl())) {
                queryParameters += "IMAGEURL = " + entity.getImageUrl() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getImageThumbUrl())) {
                queryParameters += "IMAGETHUMBURL = " + entity.getImageThumbUrl() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getPrice())) {
                queryParameters += "PRICE = " + entity.getPrice() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getTotalScore())) {
                queryParameters += "TOTALSCORE = " + entity.getTotalScore() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getImageUrl())) {
                queryParameters += "NUMBEROFVOTES = " + entity.getNumberOfVotes() + contatenator;
            }

            //queryParameters += "DISABLED = " + entity.isDisabled() + contatenator;

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (Set<Item>) query.getResultList();
        }
        return Collections.emptySet();
    }

    @Override
    public void deleteById(int id) {
        Item item = findById(id);

        if (item != null) getEntityManager().remove(item);
    }
}
