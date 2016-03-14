package es.mhp.services.impl;

import es.mhp.repositories.ZipLocationRepository;
import es.mhp.entities.ZipLocation;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.ZipLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
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
    private ZipLocationRepository ZipLocationRepository;

    @Override
    public Set<ZipLocationDTO> findAllZipLocations() {
        Iterable<ZipLocation> zipLocationSet = ZipLocationRepository.findAll();

        Set<ZipLocationDTO> zipLocationDTOs = new HashSet<>();

        for (ZipLocation zipLocation : zipLocationSet) {
            zipLocationDTOs.add(new ZipLocationDTO(zipLocation));
        }

        return zipLocationDTOs;
    }

    @Override
    public Set<ZipLocationDTO> findAnyZipLocations(String text) {
        List<ZipLocation> zipLocationList = ZipLocationRepository.findByValue(text);

        Set<ZipLocationDTO> zipLocationDTOs = new HashSet<>();

        for (ZipLocation zipLocation : zipLocationList) {
            zipLocationDTOs.add(new ZipLocationDTO(zipLocation));
        }

        return zipLocationDTOs;
    }

    @Override
    public void save(ZipLocationDTO zipLocationDTO) {
        ZipLocationRepository.save(zipLocationDTO.toEntity());
    }

    @Override
    public void delete(ZipLocationDTO zipLocationDTO) {
        ZipLocationRepository.delete(zipLocationDTO.getZipCodeId());
    }

    @Override
    public ZipLocationDTO findZipById(int id) {
        return new ZipLocationDTO(ZipLocationRepository.findOne(id));
    }
}