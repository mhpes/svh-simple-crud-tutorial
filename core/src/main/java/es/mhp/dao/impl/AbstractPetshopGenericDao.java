package es.mhp.dao.impl;

import net.sf.minuteProject.architecture.bsla.domain.AbstractDomainObject;
import net.sf.minuteProject.model.dao.jpa.GenericDaoJpaImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by isa on 15/02/2016.
 */
public abstract class AbstractPetshopGenericDao <T extends AbstractDomainObject> extends GenericDaoJpaImpl<T> {

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
}
