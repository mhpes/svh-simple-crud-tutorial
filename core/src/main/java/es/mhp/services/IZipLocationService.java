package es.mhp.services;

import es.mhp.entities.ZipLocation;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IZipLocationService {
    Set<ZipLocation> findAllZipLocations();
    Set<ZipLocation> findAllZipLocations(ZipLocation zipLocation);
    Set<ZipLocation> findAnyZipLocation(ZipLocation zipLocation);
    void delete(ZipLocation zipLocation);
    ZipLocation findZipById(long id);
    public ZipLocation update(ZipLocation zipLocation);
}
