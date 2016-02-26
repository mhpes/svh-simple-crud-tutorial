package es.mhp.services.impl;

import es.mhp.dao.IZiplocationDao;
import es.mhp.entities.ZipLocation;
import es.mhp.services.IZipLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
public class ServiceZipLocationImpl implements IZipLocationService{

    @Autowired
    private IZiplocationDao iZiplocationDao;

    public Set<ZipLocation> findAllZipLocations() {
        return iZiplocationDao.findAll();
    }

    public Set<ZipLocation> findAllZipLocations(ZipLocation zipLocation) {
        return iZiplocationDao.findAll(zipLocation);
    }

    public Set<ZipLocation> findAnyZipLocation(ZipLocation zipLocation) {
        return iZiplocationDao.findAny(zipLocation);
    }

    public void delete(ZipLocation zipLocation) {
        iZiplocationDao.delete(zipLocation);
    }

    public ZipLocation findZipById(long id) {
        return iZiplocationDao.findById(id);
    }

    public ZipLocation update(ZipLocation zipLocation) {
        return iZiplocationDao.update(zipLocation);
    }
}
