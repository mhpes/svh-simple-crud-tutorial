package es.mhp.dao;

import es.mhp.entities.Tag;

/**
 * Created by Edu on 12/02/2016.
 */
public interface ITagDao extends IPetshopGenericDao<Tag>{
    Tag findById(int id);
    void deleteById(int id);
}
