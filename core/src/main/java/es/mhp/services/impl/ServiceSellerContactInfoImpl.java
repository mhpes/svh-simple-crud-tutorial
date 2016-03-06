package es.mhp.services.impl;

import es.mhp.dao.ISellerDao;
import es.mhp.entities.SellerContactInfo;
import es.mhp.services.ISellerContactInfoService;
import es.mhp.services.dto.SellerContactInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 * Created by Edu on 24/02/2016.
*/


@Service
@Transactional
public class ServiceSellerContactInfoImpl implements ISellerContactInfoService {

    @Autowired
    private ISellerDao iSellerDao;

    @Override
    public Set<SellerContactInfoDTO> findAllSellers() {
        Set<SellerContactInfo> tagSet = iSellerDao.findAll();

        Set<SellerContactInfoDTO> tagDTOs = new HashSet<>();

        for (SellerContactInfo sellerContactInfo : tagSet){
            tagDTOs.add(new SellerContactInfoDTO(sellerContactInfo));
        }

        return tagDTOs;
    }

    @Override
    public Set<SellerContactInfoDTO> findAllSellers(SellerContactInfo tag) {
        Set<SellerContactInfo> sellerContactInfoSet = iSellerDao.findAll(tag);

        Set<SellerContactInfoDTO> tagDTOs = new HashSet<>();

        for (SellerContactInfo currentSellerContactInfo : sellerContactInfoSet) {
            tagDTOs.add(new SellerContactInfoDTO(currentSellerContactInfo));
        }

        return tagDTOs;
    }

    @Override
    public Set<SellerContactInfoDTO> findAnySellers(SellerContactInfo sellerContactInfo) {
        Set<SellerContactInfo> sellerContactInfoSet = iSellerDao.findAny(sellerContactInfo);

        if (!sellerContactInfoSet.isEmpty()){
            Set<SellerContactInfoDTO> sellerContactInfoDTOs = new HashSet<>();

            for (SellerContactInfo currentSellerContactInfo : sellerContactInfoSet) {
                sellerContactInfoDTOs.add(new SellerContactInfoDTO(currentSellerContactInfo));
            }

            return sellerContactInfoDTOs;
        }
        return Collections.emptySet();
    }

    @Override
    public SellerContactInfoDTO save(SellerContactInfoDTO tagDTO) {
        SellerContactInfo sellerContactInfo = iSellerDao.findById(tagDTO.getContactInfoId());

        if (sellerContactInfo != null){
            iSellerDao.update(tagDTO.toEntity(sellerContactInfo));
        } else {
            sellerContactInfo = new SellerContactInfo();
            iSellerDao.save(sellerContactInfo);
        }
        return new SellerContactInfoDTO(sellerContactInfo);
    }

    @Override
    public void delete(SellerContactInfoDTO sellerContactInfoDTO) {
        iSellerDao.deleteById(sellerContactInfoDTO.getContactInfoId());
    }

    @Override
    public SellerContactInfoDTO findSellerById(int id) {
        return new SellerContactInfoDTO(iSellerDao.findById(id));
    }
}
