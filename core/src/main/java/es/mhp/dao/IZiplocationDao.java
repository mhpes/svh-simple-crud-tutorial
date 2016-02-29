package es.mhp.dao;

import es.mhp.entities.ZipLocation;

/**
 * Created by Edu on 12/02/2016.
 */
public interface IZiplocationDao extends IPetshopGenericDao<ZipLocation> {
    void deleteById(long id);
}
