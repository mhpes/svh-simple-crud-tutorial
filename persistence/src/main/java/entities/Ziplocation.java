package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "ITEM")
public class Ziplocation extends AbstractEntity {

    @Id
    @SequenceGenerator(name="ziplocation_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ziplocation_sequence")
    @Column(name = "ZIPCODE_ID")
    private Long zipCodeId;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;

    @Column(name = "ZIPCODE", unique = true)
    private Long zipCode;

    @Column(name = "CITY")
    @Size(max = 30)
    private String city;

    @Column(name = "STATE")
    @Size(max = 2)
    private String state;

    public Long getZipCodeId() {
        return zipCodeId;
    }

    public void setZipCodeId(Long zipCodeId) {
        this.zipCodeId = zipCodeId;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
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

    Ziplocation() {}
}
