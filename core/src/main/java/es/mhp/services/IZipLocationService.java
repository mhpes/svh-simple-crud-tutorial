package es.mhp.services;

import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ZipLocationDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */

public interface IZipLocationService extends AbstractService {
    ZipLocationDTO save(ZipLocationDTO zipLocationDTO);
    Set<AbstractDTO> findAnyZipLocations(String text);
}
