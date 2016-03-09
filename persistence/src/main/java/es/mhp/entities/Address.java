package es.mhp.entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "ADDRESS")
public class Address extends AbstractEntity {

    @Id
    @SequenceGenerator(name="address_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="address_sequence")
    @Column(name = "ADDRESSID")
    private int addressId;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Item> items;

    @Column(name = "STREET1")
    @NotBlank
    @Size(max = 55, message = "The maximum size for Main Street is 55 and minimum is 1")
    private String mainStreet;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ZipLocation.class)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "zip", referencedColumnName = "ZIPCODE")
    private ZipLocation zipLocation;

    @Column(name = "STREET2")
    @NotBlank
    @Size(max = 55)
    private String secondaryStreet;

    @Column(name = "CITY")
    @NotBlank
    @Size(max = 55)
    private String city;

    @Column(name = "STATE")
    @NotBlank
    @Size(max = 25)
    private String state;

    @Column(name = "LATITUDE")
    @Digits(integer = 4, fraction = 10)
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    @Digits(integer = 4, fraction = 10)
    private BigDecimal longitude;

    public Address(int addressId, String mainStreet, String secondaryStreet, String city, String state, BigDecimal longitude, BigDecimal latitude, ZipLocation zipLocation){
        setAddressId(addressId);
        setMainStreet(mainStreet);
        setSecondaryStreet(secondaryStreet);
        setCity(city);
        setState(state);
        setLongitude(longitude);
        setLatitude(latitude);
        setZipLocation(zipLocation);
    }

    public Address(){}

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

    public ZipLocation getZipLocation() {
        return zipLocation;
    }

    public void setZipLocation(ZipLocation zipLocation) {
        this.zipLocation = zipLocation;
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

    public Set<Item> getItems() {
        return items;
    }

    public int getItemsCount() {
        if (getItems() == null)
            return 0;
        return this.items.size();
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}