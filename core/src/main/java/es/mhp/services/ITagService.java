package es.mhp.services;

import es.mhp.entities.Tag;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ITagService {
    List<Tag> findAllTags();
    List<Tag> findAllTags(Tag tag);
    List<Tag> findAnyTag(Tag tag);
    Tag update(Tag tag);
    void delete(Tag tag);
    Tag findTagById(long id);
}
