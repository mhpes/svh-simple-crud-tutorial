package es.mhp.services.impl;

import es.mhp.dao.ITagDao;
import es.mhp.entities.Tag;
import es.mhp.services.ITagService;
import es.mhp.services.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 * Created by Edu on 24/02/2016.
*/


@Service
@Transactional
public class ServiceTagImpl implements ITagService {

    @Autowired
    private ITagDao iTagDao;

    @Override
    public Set<TagDTO> findAllTags() {
        Set<Tag> tagSet = iTagDao.findAll();

        Set<TagDTO> tagDTOs = new HashSet<>();

        for (Tag tag : tagSet){
            tagDTOs.add(new TagDTO(tag));
        }

        return tagDTOs;
    }

    @Override
    public Set<TagDTO> findAllTags(Tag tag) {
        Set<Tag> tagSet = iTagDao.findAll(tag);

        Set<TagDTO> tagDTOs = new HashSet<>();

        for (Tag currentTag : tagSet) {
            tagDTOs.add(new TagDTO(currentTag));
        }

        return tagDTOs;
    }

    @Override
    public Set<TagDTO> findAnyTags(Tag tag) {
        Set<Tag> tagSet = iTagDao.findAny(tag);

        if (!tagSet.isEmpty()){
            Set<TagDTO> tagDTOs = new HashSet<>();

            for (Tag currentTag : tagSet) {
                tagDTOs.add(new TagDTO(currentTag));
            }

            return tagDTOs;
        }
        return Collections.emptySet();
    }

    @Override
    public TagDTO save(TagDTO tagDTO) {
        Tag tag = iTagDao.findById(tagDTO.getTagId());

        if (tag != null){
            iTagDao.update(tagDTO.toEntity(tag));
        } else {
            tag = new Tag();
            iTagDao.save(tag);
        }
        return new TagDTO(tag);
    }

    @Override
    public void delete(TagDTO tagDTO) {
        iTagDao.deleteById(tagDTO.getTagId());
    }

    @Override
    public TagDTO findTagById(int id) {
        return new TagDTO(iTagDao.findById(id));
    }
}
