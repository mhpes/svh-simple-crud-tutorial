package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Edu on 11/02/2016.
 */

@Entity
@Table(name = "TAG")
public class Tag implements Serializable {

    @Id
    @SequenceGenerator(name="tag_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tag_sequence")
    @Column(name = "TAG_ID")
    private Long tagId;

    @Column(name = "TAG")
    @Size(max = 30)
    private String tag;

    /*ToDo: this field is needed? because I think this field could be obtained as:
     select count(*) from ITEM i, TAG t where i.TAG_ID = t.TAG_ID and i.TAG_ID = :TAG_ID
     if the meaning of the field is the number of occurrences for each TAG*/
    @Column(name = "REF_COUNT")
    private int refCount;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Item> items;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    Tag() {
    }
}
