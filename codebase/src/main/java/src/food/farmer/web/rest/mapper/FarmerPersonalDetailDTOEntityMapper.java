/*
 * To change farmerPersonalDetailDTO license header, choose License Headers in Project Properties.
 * To change farmerPersonalDetailDTO template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.food.farmer.web.rest.mapper;

import javax.inject.Inject;
import src.food.farmer.domain.FarmerPersonalDetail;
import src.food.farmer.web.rest.dto.FarmerPersonalDetailDTO;

/**
 *
 * @author sumit.garg
 */
public class FarmerPersonalDetailDTOEntityMapper {

    public FarmerPersonalDetailDTO mapFarmerPersonalDetailEntityToDTO(FarmerPersonalDetail farmerPersonalDetail) {
        FarmerPersonalDetailDTO farmerPersonalDetailDTO = new FarmerPersonalDetailDTO();
        farmerPersonalDetailDTO.setAadhaarno(farmerPersonalDetail.getAadhaarno());
        farmerPersonalDetailDTO.setAccountno(farmerPersonalDetail.getAccountno());
        farmerPersonalDetailDTO.setBankname(farmerPersonalDetail.getBankname());
        farmerPersonalDetailDTO.setBranchname(farmerPersonalDetail.getBranchname());
        farmerPersonalDetailDTO.setCategory(farmerPersonalDetail.getCategory());
        farmerPersonalDetailDTO.setFarmername(farmerPersonalDetail.getFarmername());
        farmerPersonalDetailDTO.setFarmingland(farmerPersonalDetail.getFarmingland());
        farmerPersonalDetailDTO.setFathername(farmerPersonalDetail.getFathername());
        farmerPersonalDetailDTO.setGender(farmerPersonalDetail.getGender());
        farmerPersonalDetailDTO.setId(farmerPersonalDetail.getId());
        farmerPersonalDetailDTO.setIfsccode(farmerPersonalDetail.getIfsccode());
        farmerPersonalDetailDTO.setKullrakhba(farmerPersonalDetail.getKullrakhba());
        farmerPersonalDetailDTO.setMobileno(farmerPersonalDetail.getMobileno());
        farmerPersonalDetailDTO.setMothername(farmerPersonalDetail.getMothername());
        farmerPersonalDetailDTO.setVehicleno(farmerPersonalDetail.getVehicleno());
        farmerPersonalDetailDTO.setVoterid(farmerPersonalDetail.getVoterid());
        return farmerPersonalDetailDTO;
    }

    public FarmerPersonalDetail mapFarmerPersonalDetailDTOTOEntity(FarmerPersonalDetailDTO farmerPersonalDetailDTO) {
        FarmerPersonalDetail farmerPersonalDetail = new FarmerPersonalDetail();
        farmerPersonalDetail.setAadhaarno(farmerPersonalDetailDTO.getAadhaarno());
        farmerPersonalDetail.setAccountno(farmerPersonalDetailDTO.getAccountno());
        farmerPersonalDetail.setBankname(farmerPersonalDetailDTO.getBankname());
        farmerPersonalDetail.setBranchname(farmerPersonalDetailDTO.getBranchname());
        farmerPersonalDetail.setCategory(farmerPersonalDetailDTO.getCategory());
        farmerPersonalDetail.setFarmername(farmerPersonalDetailDTO.getFarmername());
        farmerPersonalDetail.setFarmingland(farmerPersonalDetailDTO.getFarmingland());
        farmerPersonalDetail.setFathername(farmerPersonalDetailDTO.getFathername());
        farmerPersonalDetail.setGender(farmerPersonalDetailDTO.getGender());
        farmerPersonalDetail.setId(farmerPersonalDetailDTO.getId());
        farmerPersonalDetail.setIfsccode(farmerPersonalDetailDTO.getIfsccode());
        farmerPersonalDetail.setKullrakhba(farmerPersonalDetailDTO.getKullrakhba());
        farmerPersonalDetail.setMobileno(farmerPersonalDetailDTO.getMobileno());
        farmerPersonalDetail.setMothername(farmerPersonalDetailDTO.getMothername());
        farmerPersonalDetail.setVehicleno(farmerPersonalDetailDTO.getVehicleno());
        farmerPersonalDetail.setVoterid(farmerPersonalDetailDTO.getVoterid());

        return farmerPersonalDetail;
    }
}
