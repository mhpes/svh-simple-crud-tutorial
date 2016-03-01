package es.mhp.dao.impl;

import es.mhp.dao.ITagDao;
import es.mhp.entities.Tag;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
public class TagDaoImpl extends AbstractPetshopGenericDao<Tag> implements ITagDao {

    @Override
    public Tag findById(int id) {
        return entityManager.find(Tag.class, id);
    }

    @Override
    public Set<Tag> findAny(Tag entity) {
        return findAll(entity, false);
    }

    @Override
    public Set<Tag> findAll(Tag entity) {
        return findAll(entity, true);
    }

    @Override
    public Set<Tag> findAll() {
        return (Set<Tag>) entityManager.createQuery("SELECT a FROM Tag a").getResultList();
    }

    @Override
    protected Set<Tag> findAll(Tag entity, boolean type) {

        if (entity != null) {
            String queryParameters = "SELECT a FROM Tag a WHERE ";

            if (!StringUtils.isEmpty(entity.getTagId())) {
                queryParameters += "TAG = " + entity.getTagId();
            }

            Query query = entityManager.createQuery(queryParameters);

            return (Set<Tag>) query.getResultList();
        }
        return Collections.emptySet();
    }
}
