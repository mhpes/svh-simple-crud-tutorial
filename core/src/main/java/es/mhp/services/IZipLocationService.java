package es.mhp.services;

import entities.ZipLocation;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IZipLocationService {
    List<ZipLocation> findAllZipLocations();
    List<ZipLocation> findAllZipLocations(ZipLocation zipLocation);
    List<ZipLocation> findAnyZipLocation(ZipLocation zipLocation);
    void delete(ZipLocation zipLocation);
    ZipLocation findZipById(long id);
    public ZipLocation update(ZipLocation zipLocation);
}
