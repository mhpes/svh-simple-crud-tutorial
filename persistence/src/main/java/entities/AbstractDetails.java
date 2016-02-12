package entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Edu on 12/02/2016.
 */

@MappedSuperclass
public abstract class AbstractDetails extends AbstractEntity implements Serializable{

    @Column(name = "NAME")
    @Size(max = 30)
    protected String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 500)
    protected String description;

    @Column(name = "IMAGE_URL")
    @Size(max = 55)
    protected String imageUrl;

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
