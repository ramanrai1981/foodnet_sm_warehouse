package src.food.farmer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import src.food.farmer.domain.Location;
import src.food.farmer.repository.FarmerLocationDetailRepository;
import src.food.farmer.web.rest.dto.LocationDTO;
import src.food.farmer.web.rest.mapper.LocationDTOEntityMapper;

/**
 * Service Implementation for managing Farmer's Personal Detail.
 */
@Service
public class FarmerLocationlDetailService {

    private final Logger log = LoggerFactory.getLogger(FarmerLocationlDetailService.class);

    @Inject
    private FarmerLocationDetailRepository farmerLocationDetailRepository;

    /**
     * Get Personal Detail.
     *
     *
     * @param id
     * @return the entity
     */
    public LocationDTO getFarmerLocationDetail(String id) {
        log.debug("Request to get Get Location  : {}",id);
        Location location = farmerLocationDetailRepository.getFarmerLocationDetail(id);
        return (new LocationDTOEntityMapper()).mapLocationEntityToDTO(location);
    }

    public boolean update(LocationDTO locationDTO) {
   log.debug("#######################################Request to get Save  Location  : {}", locationDTO.getCity());
        boolean result = false;
        if (locationDTO.getFarmerid() != null && !locationDTO.getFarmerid().trim().equals("")
                && locationDTO.getCity() != null && !locationDTO.getCity().trim().equals("")
                && locationDTO.getCountry() != null && !locationDTO.getCountry().trim().equals("")
                && locationDTO.getHouseno() != null && !locationDTO.getHouseno().trim().equals("")
                && locationDTO.getLandmark() != null && !locationDTO.getLandmark().trim().equals("")
                && locationDTO.getState() != null && !locationDTO.getState().trim().equals("")
                && locationDTO.getPincode() != null && !locationDTO.getPincode().trim().equals("")) {
            result = farmerLocationDetailRepository.update((new LocationDTOEntityMapper()).mapLocationDTOTOEntity(locationDTO));
        } else {
            result = false;
        }
        return result;
    }

}
