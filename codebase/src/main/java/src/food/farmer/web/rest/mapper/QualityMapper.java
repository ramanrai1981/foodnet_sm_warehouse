package src.food.farmer.web.rest.mapper;

import src.food.farmer.domain.*;
import src.food.farmer.web.rest.dto.QualityDTO;

import org.mapstruct.*;
import java.util.List;
import java.util.Map;

/**
 * Mapper for the entity Quality and its DTO QualityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QualityMapper {

    QualityDTO qualityToQualityDTO(Quality quality);

    List<QualityDTO> qualitiesToQualityDTOs(List<Quality> qualities);
    
    //Map<String, Comparable> qualityToQualityDTO2(List<Quality> quality);

    Quality qualityDTOToQuality(QualityDTO qualityDTO);

    List<Quality> qualityDTOsToQualities(List<QualityDTO> qualityDTOs);
}
