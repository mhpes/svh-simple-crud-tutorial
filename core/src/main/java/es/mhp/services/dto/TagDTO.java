package es.mhp.services.dto;

import es.mhp.entities.Tag;
import org.springframework.beans.BeanUtils;

/**
 * Created by Edu on 26/02/2016.
 */
public class TagDTO extends AbstractDTO<Tag>{

    private int tagId;
    private String tagDescription;
    private int refCount;

    @Override
    public Tag toEntity(Tag tag) {
        BeanUtils.copyProperties(this, tag);
        return tag;
    }

    public TagDTO(Tag tag) {
        if (tag != null) {
            this.tagId = tag.getTagId();
            this.tagDescription = tag.getTagDescription();
            this.refCount = tag.getRefCount();
        }
    }

    public TagDTO(Integer tagId, String tagDescription, Integer refCount) {
        this.tagId = tagId;
        this.tagDescription = tagDescription;
        this.refCount = refCount;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTag() {
        return tagDescription;
    }

    public void setTag(String tag) {
        this.tagDescription = tag;
    }

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }
}
