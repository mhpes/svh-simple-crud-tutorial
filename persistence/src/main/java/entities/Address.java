package entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "ADDRESS")
public class Address extends AbstractEntity{

    @Id
    @SequenceGenerator(name="address_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="address_sequence")
    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items;

    @Column(name = "STREET1")
    @Size(max = 55)
    private String mainStreet;

    @Column(name = "STREET2")
    @Size(max = 55)
    private String secondaryStreet;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Ziplocation.class)
    @JoinColumn(name = "zipCodeId")
    private Ziplocation ziplocation;

    @Column(name = "CITY")
    @Size(max = 55)
    private String city;

    @Column(name = "STATE")
    @Size(max = 25)
    private String state;

    @Column(name = "LATITUDE")
    @Digits(integer = 4, fraction = 10)
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    @Digits(integer = 4, fraction = 10)
    private BigDecimal longitude;

    Address(){}

    public Address(String secondaryStreet){this.setSecondaryStreet(secondaryStreet);}

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Ziplocation getZiplocation() {
        return ziplocation;
    }

    public void setZiplocation(Ziplocation ziplocation) {
        this.ziplocation = ziplocation;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}