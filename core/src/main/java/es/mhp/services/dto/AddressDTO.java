package es.mhp.services.dto;

import es.mhp.entities.Address;
import es.mhp.entities.ZipLocation;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;

/**
 * Created by Edu on 26/02/2016.
 */

public class AddressDTO extends AbstractDTO<Address>{

    private int addressId;
    private int associatedItemsCount;
    private String mainStreet;
    private String secondaryStreet;
    private ZipLocationDTO zip;
    private String city;
    private String state;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @Override
    public Address toEntity() {
        Address address = new Address();
        ZipLocation zipLocation = this.getZip().toEntity();
        BeanUtils.copyProperties(this, address);
        address.setZipLocation(zipLocation);
        return address;
    }

    @Override
    public Address toEntity(Address address) {
        BeanUtils.copyProperties(this, address);
        return address;
    }

    public AddressDTO(Address address) {
        if (address != null) {
            this.addressId = address.getAddressId();
            this.mainStreet = address.getMainStreet();
            this.secondaryStreet = address.getSecondaryStreet();

            if (address.getZipLocation() != null){
                this.zip = new ZipLocationDTO(address.getZipLocation());
            }

            this.city = address.getCity();
            this.state = address.getState();
            this.latitude = address.getLatitude();
            this.longitude = address.getLongitude();
            this.associatedItemsCount = address.getItemsCount();
        }
    }

    public AddressDTO(int addressId, String mainStreet, String secondaryStreet, ZipLocationDTO zip, String city, String state, BigDecimal latitude, BigDecimal longitude){
        this.addressId = addressId;
        this.mainStreet = mainStreet;
        this.secondaryStreet = secondaryStreet;
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AddressDTO(String mainStreet, String secondaryStreet, String city, String state) {
        this.mainStreet = mainStreet;
        this.secondaryStreet = secondaryStreet;
        this.city = city;
        this.state = state;
    }

    public AddressDTO(String mainStreet, String secondaryStreet, String city) {
        this.mainStreet = mainStreet;
        this.secondaryStreet = secondaryStreet;
        this.city = city;
    }

    public AddressDTO() {

    }

    public int getAddressId() {
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

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public ZipLocationDTO getZip() {
        return zip;
    }

    public void setZip(ZipLocationDTO zip) {
        this.zip = zip;
    }
}
