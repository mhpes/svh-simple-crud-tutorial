package es.mhp.services.dto;

import java.io.Serializable;

/**
 * Created by Edu on 26/02/2016.
 */
public abstract class AbstractDTO<T> implements Serializable{
    public abstract T toEntity();
    public abstract T toEntity(T entity);
    public abstract Object getId();
}
