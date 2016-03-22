package es.mhp.services.impl;

import es.mhp.entities.Tag;
import es.mhp.repositories.TagRepository;
import es.mhp.services.ITagService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/*
 * Created by Edu on 24/02/2016.
*/


@Service
@Transactional
@Configuration
@EnableJpaRepositories
public class ServiceTagImpl implements ITagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Set<AbstractDTO> findAnyTags(String text) {
        Iterable<Tag> tagSet = tagRepository.findByValue(text);

        Set<AbstractDTO> zipLocationDTOs = new HashSet<>();

        for (Tag currentTag : tagSet) {
            zipLocationDTOs.add(new TagDTO(currentTag));
        }

        return zipLocationDTOs;
    }

    @Override
    public void save(TagDTO tagDTO) {
        tagRepository.save(tagDTO.toEntity());
    }

    @Override
    public Set<AbstractDTO> findAll() {
        Iterable<Tag> tagSet = tagRepository.findAll();

        Set<AbstractDTO> zipLocationDTOs = new HashSet<>();

        for (Tag currentTag : tagSet) {
            zipLocationDTOs.add(new TagDTO(currentTag));
        }

        return zipLocationDTOs;
    }

    @Override
    public void delete(Object id) {
        tagRepository.delete((Integer) id);
    }
}
