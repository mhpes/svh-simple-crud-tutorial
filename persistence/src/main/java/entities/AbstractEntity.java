package entities;

import net.sf.minuteProject.architecture.bsla.domain.AbstractDomainObject;

import java.io.Serializable;

/**
 * Created by Edu on 12/02/2016.
 */
public class AbstractEntity extends AbstractDomainObject{
    @Override
    public boolean equalsMask(Object o) {
        return false;
    }

    @Override
    public AbstractDomainObject mask(String s) {
        return null;
    }

    @Override
    public AbstractDomainObject clone() {
        return null;
    }
}
