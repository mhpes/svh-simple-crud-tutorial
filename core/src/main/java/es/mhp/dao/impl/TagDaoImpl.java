package es.mhp.dao.impl;

import entities.Tag;
import es.mhp.dao.ITagDao;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
public class TagDaoImpl extends AbstractPetshopGenericDao<Tag> implements ITagDao {
    public Tag findById(long id) {
        return entityManager.find(Tag.class, id);
    }

    public List<Tag> findAny(Tag entity) {
        return findAll(entity, false);
    }

    public List<Tag> findAll(Tag entity) {
        return findAll(entity, true);
    }

    public List<Tag> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM TAG a");
        return query.getResultList();
    }

    @Override
    protected List<Tag> findAll(Tag entity, boolean type) {

        if (entity != null) {
            String queryParameters = "SELECT a FROM TAG a WHERE ";

            if (!StringUtils.isEmpty(entity.getTagId())) {
                queryParameters += "TAG = " + entity.getTagId();
            }

            Query query = entityManager.createQuery(queryParameters);

            return (List<Tag>) query.getResultList();
        }
        return Collections.emptyList();
    }

    @Override
    public String test() {
        return "Edumola";
    }
}
