package src.food.farmer.service;

import src.food.farmer.domain.Marketcommittee;
import src.food.farmer.repository.MarketcommitteeRepository;
import src.food.farmer.web.rest.dto.MarketcommitteeDTO;
import src.food.farmer.web.rest.mapper.MarketcommitteeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Marketcommittee.
 */
@Service
public class MarketcommitteeService {

    private final Logger log = LoggerFactory.getLogger(MarketcommitteeService.class);
    
    @Inject
    private MarketcommitteeRepository marketcommitteeRepository;
    
    @Inject
    private MarketcommitteeMapper marketcommitteeMapper;
    
    /**
     * Save a marketcommittee.
     * 
     * @param marketcommitteeDTO the entity to save
     * @return the persisted entity
     */
    public MarketcommitteeDTO save(MarketcommitteeDTO marketcommitteeDTO) {
        log.debug("Request to save Marketcommittee : {}", marketcommitteeDTO);
        Marketcommittee marketcommittee = marketcommitteeMapper.marketcommitteeDTOToMarketcommittee(marketcommitteeDTO);
        marketcommittee = marketcommitteeRepository.save(marketcommittee);
        MarketcommitteeDTO result = marketcommitteeMapper.marketcommitteeToMarketcommitteeDTO(marketcommittee);
        return result;
    }

    /**
     *  Get all the marketcommittees.
     *  
     *  @return the list of entities
     */
    public List<MarketcommitteeDTO> findAll() {
        log.debug("Request to get all Marketcommittees");
        List<MarketcommitteeDTO> result = marketcommitteeRepository.findAll().stream()
            .map(marketcommitteeMapper::marketcommitteeToMarketcommitteeDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  Get one marketcommittee by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public MarketcommitteeDTO findOne(String id) {
        log.debug("Request to get Marketcommittee : {}", id);
        Marketcommittee marketcommittee = marketcommitteeRepository.findOne(UUID.fromString(id));
        MarketcommitteeDTO marketcommitteeDTO = marketcommitteeMapper.marketcommitteeToMarketcommitteeDTO(marketcommittee);
        return marketcommitteeDTO;
    }

    /**
     *  Delete the  marketcommittee by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Marketcommittee : {}", id);
        marketcommitteeRepository.delete(UUID.fromString(id));
    }
}
