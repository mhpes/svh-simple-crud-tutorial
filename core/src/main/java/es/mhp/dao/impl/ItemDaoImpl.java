package es.mhp.dao.impl;

import entities.Item;
import es.mhp.dao.IItemDao;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
public class ItemDaoImpl extends AbstractPetshopGenericDao<Item> implements IItemDao {

    @Override
    public Item findById(long id) {
        return entityManager.find(Item.class, id);
    }

    @Override
    public List<Item> findAny(Item entity) {
        return findAll(entity, false);
    }

    @Override
    public List<Item> findAll(Item entity) {
        return findAll(entity, true);
    }

    @Override
    public List<Item> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM ITEM a");
        return query.getResultList();
    }

    @Override
    protected List<Item> findAll(Item entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM ITEM a WHERE ";

            if (!StringUtils.isEmpty(entity.getName())) {
                queryParameters += "NAME = " + entity.getName() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getDescription())) {
                queryParameters += "DESCRIPTION = " + entity.getDescription() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getImageUrl())) {
                queryParameters += "IMAGE_URL = " + entity.getImageUrl() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getImageThumbUrl())) {
                queryParameters += "IMAGE_THUMB_ULR = " + entity.getImageThumbUrl() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getPrice())) {
                queryParameters += "PRICE = " + entity.getPrice() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getTotalScore())) {
                queryParameters += "TOTAL_SCORE = " + entity.getTotalScore() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getImageUrl())) {
                queryParameters += "NUMBER_OF_VOTES = " + entity.getNumberOfVotes() + contatenator;
            }

            //queryParameters += "DISABLED = " + entity.isDisabled() + contatenator;

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<Item>) query.getResultList();
        }
        return Collections.emptyList();
    }
}
