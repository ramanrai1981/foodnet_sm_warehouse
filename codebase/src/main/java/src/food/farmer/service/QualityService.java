package src.food.farmer.service;

import src.food.farmer.domain.Quality;
import src.food.farmer.repository.QualityRepository;
import src.food.farmer.web.rest.dto.QualityDTO;
import src.food.farmer.web.rest.mapper.QualityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Quality.
 */
@Service
public class QualityService {

    private final Logger log = LoggerFactory.getLogger(QualityService.class);
    
    @Inject
    private QualityRepository qualityRepository;
    
    @Inject
    private QualityMapper qualityMapper;
    
    /**
     * Save a quality.
     * 
     * @param qualityDTO the entity to save
     * @return the persisted entity
     */
    public QualityDTO save(QualityDTO qualityDTO) {
        log.debug("Request to save Quality : {}", qualityDTO);
        Quality quality = qualityMapper.qualityDTOToQuality(qualityDTO);
        quality = qualityRepository.save(quality);
        QualityDTO result = qualityMapper.qualityToQualityDTO(quality);
        return result;
    }

    /**
     *  Get all the qualities.
     *  
     *  @return the list of entities
     */
    public List<QualityDTO> findAll() {
        log.debug("Request to get all Qualities");
        List<QualityDTO> result = qualityRepository.findAll().stream()
            .map(qualityMapper::qualityToQualityDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  Get one quality by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public QualityDTO findOne(String id) {
        log.debug("Request to get Quality : {}", id);
        Quality quality = qualityRepository.findOne(UUID.fromString(id));
        QualityDTO qualityDTO = qualityMapper.qualityToQualityDTO(quality);
        return qualityDTO;
    }
    
    /**
     *  Get one quality by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public List<QualityDTO> findOneByCommodityId(String commodity_id) { 
        log.debug("Request to get Quality : {}", commodity_id);
       
        List<QualityDTO> result = qualityRepository.findQuality(commodity_id).stream()
                .map(qualityMapper::qualityToQualityDTO)
                .collect(Collectors.toCollection(LinkedList::new));
       return result; 
    }

    /**
     *  Delete the  quality by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Quality : {}", id);
        qualityRepository.delete(UUID.fromString(id));
    }
}
