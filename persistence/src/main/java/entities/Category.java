package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "CATEGORY")
public class Category extends AbstractEntity{

    @Id
    @SequenceGenerator(name="category_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_sequence")
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @Column(name = "NAME")
    @Size(max = 25)
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 255)
    private String description;

    @Column(name = "IMAGE_URL")
    @Size(max = 55)
    private String imageUrl;

    Category(){}

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}