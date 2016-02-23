package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "ZIPLOCATION")
public class ZipLocation extends AbstractEntity {

    @Id
    @SequenceGenerator(name="ziplocation_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ziplocation_sequence")
    @Column(name = "ZIPCODE_ID")
    private Integer zipCodeId;

    @OneToMany(mappedBy = "zipLocation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;

    @Column(name = "ZIPCODE", unique = true)
    private Integer zipCode;

    @Column(name = "CITY")
    @Size(max = 30)
    private String city;

    @Column(name = "STATE")
    @Size(max = 2)
    private String state;

    ZipLocation() {}

    public Integer getZipCodeId() {
        return zipCodeId;
    }

    public void setZipCodeId(Integer zipCodeId) {
        this.zipCodeId = zipCodeId;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
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

    public ZipLocation (Integer zipCodeId, Integer zipCode, String city, String state){
        this.zipCodeId = zipCodeId;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }
}
