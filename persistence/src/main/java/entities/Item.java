package entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Edu on 11/02/2016.
 */

@Entity
@Table(name = "ITEM")
public class Item implements Serializable {

    @Id
    @SequenceGenerator(name="item_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_sequence")
    @Column(name = "ITEM_ID")
    private Long itemId;

    //ToDo check that all the relations has the JoinColumns right
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinColumn(name = "tagId")
    private Long tagId;

    //ToDo check that all the relations has the JoinColumns right
    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> products;

    @Column(name = "NAME")
    @Size(max = 30)
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 500)
    private String description;

    @Column(name = "IMAGE_URL")
    @Size(max = 55)
    private String imageUrl;

    @Column(name = "IMAGE_THUMB_ULR")
    @Size(max = 55)
    private String imageThumbUrl;

    @Column(name = "PRICE")
    @Digits(integer = 14, fraction = 2)
    private BigDecimal price;

    //ToDo check that all the relations has the JoinColumns right
    @OneToMany(mappedBy = "addressId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Address> addresses;

    //ToDo check how works ManyToMany relationships
    @ManyToMany(mappedBy = "addressId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SellerContactInfo> sellerContactInfos;

    @Column(name = "TOTAL_SCORE")
    private int totalScore;

    @Column(name = "NUMBER_OF_VOTES")
    private int numberOfVotes;

    //Annotation: default value true, this could be done in the constructor too
    @Column(name = "DISABLED", columnDefinition = "boolean default true")
    private boolean disabled = true;

    Item() {
    }
}
