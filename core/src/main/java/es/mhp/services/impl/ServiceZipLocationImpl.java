package es.mhp.services.impl;

import es.mhp.dao.IZiplocationDao;
import es.mhp.entities.ZipLocation;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
*
 * Created by Edu on 24/02/2016.
*/


@Service
@Transactional
public class ServiceZipLocationImpl implements IZipLocationService {

    @Autowired
    private IZiplocationDao iZiplocationDao;

    @Override
    public Set<ZipLocationDTO> findAllZipLocations() {
        Set<ZipLocation> zipLocationSet = iZiplocationDao.findAll();

        Set<ZipLocationDTO> zipLocationDTOs = new HashSet<>();

        for (ZipLocation zipLocation : zipLocationSet){
            zipLocationDTOs.add(new ZipLocationDTO(zipLocation));
        }

        return zipLocationDTOs;
    }

    @Override
    public Set<ZipLocationDTO> findAllZipLocations(ZipLocation zipLocation) {
        Set<ZipLocation> zipLocationSet = iZiplocationDao.findAll(zipLocation);

        Set<ZipLocationDTO> addressDTOs = new HashSet<>();

        for (ZipLocation currentZip : zipLocationSet) {
            addressDTOs.add(new ZipLocationDTO(currentZip));
        }

        return addressDTOs;
    }

    @Override
    public Set<ZipLocationDTO> findAnyZipLocations(ZipLocation zipLocation) {
        Set<ZipLocation> zipLocationSet = iZiplocationDao.findAny(zipLocation);

        if (!zipLocationSet.isEmpty()){
            Set<ZipLocationDTO> addressDTOs = new HashSet<>();

            for (ZipLocation currentZip : zipLocationSet) {
                addressDTOs.add(new ZipLocationDTO(currentZip));
            }

            return addressDTOs;
        }
        return Collections.emptySet();
    }

    @Override
    public ZipLocationDTO save(ZipLocationDTO zipLocationDTO) {
        ZipLocation zipLocation = iZiplocationDao.findById(zipLocationDTO.getZipCodeId());

        if (zipLocation != null){
            iZiplocationDao.update(zipLocationDTO.toEntity(zipLocation));
        } else {
            zipLocation = new ZipLocation();
            iZiplocationDao.save(zipLocationDTO.toEntity(zipLocation));
        }
        return zipLocationDTO;
    }

    @Override
    public void delete(int id) {
        iZiplocationDao.deleteById(id);
    }

    @Override
    public ZipLocationDTO findZipById(int id) {
        return new ZipLocationDTO(iZiplocationDao.findById(id));
    }
}