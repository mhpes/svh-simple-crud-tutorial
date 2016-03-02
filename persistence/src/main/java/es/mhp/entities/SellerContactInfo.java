package es.mhp.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "SELLERCONTACTINFO")
public class SellerContactInfo extends AbstractEntity{

    @Id
    @SequenceGenerator(name="seller_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seller_sequence")
    @Column(name = "CONTACTINFOID")
    private Integer sellerId;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Item.class)
    @JoinColumn(name = "itemId")
    private Set<Item> item;

    @Column(name = "LASTNAME")
    @Size(max = 24)
    private String lastName;

    @Column(name = "FIRSTNAME")
    @Size(max = 24)
    private String firstName;

    @Column(name = "EMAIL")
    @Size(max = 24)
    private String email;

    public SellerContactInfo(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }
}
