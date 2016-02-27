package es.mhp.services;

import es.mhp.entities.ZipLocation;
import es.mhp.services.dto.ZipLocationDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IZipLocationService {
    Set<ZipLocationDTO> findAllZipLocations();
    Set<ZipLocationDTO> findAllZipLocations(ZipLocation zipLocation);
    Set<ZipLocationDTO> findAnyZipLocation(ZipLocation zipLocation);
    void delete(ZipLocation zipLocation);
    ZipLocationDTO findZipById(long id);
    public ZipLocationDTO update(ZipLocation zipLocation);
}
