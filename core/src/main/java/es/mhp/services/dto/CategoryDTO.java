package es.mhp.services.dto;

import es.mhp.entities.Category;

/**
 * Created by Edu on 26/02/2016.
 */
public class CategoryDTO extends AbstractDTO{

    private String categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private int associatedProductsCount;

    public CategoryDTO(Category category) {
        if (category != null) {
            this.categoryId = category.getCategoryId();
            this.name = category.getName();
            this.description = category.getDescription();
            this.imageUrl = category.getImageUrl();
            this.associatedProductsCount = category.getProductsCount();
        }
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public int getAssociatedProductsCount() {
        return associatedProductsCount;
    }

    public void setAssociatedProductsCount(int associatedProductsCount) {
        this.associatedProductsCount = associatedProductsCount;
    }
}
