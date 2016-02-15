package entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Edu on 11/02/2016.
 */

@Entity
@Table(name = "ITEM")
public class Item extends AbstractEntity {

    @Id
    @SequenceGenerator(name="item_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_sequence")
    @Column(name = "ITEM_ID")
    private Long itemId;

    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="items")
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "IMAGE_THUMB_ULR")
    @Size(max = 55)
    private String imageThumbUrl;

    @Column(name = "PRICE")
    @Digits(integer = 12, fraction = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @JoinColumn(name = "addressId")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SellerContactInfo.class)
    @JoinColumn(name = "sellerId")
    private SellerContactInfo seller;

    @Column(name = "TOTAL_SCORE")
    private int totalScore;

    @Column(name = "NUMBER_OF_VOTES")
    private int numberOfVotes;

    //@Doubt: default value true, this could be done in the constructor too?
    @Column(name = "DISABLED", columnDefinition = "boolean default true")
    private boolean disabled = true;

    @Column(name = "NAME")
    @Size(max = 30)
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 500)
    private String description;

    @Column(name = "IMAGE_URL")
    @Size(max = 55)
    private String imageUrl;

    Item() {}

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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
