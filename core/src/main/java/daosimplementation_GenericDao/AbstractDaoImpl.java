package daosimplementation_GenericDao;

import net.sf.minuteProject.architecture.bsla.domain.AbstractDomainObject;
import net.sf.minuteProject.model.dao.GenericDaoImpl;
import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;

import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
public class AbstractDaoImpl extends GenericDaoImpl{
    @Override
    protected Long count(AbstractDomainObject abstractDomainObject, AbstractDomainObject t1, EntityMatchType entityMatchType, OperandType operandType, Boolean aBoolean) {
        return null;
    }

    @Override
    protected List find(AbstractDomainObject abstractDomainObject, AbstractDomainObject t1, AbstractDomainObject t2, EntityMatchType entityMatchType, OperandType operandType, Boolean aBoolean, QuerySortOrder querySortOrder, Integer integer, Integer integer1) {
        return null;
    }

    @Override
    protected String getSelectFrom() {
        return null;
    }

    @Override
    protected boolean isAllNull(AbstractDomainObject abstractDomainObject) {
        return false;
    }

    @Override
    protected String getSearchEqualWhereQueryChunk(AbstractDomainObject abstractDomainObject, boolean b, boolean b1) {
        return null;
    }

    @Override
    protected String findWhere(AbstractDomainObject abstractDomainObject, boolean b, boolean b1, OperandType operandType, Boolean aBoolean) {
        return null;
    }

    @Override
    protected String findOrder(AbstractDomainObject abstractDomainObject, QuerySortOrder querySortOrder) {
        return null;
    }

    @Override
    protected List searchPrototype(String s, Integer integer) {
        return null;
    }

    @Override
    protected AbstractDomainObject assignBlankToNull(AbstractDomainObject abstractDomainObject) {
        return null;
    }

    public void save(AbstractDomainObject abstractDomainObject) {

    }

    public void delete(AbstractDomainObject abstractDomainObject) {

    }

    public void insert(AbstractDomainObject abstractDomainObject) {

    }

    public void insert(List list) {

    }

    public AbstractDomainObject update(AbstractDomainObject abstractDomainObject) {
        return null;
    }

    public List list(AbstractDomainObject abstractDomainObject, AbstractDomainObject t1, QuerySortOrder querySortOrder) {
        return null;
    }
}
