package es.mhp.services.dto;

import es.mhp.entities.Item;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * Created by Edu on 26/02/2016.
 */
public class ItemDTO extends AbstractDTO<Item>{

    private int itemId;
    private AddressDTO addressDTO;
    private ProductDTO productDTO;
    private SellerContactInfoDTO sellerContactInfoDTO;

    private String productSummary;
    private String addressSummary;
    private int associatedTagsCount;
    private String sellerContactSummary;

    private String name;
    private int disabled;
    private String imageUrl;
    private BigDecimal price;
    private Integer totalScore;
    private String description;
    private String imageThumbUrl;
    private Integer numberOfVotes;

    @Override
    public Item toEntity() {
        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        return item;
    }

    @Override
    public Item toEntity(Item item) {
        BeanUtils.copyProperties(this, item);
        return item;
    }

    @Override
    public Object getId() {
        return getItemId();
    }


    public ItemDTO(Item item){
        if (item != null){
            this.itemId = item.getItemId();
            this.associatedTagsCount = item.getTagsCount();
            this.productSummary = calculateProductSummary(item);

            if (item.getProduct() != null){
                this.productDTO = new ProductDTO(item.getProduct());
            }

            if (item.getSeller() != null){
                this.sellerContactInfoDTO = new SellerContactInfoDTO(item.getSeller());
            }

            if (item.getAddress() != null){
                this.addressDTO = new AddressDTO(item.getAddress());
            }

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

    public ItemDTO(AddressDTO addressDTO, int itemId, ProductDTO productDTO, String name, String description, String imageUrl,
                   String imageThumbUrl, BigDecimal price, SellerContactInfoDTO sellerContactInfoDTO, int totalScore, int numberOfVotes, int disabled) {
        this.addressDTO = addressDTO;
        this.itemId = itemId;
        this.productDTO = productDTO;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageThumbUrl = imageThumbUrl;
        this.price = price;
        this.sellerContactInfoDTO = sellerContactInfoDTO;
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

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public SellerContactInfoDTO getSellerContactInfoDTO() {
        return sellerContactInfoDTO;
    }

    public void setSellerContactInfoDTO(SellerContactInfoDTO sellerContactInfoDTO) {
        this.sellerContactInfoDTO = sellerContactInfoDTO;
    }
}
