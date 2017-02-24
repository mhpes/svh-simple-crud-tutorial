package es.mhp.services;

import es.mhp.services.dto.AbstractDTO;

import java.util.Set;

/**
 * Created by Edu on 26/02/2016.
 */
public interface AbstractService {
    Set<AbstractDTO> findAll();
    void delete(Object id);
}
