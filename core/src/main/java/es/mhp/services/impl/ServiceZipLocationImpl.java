package es.mhp.services.impl;

import es.mhp.dao.ZipLocationRepository;
import es.mhp.entities.ZipLocation;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/*
*
 * Created by Edu on 24/02/2016.
*/


@Service
@Transactional
@Configuration
@EnableJpaRepositories
public class ServiceZipLocationImpl implements IZipLocationService {

    @Autowired
    private ZipLocationRepository zipLocationRepository;

    @Override
    public Set<ZipLocationDTO> findAllZipLocations() {
        Iterable<ZipLocation> zipLocationSet = zipLocationRepository.findAll();

        Set<ZipLocationDTO> zipLocationDTOs = new HashSet<>();

        for (ZipLocation zipLocation : zipLocationSet){
            zipLocationDTOs.add(new ZipLocationDTO(zipLocation));
        }

        return zipLocationDTOs;
    }

    @Override
    public Set<ZipLocationDTO> findAllZipLocations(ZipLocationDTO zipLocationDTO) {
        return null;
    }

    @Override
    public Set<ZipLocationDTO> findAnyZipLocations(ZipLocationDTO zipLocationDTO) {
        return null;
    }

    @Override
    public Set<ZipLocationDTO> findAnyZipLocations(String text) {
        return null;
    }

    @Override
    public ZipLocationDTO save(ZipLocationDTO zipLocationDTO) {
        return null;
    }

    @Override
    public void delete(ZipLocationDTO zipLocationDTO) {
        //zipLocationRepository.deleteById(zipLocationDTO.getZipCodeId());
    }

    @Override
    public ZipLocationDTO findZipById(int id) {
        return null;
        //return new ZipLocationDTO(zipLocationRepository.findById(id));
    }
}