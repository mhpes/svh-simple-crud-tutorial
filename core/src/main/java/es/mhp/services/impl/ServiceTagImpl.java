package es.mhp.services.impl;

import entities.Tag;
import es.mhp.dao.ITagDao;
import es.mhp.services.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
public class ServiceTagImpl implements ITagService {

    @Autowired
    private ITagDao iTagDao;

    public List<Tag> findAllTags() {
        return iTagDao.findAll();
    }

    public List<Tag> findAllTags(Tag tag) {
        return iTagDao.findAll(tag);
    }

    public List<Tag> findAnyTag(Tag tag) {
        return iTagDao.findAny(tag);
    }

    public Tag update(Tag tag) {
        return iTagDao.update(tag);
    }

    public void delete(Tag tag) {
        iTagDao.delete(tag);
    }

    public Tag findTagById(long id) {
        return iTagDao.findById(id);
    }
}
