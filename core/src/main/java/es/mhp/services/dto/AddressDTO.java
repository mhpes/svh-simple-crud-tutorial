package es.mhp.services.dto;

import es.mhp.entities.Address;
import es.mhp.entities.Item;
import es.mhp.entities.ZipLocation;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Edu on 26/02/2016.
 */
public class AddressDTO extends AbstractDTO{
    private Integer addressId;
    private Set<Item> items;
    private String mainStreet;
    private String secondaryStreet;
    private ZipLocation zipLocation;
    private String city;
    private String state;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public AddressDTO(Address address){
        if (address != null){
            if (address.getAddressId() != null){
                this.addressId = address.getAddressId();
            }
            if (address.getItemsCount() > 0 ){
                this.items = address.getItems();
            }
            if (address.getMainStreet() != null){
                this.mainStreet = address.getMainStreet();
            }
            if (address.getSecondaryStreet() != null){
                this.secondaryStreet = address.getSecondaryStreet();
            }
            if (address.getZipLocation() != null){
                this.zipLocation = address.getZipLocation();
            }
            if (address.getCity() != null){
                this.city = address.getCity();
            }
            if (address.getState() != null){
                this.state = address.getState();
            }
            if (address.getLatitude() != null){
                this.latitude = address.getLatitude();
            }
            if (address.getLongitude() != null){
                this.longitude = address.getLongitude();
            }
        }
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public String getMainStreet() {
        return mainStreet;
    }

    public void setMainStreet(String mainStreet) {
        this.mainStreet = mainStreet;
    }

    public String getSecondaryStreet() {
        return secondaryStreet;
    }

    public void setSecondaryStreet(String secondaryStreet) {
        this.secondaryStreet = secondaryStreet;
    }

    public ZipLocation getZipLocation() {
        return zipLocation;
    }

    public void setZipLocation(ZipLocation zipLocation) {
        this.zipLocation = zipLocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
