package es.mhp.services.impl;

import es.mhp.entities.ZipLocation;
import es.mhp.repositories.ZipLocationRepository;
import es.mhp.services.IZipLocationService;
import es.mhp.services.dto.AbstractDTO;
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
    private ZipLocationRepository zipLocationRepository;

    @Override
    public Set<AbstractDTO> findAll() {
        Iterable<ZipLocation> zipLocations = zipLocationRepository.findAll();

        Set<AbstractDTO> zipLocationDtos = new HashSet<>();

        for (ZipLocation address : zipLocations){
            zipLocationDtos.add(new ZipLocationDTO(address));
        }

        return zipLocationDtos;
    }

    @Override
    public Set<AbstractDTO> findAnyZipLocations(String text) {
        List<ZipLocation> zipLocationList = zipLocationRepository.findByValue(text);

        Set<AbstractDTO> zipLocationDTOs = new HashSet<>();

        for (ZipLocation zipLocation : zipLocationList) {
            zipLocationDTOs.add(new ZipLocationDTO(zipLocation));
        }

        return zipLocationDTOs;
    }

    @Override
    public ZipLocationDTO save(ZipLocationDTO zipLocationDTO) {
        return new ZipLocationDTO(zipLocationRepository.save(zipLocationDTO.toEntity()));
    }

    @Override
    public void delete(Object id) {
        zipLocationRepository.delete((Integer) id);
    }
}