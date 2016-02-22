package entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

public class AddressNew implements Serializable{

    private Integer addressId;
    private String mainStreet;
    private String secondaryStreet;
    private String city;
    private String state;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Integer getAddressId() {
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

    public AddressNew(int addressId, String mainStreet, String secondaryStreet, String city, String state, BigDecimal longitude, BigDecimal latitude){
        this.addressId = addressId;
        this.mainStreet = mainStreet;
        this.secondaryStreet = secondaryStreet;
        this.city = city;
        this.state = state;
        this.longitude = longitude;
        this.latitude = latitude;

    }
}