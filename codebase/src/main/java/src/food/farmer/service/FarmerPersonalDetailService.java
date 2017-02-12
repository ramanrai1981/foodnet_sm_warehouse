package src.food.farmer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import src.food.farmer.domain.FarmerPersonalDetail;
import src.food.farmer.repository.FarmerPersonalDetailRepository;
import src.food.farmer.web.rest.dto.FarmerPersonalDetailDTO;
import src.food.farmer.web.rest.mapper.FarmerPersonalDetailDTOEntityMapper;

/**
 * Service Implementation for managing Farmer's Personal Detail.
 */
@Service
public class FarmerPersonalDetailService {
    
    private final Logger log = LoggerFactory.getLogger(FarmerPersonalDetailService.class);
    
    @Inject
    private FarmerPersonalDetailRepository farmerPersonalDetailRepository;
    
   

    /**
     * Get Personal Detail.
     *
     *
     * @return the entity
     */
    public FarmerPersonalDetailDTO getFarmerPersonalDetail(String id) {
        log.debug("Request to get Msp : {}");
        FarmerPersonalDetail farmerPersonalDetail = farmerPersonalDetailRepository.getFarmerPersonalDetail(id);
        return (new FarmerPersonalDetailDTOEntityMapper()).mapFarmerPersonalDetailEntityToDTO(farmerPersonalDetail);
    }
    
    public boolean update(FarmerPersonalDetailDTO farmerPersonalDetailDTO) {
        
        boolean result = false;
        if (farmerPersonalDetailDTO.getId() != null && !farmerPersonalDetailDTO.getId().trim().equals("")
                && farmerPersonalDetailDTO.getFarmername() != null && !farmerPersonalDetailDTO.getFarmername().trim().equals("")
                && farmerPersonalDetailDTO.getAadhaarno() != null && !farmerPersonalDetailDTO.getAadhaarno().trim().equals("")
                && farmerPersonalDetailDTO.getMobileno() != null && !farmerPersonalDetailDTO.getMobileno().trim().equals("")) {
            result = farmerPersonalDetailRepository.update((new FarmerPersonalDetailDTOEntityMapper()).mapFarmerPersonalDetailDTOTOEntity(farmerPersonalDetailDTO));
        } else {
            result = false;
        }
        return result;
    }
    
}
