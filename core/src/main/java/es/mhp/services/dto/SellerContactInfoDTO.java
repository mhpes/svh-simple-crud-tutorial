package es.mhp.services.dto;

import es.mhp.entities.SellerContactInfo;
import org.springframework.beans.BeanUtils;

/**
 * Created by Edu on 26/02/2016.
 */
public class SellerContactInfoDTO extends AbstractDTO<SellerContactInfo> {

    private int sellerId;
    private String lastName;
    private String firstName;
    private String email;

    public SellerContactInfoDTO() { }

    @Override
    public SellerContactInfo toEntity(SellerContactInfo sellerContactInfo) {
        BeanUtils.copyProperties(this, sellerContactInfo);
        return sellerContactInfo;
    }

    @Override
    public Object getId() {
        return getSellerId();
    }

    @Override
    public SellerContactInfo toEntity() {
        SellerContactInfo sellerContactInfo = new SellerContactInfo();
        BeanUtils.copyProperties(this, sellerContactInfo);
        return sellerContactInfo;
    }

    public SellerContactInfoDTO(int sellerId, String lastName, String firstName, String email) {
        this.sellerId = sellerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public SellerContactInfoDTO(SellerContactInfo sellerContactInfo) {
        if (sellerContactInfo != null) {
            this.sellerId = sellerContactInfo.getSellerId();
            this.lastName = sellerContactInfo.getLastName();
            this.firstName = sellerContactInfo.getFirstName();
            this.email = sellerContactInfo.getEmail();
        }
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
