package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import src.food.farmer.domain.Commodity;
import src.food.farmer.service.CommodityService;
import src.food.farmer.web.rest.util.HeaderUtil;
import src.food.farmer.web.rest.dto.CommodityDTO;
import src.food.farmer.web.rest.mapper.CommodityMapper;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Commodity.
 */
@RestController
@RequestMapping("/api")
public class CommodityResource {

    private final Logger log = LoggerFactory.getLogger(CommodityResource.class);
        
    @Inject
    private CommodityService commodityService;
    
    @Inject
    private CommodityMapper commodityMapper;
    
    /**
     * POST  /commodities : Create a new commodity.
     *
     * @param commodityDTO the commodityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new commodityDTO, or with status 400 (Bad Request) if the commodity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commodities",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CommodityDTO> createCommodity(@Valid @RequestBody CommodityDTO commodityDTO) throws URISyntaxException {
        log.debug("REST request to save Commodity : {}", commodityDTO);
        if (commodityDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("commodity", "idexists", "A new commodity cannot already have an ID")).body(null);
        }
        CommodityDTO result = commodityService.save(commodityDTO);
        return ResponseEntity.created(new URI("/api/commodities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("commodity", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /commodities : Updates an existing commodity.
     *
     * @param commodityDTO the commodityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated commodityDTO,
     * or with status 400 (Bad Request) if the commodityDTO is not valid,
     * or with status 500 (Internal Server Error) if the commodityDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commodities",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CommodityDTO> updateCommodity(@Valid @RequestBody CommodityDTO commodityDTO) throws URISyntaxException {
        log.debug("REST request to update Commodity : {}", commodityDTO);
        if (commodityDTO.getId() == null) {
            return createCommodity(commodityDTO);
        }
        CommodityDTO result = commodityService.save(commodityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("commodity", commodityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /commodities : get all the commodities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of commodities in body
     */
    @RequestMapping(value = "/commodities",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CommodityDTO> getAllCommodities() {
        log.debug("REST request to get all Commodities");
        return commodityService.findAll();
    }

    /**
     * GET  /commodities/:id : get the "id" commodity.
     *
     * @param id the id of the commodityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the commodityDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/commodities/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CommodityDTO> getCommodity(@PathVariable String id) {
        log.debug("REST request to get Commodity : {}", id);
        CommodityDTO commodityDTO = commodityService.findOne(id);
        return Optional.ofNullable(commodityDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /commodities/:id : delete the "id" commodity.
     *
     * @param id the id of the commodityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/commodities/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCommodity(@PathVariable String id) {
        log.debug("REST request to delete Commodity : {}", id);
        commodityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("commodity", id.toString())).build();
    }

}
