package entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * Created by Edu on 12/02/2016.
 */

@MappedSuperclass
public abstract class AbstractEntity {

    @Column(name = "NAME")
    @Size(max = 30)
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 500)
    private String description;

    @Column(name = "IMAGE_URL")
    @Size(max = 55)
    private String imageUrl;

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
