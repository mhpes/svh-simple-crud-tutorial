package es.mhp.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractEntity {

    @Id
    @SequenceGenerator(name="product_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_sequence")
    @Size(max = 10)
    @Column(name = "PRODUCTID")
    private String productId;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column(name = "NAME")
    @Size(max = 25)
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 255)
    private String description;

    @Column(name = "IMAGEURL")
    @Size(max = 55)
    private String imageUrl;

    public Product(){}

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
