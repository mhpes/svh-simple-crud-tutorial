package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "ITEM")
public class Ziplocation implements Serializable {

    @Id
    @SequenceGenerator(name="ziplocation_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ziplocation_sequence")
    @Column(name = "ZIPCODE_ID")
    private Long zipCodeId;

    @Column(name = "CITY")
    @Size(max = 30)
    private String city;

    @Column(name = "STATE")
    @Size(max = 2)
    private String state;

    Ziplocation() {
    }
}
