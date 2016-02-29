package es.mhp.services.dto;

import es.mhp.entities.ZipLocation;
import org.springframework.beans.BeanUtils;

/**
 * Created by Edu on 26/02/2016.
 */
public class ZipLocationDTO extends AbstractDTO{

    private long zipCode;
    private String city;
    private String state;

    public ZipLocationDTO(Integer zipCode, String city, String state) {
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public ZipLocationDTO(ZipLocationDTO zipLocationDTO) {
        if (zipLocationDTO != null) {
            this.zipCode = zipLocationDTO.getZipCode();
            this.city = zipLocationDTO.getCity();
            this.state = zipLocationDTO.getState();
        }
    }

    public ZipLocationDTO(ZipLocation zipLocation) {
        this.zipCode = zipLocation.getZipCodeId();
        this.city = zipLocation.getCity();
        this.state = zipLocation.getState();
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ZipLocation ToEntity(ZipLocation zipLocation) {
        BeanUtils.copyProperties(this, zipLocation);
        return zipLocation;
    }
}
