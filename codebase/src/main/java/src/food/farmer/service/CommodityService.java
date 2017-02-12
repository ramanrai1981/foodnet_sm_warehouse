package src.food.farmer.service;

import src.food.farmer.domain.Commodity;
import src.food.farmer.repository.CommodityRepository;
import src.food.farmer.web.rest.dto.CommodityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import src.food.farmer.web.rest.mapper.CommodityMapper;

/**
 * Service Implementation for managing Commodity.
 */
@Service
public class CommodityService {

    private final Logger log = LoggerFactory.getLogger(CommodityService.class);
    
    @Inject
    private CommodityRepository commodityRepository;
    
    @Inject
    private CommodityMapper commodityMapper;
    
    /**
     * Save a commodity.
     * 
     * @param commodityDTO the entity to save
     * @return the persisted entity
     */
    public CommodityDTO save(CommodityDTO commodityDTO) {
        log.debug("Request to save Commodity : {}", commodityDTO);
        Commodity commodity = commodityMapper.commodityDTOToCommodity(commodityDTO);
        commodity = commodityRepository.save(commodity);
        CommodityDTO result = commodityMapper.commodityToCommodityDTO(commodity);
        return result;
    }

    /**
     *  Get all the commodities.
     *  
     *  @return the list of entities
     */
    public List<CommodityDTO> findAll() {
        log.debug("Request to get all Commodities");
        List<CommodityDTO> result = commodityRepository.findAll().stream()
            .map(commodityMapper::commodityToCommodityDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  Get one commodity by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public CommodityDTO findOne(String id) {
        log.debug("Request to get Commodity : {}", id);
        Commodity commodity = commodityRepository.findOne(UUID.fromString(id));
        CommodityDTO commodityDTO = commodityMapper.commodityToCommodityDTO(commodity);
        return commodityDTO;
    }

    /**
     *  Delete the  commodity by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Commodity : {}", id);
        commodityRepository.delete(UUID.fromString(id));
    }
}
