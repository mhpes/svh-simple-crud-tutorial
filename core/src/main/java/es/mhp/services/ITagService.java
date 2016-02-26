package es.mhp.services;

import es.mhp.entities.Tag;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ITagService {
    Set<Tag> findAllTags();
    Set<Tag> findAllTags(Tag tag);
    Set<Tag> findAnyTag(Tag tag);
    Tag update(Tag tag);
    void delete(Tag tag);
    Tag findTagById(long id);
}
