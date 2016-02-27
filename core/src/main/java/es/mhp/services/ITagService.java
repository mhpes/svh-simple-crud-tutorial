package es.mhp.services;

import es.mhp.entities.Tag;
import es.mhp.services.dto.TagDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ITagService {
    Set<TagDTO> findAllTags();
    Set<TagDTO> findAllTags(Tag tag);
    Set<TagDTO> findAnyTag(Tag tag);
    TagDTO update(Tag tag);
    void delete(Tag tag);
    TagDTO findTagById(long id);
}
