package entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable{

    @Id
    @SequenceGenerator(name="address_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="address_sequence")
    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class)
    @JoinColumn(name = "itemId")
    private Item item;

    @Column(name = "MAIN_STREET")
    @Size(max = 55)
    private String mainStreet;

    @Column(name = "SECONDARY_STREET")
    @Size(max = 55)
    private String secondaryStreet;

    //ToAsk
    private Ziplocation ziplocation;

    @Column(name = "LATITUDE")
    @Digits(integer = 4, fraction = 10)
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    @Digits(integer = 4, fraction = 10)
    private BigDecimal longitude;

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getSecondaryStreet() {
        return secondaryStreet;
    }

    public void setSecondaryStreet(String secondaryStreet) {
        this.secondaryStreet = secondaryStreet;
    }

    public String getMainStreet() {
        return mainStreet;
    }

    public void setMainStreet(String mainStreet) {
        this.mainStreet = mainStreet;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    Address(){

    }
}

//ToDo mirar cómo es el tema de la clave ajena, tipo address_addressId