package es.mhp.services.dto;

import es.mhp.entities.Product;
import org.springframework.beans.BeanUtils;

/**
 * Created by Edu on 26/02/2016.
 */
public class ProductDTO extends AbstractDTO<Product> {

    private String productId;
    private String category;
    private String name;
    private String description;
    private String imageUrl;

    @Override
    public Product toEntity(Product product) {
        BeanUtils.copyProperties(this, product);
        return product;
    }

    public ProductDTO(Product product) {
        if (product != null) {
            this.productId = product.getProductId();

            if (product.getCategory() != null){
                this.category = product.getCategory().getCategoryId();
            }

            this.name = product.getName();
            this.description = product.getDescription();
            this.imageUrl = product.getImageUrl();
        }
    }

    public ProductDTO(){}

    public ProductDTO(String productId, String name, String description, String imageUrl, String category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
