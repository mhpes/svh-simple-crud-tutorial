package entities;

import javax.persistence.*;

/**
 * Created by Edu on 12/02/2016.
 */

@Entity
@Table(name = "CATEGORY")
public class Category extends AbstractDetails{

    @Id
    @SequenceGenerator(name="category_sequence", initialValue=1, allocationSize=9999999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_sequence")
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "productId")
    private Product product;

    Category(){}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}