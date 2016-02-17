package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Edu on 11/02/2016.
 */

@Entity
@Table(name = "TAG")
public class Tag extends AbstractEntity {

    @Id
    @SequenceGenerator(name="tag_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tag_sequence")
    @Column(name = "TAG_ID")
    private Integer tagId;

    @Column(name = "TAG")
    @Size(max = 30)
    private String tagDescription;

    /*@Doubt: this field is needed? because I think this field could be obtained as:
     select count(*) from ITEM i, TAG t where i.TAG_ID = t.TAG_ID and i.TAG_ID = :TAG_ID
     if the meaning of the field is the number of occurrences for each TAG*/
    @Column(name = "REF_COUNT")
    private Integer refCount;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="TAG_ITEM", joinColumns = {@JoinColumn(name="tagId")}, inverseJoinColumns={@JoinColumn(name="itemId")})
    private List<Item> items;

    Tag() {}

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
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
}
