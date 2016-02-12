package entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "SELLER_CONTACT_INFO")
public class SellerContactInfo extends AbstractEntity{

    @Id
    @SequenceGenerator(name="seller_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seller_sequence")
    @Column(name = "SELLER_ID")
    private Long sellerId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class)
    @JoinColumn(name = "itemId")
    private Item item;

    @Column(name = "LAST_NAME")
    @Size(max = 24)
    private String lastName;

    @Column(name = "FIRST_NAME")
    @Size(max = 24)
    private String firstName;

    @Column(name = "EMAIL")
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="{invalid.email}")
    @Size(max = 24)
    private String email;

    SellerContactInfo(){

    }
}
