package es.mhp.services.dto;

import es.mhp.entities.Item;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Edu on 26/02/2016.
 */
public class ItemDTO extends AbstractDTO{

    private int itemId;
    private int associatedTagsCount;
    private String productSummary;
    private String productId;
    private int addressId;
    private int contactInfoId;
    private String imageThumbUrl;
    private BigDecimal price;
    private String addressSummary;
    private String sellerContactSummary;
    private Integer totalScore;
    private Integer numberOfVotes;
    private String name;
    private String description;
    private String imageUrl;
    private int disabled;

    public ItemDTO(Item item){
        if (item != null){
            this.itemId = item.getItemId();
            this.associatedTagsCount = item.getTagsCount();
            this.productSummary = calculateProductSummary(item);
            this.imageThumbUrl = item.getImageThumbUrl();
            this.price = item.getPrice();
            this.addressSummary = calculateAddressSummary(item);
            this.sellerContactSummary = calculateSellerContactSummary(item);
            this.totalScore = item.getTotalScore();
            this.numberOfVotes = item.getNumberOfVotes();
            this.name = item.getName();
            this.description = item.getDescription();
            this.imageUrl = item.getImageUrl();
        }
    }

    public ItemDTO() {

    }

    public ItemDTO(int addressId, int itemId, String productId, String name, String description, String imageUrl, String imageThumbUrl, BigDecimal price, int contactInfoId, int totalScore, int numberOfVotes, int disabled) {
        this.addressId = addressId;
        this.itemId = itemId;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageThumbUrl = imageThumbUrl;
        this.price = price;
        this.contactInfoId = contactInfoId;
        this.totalScore = totalScore;
        this.numberOfVotes = numberOfVotes;
        this.disabled = disabled;
    }

    private String calculateSellerContactSummary(Item item) {
        return item.getSeller() == null ? "" : (item.getSeller().getFirstName() + " " + item.getSeller().getLastName() + " - " + item.getSeller().getEmail());
    }

    private String calculateAddressSummary(Item item) {
        return item.getAddress() == null ? "" : (item.getAddress().getCity() + ". " + item.getAddress().getState() + ". " + item.getAddress().getMainStreet());
    }

    private String calculateProductSummary(Item item) {
        return item.getProduct() == null ? "" : (item.getProduct().getName() +". " + item.getProduct().getDescription());
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAssociatedTagsCount() {
        return associatedTagsCount;
    }

    public void setAssociatedTagsCount(int associatedTagsCount) {
        this.associatedTagsCount = associatedTagsCount;
    }

    public String getProductSummary() {
        return productSummary;
    }

    public void setProductSummary(String productSummary) {
        this.productSummary = productSummary;
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

    public String getAddressSummary() {
        return addressSummary;
    }

    public void setAddressSummary(String addressSummary) {
        this.addressSummary = addressSummary;
    }

    public String getSellerContactSummary() {
        return sellerContactSummary;
    }

    public void setSellerContactSummary(String sellerContactSummary) {
        this.sellerContactSummary = sellerContactSummary;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getContactInfoId() {
        return contactInfoId;
    }

    public void setContactInfoId(int contactInfoId) {
        this.contactInfoId = contactInfoId;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }
}
