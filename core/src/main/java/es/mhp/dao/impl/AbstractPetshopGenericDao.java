package es.mhp.dao.impl;

import entities.AbstractEntity;
import es.mhp.dao.IPetshopGenericDao;
import net.sf.minuteProject.model.dao.jpa.GenericDaoJpaImpl;
import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by isa on 15/02/2016.
 */
public abstract class AbstractPetshopGenericDao<T extends AbstractEntity> extends GenericDaoJpaImpl<T> implements IPetshopGenericDao<T> {

    @PersistenceContext(unitName = "petshop")
    protected EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected String getQuerySelectFromEntity() {
        return null;
    }

    @Override
    protected Long count(T t, T t1, EntityMatchType entityMatchType, OperandType operandType, Boolean aBoolean) {
        return null;
    }

    @Override
    protected List<T> find(T t, T t1, T t2, EntityMatchType entityMatchType, OperandType operandType, Boolean aBoolean, QuerySortOrder querySortOrder, Integer integer, Integer integer1) {
        return null;
    }

    @Override
    protected String getSelectFrom() {
        return null;
    }

    @Override
    protected boolean isAllNull(T t) {
        return false;
    }

    @Override
    protected String getSearchEqualWhereQueryChunk(T t, boolean b, boolean b1) {
        return null;
    }

    @Override
    protected String findWhere(T t, boolean b, boolean b1, OperandType operandType, Boolean aBoolean) {
        return null;
    }

    @Override
    protected String findOrder(T t, QuerySortOrder querySortOrder) {
        return null;
    }

    @Override
    protected T assignBlankToNull(T t) {
        return null;
    }

    public List<T> list(T t, T t1, QuerySortOrder querySortOrder) {
        return null;
    }

    protected abstract List<T> findAll(T address, boolean type);
}
