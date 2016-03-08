package es.mhp.dao.impl;

import es.mhp.dao.ITagDao;
import es.mhp.entities.Tag;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
@Transactional
public class TagDaoImpl extends AbstractPetshopGenericDao<Tag> implements ITagDao {

    @Override
    public Tag findById(int id) {
        return getEntityManager().find(Tag.class, id);
    }

    @Override
    public void deleteById(int id) {
        Tag tag = findById(id);

        if (tag != null) {
            getEntityManager().remove(tag);
        }
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
    public Set<Tag> findAny(String text) {
        return null;
    }

    @Override
    public Set<Tag> findAll() {
        return new HashSet<> (entityManager.createQuery("SELECT a FROM Tag a").getResultList());
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
