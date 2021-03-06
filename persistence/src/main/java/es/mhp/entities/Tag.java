package es.mhp.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Edu on 11/02/2016.
 */

@Entity
@Table(name = "TAG")
public class Tag extends AbstractEntity {

    @Id
    @SequenceGenerator(name="tag_sequence", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tag_sequence")
    @Column(name = "TAGID")
    private Integer tagId;

    @Column(name = "TAG")
    @Size(max = 30, min = 1)
    private String tagDescription;

    @Column(name = "REFCOUNT")
    private Integer refCount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="TAG_ITEM",
            joinColumns = {@JoinColumn(name="tagId")},
            inverseJoinColumns={@JoinColumn(name="itemId")})
    private Set<Item> items;

    public Tag() {}

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public void setRefCount(Integer refCount) {
        this.refCount = refCount;
    }
}
