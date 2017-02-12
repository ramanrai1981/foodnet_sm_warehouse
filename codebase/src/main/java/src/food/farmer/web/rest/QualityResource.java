package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import src.food.farmer.domain.Quality;
import src.food.farmer.service.QualityService;
import src.food.farmer.web.rest.util.HeaderUtil;
import src.food.farmer.web.rest.dto.QualityDTO;
import src.food.farmer.web.rest.mapper.QualityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Quality.
 */
@RestController
@RequestMapping("/api")
public class QualityResource {

    private final Logger log = LoggerFactory.getLogger(QualityResource.class);
        
    @Inject
    private QualityService qualityService;
    
    @Inject
    private QualityMapper qualityMapper;
    
    /**
     * POST  /commodityQuality : Create a new quality.
     *
     * @param qualityDTO the qualityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new qualityDTO, or with status 400 (Bad Request) if the quality has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commodityQuality",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<QualityDTO> createQuality(@Valid @RequestBody QualityDTO qualityDTO) throws URISyntaxException {
        log.debug("REST request to save Quality : {}", qualityDTO);
        if (qualityDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("quality", "idexists", "A new quality cannot already have an ID")).body(null);
        }
        QualityDTO result = qualityService.save(qualityDTO);
        return ResponseEntity.created(new URI("/api/qualities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("quality", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /commodityQuality : Updates an existing quality.
     *
     * @param qualityDTO the qualityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated qualityDTO,
     * or with status 400 (Bad Request) if the qualityDTO is not valid,
     * or with status 500 (Internal Server Error) if the qualityDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commodityQuality",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<QualityDTO> updateQuality(@Valid @RequestBody QualityDTO qualityDTO) throws URISyntaxException {
        log.debug("REST request to update Quality : {}", qualityDTO);
        if (qualityDTO.getId() == null) {
            return createQuality(qualityDTO);
        }
        QualityDTO result = qualityService.save(qualityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("quality", qualityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /qualities : get all the qualities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of qualities in body
     */
    @RequestMapping(value = "/commodityQuality",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<QualityDTO> getAllQualities() {
        log.debug("REST request to get all Qualities");
        return qualityService.findAll();
    }

    /**
     * GET  /commodityQuality/:id : get the "id" quality.
     *
     * @param id the id of the qualityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the qualityDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/commodityQuality/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<QualityDTO> getQuality(@PathVariable String id) {
        log.debug("REST request to get Quality : {}", id);
        QualityDTO qualityDTO = qualityService.findOne(id);
        return Optional.ofNullable(qualityDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    
    /**
     * GET  /commodityQuality/:commodity_id : get the "id" quality.
     *
     * @param id the id of the qualityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the qualityDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/commodityQuality/getQualityByCommodityId/{commodity_id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<QualityDTO> getQualityByCommodityId(@PathVariable String commodity_id) {
    	log.debug("REST request to get Quality : {}", commodity_id);
        return qualityService.findOneByCommodityId(commodity_id);
        
    }
    

    /**
     * DELETE  /commodityQuality/:id : delete the "id" quality.
     *
     * @param id the id of the qualityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/commodityQuality/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteQuality(@PathVariable String id) {
        log.debug("REST request to delete Quality : {}", id);
        qualityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("quality", id.toString())).build();
    }

}
