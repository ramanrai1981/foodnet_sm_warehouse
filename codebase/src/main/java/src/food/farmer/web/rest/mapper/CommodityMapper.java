package src.food.farmer.web.rest.mapper;

import src.food.farmer.domain.*;
import src.food.farmer.web.rest.dto.CommodityDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Commodity and its DTO CommodityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommodityMapper {

    CommodityDTO commodityToCommodityDTO(Commodity commodity);

    List<CommodityDTO> commoditiesToCommodityDTOs(List<Commodity> commodities);

    Commodity commodityDTOToCommodity(CommodityDTO commodityDTO);

    List<Commodity> commodityDTOsToCommodities(List<CommodityDTO> commodityDTOs);
}
