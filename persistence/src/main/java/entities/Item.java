package entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;


/**
 * Created by Edu on 11/02/2016.
 */

@Entity
@Table(name = "ITEM")
public class Item extends AbstractEntity implements Serializable {

    @Id
    @SequenceGenerator(name="item_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_sequence")
    @Column(name = "ITEM_ID")
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinColumn(name = "tagId")
    private Tag tag;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> products;

    @Column(name = "IMAGE_THUMB_ULR")
    @Size(max = 55)
    private String imageThumbUrl;

    @Column(name = "PRICE")
    @Digits(integer = 12, fraction = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Address> addresses;

    @ManyToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SellerContactInfo> sellerContactInfos;

    @Column(name = "TOTAL_SCORE")
    private int totalScore;

    @Column(name = "NUMBER_OF_VOTES")
    private int numberOfVotes;

    //@Doubt: default value true, this could be done in the constructor too?
    @Column(name = "DISABLED", columnDefinition = "boolean default true")
    private boolean disabled = true;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<SellerContactInfo> getSellerContactInfos() {
        return sellerContactInfos;
    }

    public void setSellerContactInfos(Set<SellerContactInfo> sellerContactInfos) {
        this.sellerContactInfos = sellerContactInfos;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    Item() {
    }
}
