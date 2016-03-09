package es.mhp.services;

import es.mhp.services.dto.TagDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ITagService {
    Set<TagDTO> findAllTags();
    Set<TagDTO> findAllTags(TagDTO tagDTO);
    Set<TagDTO> findAnyTags(TagDTO tagDTO);
    Set<TagDTO> findAnyTags(String text);
    TagDTO save(TagDTO tagDTO);
    void delete(TagDTO tagDTO);
    TagDTO findTagById(int id);
}
