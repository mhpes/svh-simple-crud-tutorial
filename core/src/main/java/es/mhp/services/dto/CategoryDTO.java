package es.mhp.services.dto;

import es.mhp.entities.Category;
import org.springframework.beans.BeanUtils;

/**
 * Created by Edu on 26/02/2016.
 */
public class CategoryDTO extends AbstractDTO<Category>{

    private String categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private int associatedProductsCount;

    @Override
    public Category toEntity() {
        Category category = new Category();
        BeanUtils.copyProperties(this, category);
        return category;
    }

    @Override
    public Category toEntity(Category category) {
        BeanUtils.copyProperties(this, category);
        return category;
    }

    public CategoryDTO(Category category) {
        if (category != null) {
            this.categoryId = category.getCategoryId();
            this.name = category.getName();
            this.description = category.getDescription();
            this.imageUrl = category.getImageUrl();
            this.associatedProductsCount = category.getProductsCount();
        }
    }

    public CategoryDTO(String categoryId,String name, String description,String imageUrl) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public CategoryDTO(){}

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

    @Override
    public Object getId(){
        return getCategoryId();
    }
}
