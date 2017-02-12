/*
 * To change farmerPersonalDetailDTO license header, choose License Headers in Project Properties.
 * To change farmerPersonalDetailDTO template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.food.farmer.web.rest.mapper;

import src.food.farmer.domain.Location;
import src.food.farmer.web.rest.dto.LocationDTO;

/**
 *
 * @author sumit.garg
 */
public class LocationDTOEntityMapper {

    public LocationDTO mapLocationEntityToDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setFarmerid(location.getFarmerid());
        locationDTO.setCity(location.getCity());
        locationDTO.setCountry(location.getCountry());
        locationDTO.setHouseno(location.getHouseno());
        locationDTO.setLandmark(location.getLandmark());
        locationDTO.setPincode(location.getPincode());
        locationDTO.setState(location.getState());

        return locationDTO;
    }

    public Location mapLocationDTOTOEntity(LocationDTO locationDTO) {
        Location location = new Location();

        location.setFarmerid(locationDTO.getFarmerid());
        location.setCity(locationDTO.getCity());
        location.setCountry(locationDTO.getCountry());
        location.setHouseno(locationDTO.getHouseno());
        location.setLandmark(locationDTO.getLandmark());
        location.setPincode(locationDTO.getPincode());
        location.setState(locationDTO.getState());

        return location;
    }
}
