package src.food.farmer.web.rest.mapper;

import src.food.farmer.domain.*;
import src.food.farmer.web.rest.dto.MarketcommitteeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Marketcommittee and its DTO MarketcommitteeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MarketcommitteeMapper {

    MarketcommitteeDTO marketcommitteeToMarketcommitteeDTO(Marketcommittee marketcommittee);

    List<MarketcommitteeDTO> marketcommitteesToMarketcommitteeDTOs(List<Marketcommittee> marketcommittees);

    Marketcommittee marketcommitteeDTOToMarketcommittee(MarketcommitteeDTO marketcommitteeDTO);

    List<Marketcommittee> marketcommitteeDTOsToMarketcommittees(List<MarketcommitteeDTO> marketcommitteeDTOs);
}
