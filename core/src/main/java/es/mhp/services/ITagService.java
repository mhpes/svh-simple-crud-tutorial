package es.mhp.services;

import es.mhp.services.dto.TagDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ITagService extends AbstractService {
    Set<TagDTO> findAllTags();
    Set<TagDTO> findAnyTags(String text);
    void save(TagDTO tagDTO);
    TagDTO findTagById(int id);
}
