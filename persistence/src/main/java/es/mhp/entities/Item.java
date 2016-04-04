package es.mhp.entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;


/**
 * Created by Edu on 11/02/2016.
 */

@Entity
@Table(name = "ITEM")
public class Item extends AbstractEntity {

    @Id
    @SequenceGenerator(name="item_sequence", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_sequence")
    @Column(name = "ITEMID")
    private Integer itemId;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy="items")
    private Set<Tag> tags;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ADDRESSID")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SellerContactInfo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTACTINFO_CONTACTINFOID")
    private SellerContactInfo seller;

    @Column(name = "TOTALSCORE")
    private Integer totalScore;

    @Column(name = "NUMBEROFVOTES")
    private Integer numberOfVotes;

    @Column(name = "DISABLED")
    private int disabled;

    @Column(name = "NAME")
    @Size(max = 30)
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 500)
    private String description;

    @Column(name = "IMAGEURL")
    @Size(max = 55)
    private String imageUrl;

    @Column(name = "IMAGETHUMBURL")
    @Size(max = 55)
    private String imageThumbUrl;

    @Column(name = "PRICE")
    @Digits(integer = 12, fraction = 2)
    private BigDecimal price;

    public Item() {}

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int isDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public SellerContactInfo getSeller() {
        return seller;
    }

    public void setSeller(SellerContactInfo seller) {
        this.seller = seller;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public int getTagsCount() {
        if (getTags() == null) return 0;
        return this.tags.size();
    }
}
