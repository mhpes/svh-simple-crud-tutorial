package es.mhp.services;

import es.mhp.services.dto.ZipLocationDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */

public interface IZipLocationService extends AbstractService {
    Set<ZipLocationDTO> findAllZipLocations();
    Set<ZipLocationDTO> findAnyZipLocations(String text);
    void save(ZipLocationDTO zipLocationDTO);
    ZipLocationDTO findZipById(int id);
}
