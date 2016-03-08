package es.mhp.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "ZIPLOCATION")
public class ZipLocation extends AbstractEntity {

    @Id
    @Column(name = "ZIPCODE")
    private int zipCodeId;

    @Column(name = "CITY")
    @Size(max = 30)
    private String city;

    @Column(name = "STATE")
    @Size(max = 2)
    private String state;

    @OneToMany(mappedBy = "zipLocation", fetch = FetchType.LAZY)
    private Set<Address> addresses;

    public ZipLocation() {
    }

    public ZipLocation(int zipCode, String city, String state) {
        this.zipCodeId = zipCode;
        this.city = city;
        this.state = state;
    }

    public int getZipCodeId() {
        return zipCodeId;
    }

    public void setZipCodeId(int zipCodeId) {
        this.zipCodeId = zipCodeId;
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

    public String toString(){
        return getZipCodeId() + " " + getState() + " " + getCity();
    }
}
