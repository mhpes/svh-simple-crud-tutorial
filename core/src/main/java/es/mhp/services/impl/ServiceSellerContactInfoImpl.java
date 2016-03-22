package es.mhp.services.impl;

import es.mhp.entities.SellerContactInfo;
import es.mhp.repositories.SellerRepository;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/*
 * Created by Edu on 24/02/2016.
*/


@Service
@Transactional
@Configuration
@EnableJpaRepositories
public class ServiceSellerContactInfoImpl implements ISellerContactInfoService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Set<AbstractDTO> findAllSellers() {
        Iterable<SellerContactInfo> tagSet = sellerRepository.findAll();

        Set<AbstractDTO> tagDTOs = new HashSet<>();

        for (SellerContactInfo sellerContactInfo : tagSet){
            tagDTOs.add(new SellerContactInfoDTO(sellerContactInfo));
        }

        return tagDTOs;
    }

    @Override
    public Set<AbstractDTO> findAnySellers(String text) {
        Iterable<SellerContactInfo> sellerSet = sellerRepository.findByValue(text);

        Set<AbstractDTO> sellerDTOs = new HashSet<>();

        for (SellerContactInfo currentCategory : sellerSet) {
            sellerDTOs.add(new SellerContactInfoDTO(currentCategory));
        }

        return sellerDTOs;
    }

    @Override
    public SellerContactInfoDTO save(SellerContactInfoDTO sellerContactInfoDTO) {
        return new SellerContactInfoDTO(sellerRepository.save(sellerContactInfoDTO.toEntity()));
    }

    @Override
    public Set<AbstractDTO> findAll() {
        Iterable<SellerContactInfo> sellerSet = sellerRepository.findAll();

        Set<AbstractDTO> sellerDTOs = new HashSet<>();

        for (SellerContactInfo currentCategory : sellerSet) {
            sellerDTOs.add(new SellerContactInfoDTO(currentCategory));
        }

        return sellerDTOs;
    }

    @Override
    public void delete(Object id) {
        sellerRepository.delete((Integer) id);
    }
}
