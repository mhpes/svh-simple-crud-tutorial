package es.mhp.services.dto;

import es.mhp.entities.Address;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * Created by Edu on 26/02/2016.
 */

public class AddressDTO extends AbstractDTO{

    private long addressId;
    private int associatedItemsCount;
    private String mainStreet;
    private String secondaryStreet;
    private String zipLocationSummary;
    private String city;
    private String state;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public AddressDTO(Address address) {
        if (address != null) {
            this.addressId = address.getAddressId();
            this.mainStreet = address.getMainStreet();
            this.secondaryStreet = address.getSecondaryStreet();
            this.zipLocationSummary = calculateZipLocationSummary(address);
            this.city = address.getCity();
            this.state = address.getState();
            this.latitude = address.getLatitude();
            this.longitude = address.getLongitude();
            this.associatedItemsCount = address.getItemsCount();
        }
    }

    public AddressDTO(){}

    public AddressDTO(long addressId, String mainStreet, String secondaryStreet, String city, String state, BigDecimal latitude, BigDecimal longitude){
        this.addressId = addressId;
        this.mainStreet = mainStreet;
        this.secondaryStreet = secondaryStreet;
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address ToEntity() {
        Address address = new Address();
        address.setAddressId(this.getAddressId());
        address.setMainStreet(this.getMainStreet());
        address.setSecondaryStreet(this.getSecondaryStreet());
        address.setCity(this.getCity());
        address.setState(this.getState());
        address.setLongitude(this.getLongitude());
        address.setLatitude(this.getLongitude());

        return address;
    }

    private String calculateZipLocationSummary(Address address) {
        return (address.getZipLocation() == null) ? "" : address.getZipLocation().getCity() + ". " + address.getZipLocation().getState() + " " + address.getZipLocation().getZipCodeId();
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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

    public int getAssociatedItemsCount() {
        return associatedItemsCount;
    }

    public void setAssociatedItemsCount(int associatedItemsCount) {
        this.associatedItemsCount = associatedItemsCount;
    }

    public String getZipLocationSummary() {
        return zipLocationSummary;
    }

    public void setZipLocationSummary(String zipLocationSummary) {
        this.zipLocationSummary = zipLocationSummary;
    }

    public Address ToEntity(Address address) {
        BeanUtils.copyProperties(this, address);
        return address;
    }
}
