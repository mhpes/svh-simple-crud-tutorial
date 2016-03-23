package es.mhp.services;

import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.TagDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ITagService extends AbstractService {
    Set<AbstractDTO> findAnyTags(String text);
    TagDTO save(TagDTO tagDTO);
}
