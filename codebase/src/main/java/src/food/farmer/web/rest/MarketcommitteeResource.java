package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import src.food.farmer.domain.Marketcommittee;
import src.food.farmer.service.MarketcommitteeService;
import src.food.farmer.web.rest.util.HeaderUtil;
import src.food.farmer.web.rest.dto.MarketcommitteeDTO;
import src.food.farmer.web.rest.mapper.MarketcommitteeMapper;
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
 * REST controller for managing Marketcommittee.
 */
@RestController
@RequestMapping("/api")
public class MarketcommitteeResource {

    private final Logger log = LoggerFactory.getLogger(MarketcommitteeResource.class);
        
    @Inject
    private MarketcommitteeService marketcommitteeService;
    
    @Inject
    private MarketcommitteeMapper marketcommitteeMapper;
    
    /**
     * POST  /marketcommittees : Create a new marketcommittee.
     *
     * @param marketcommitteeDTO the marketcommitteeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new marketcommitteeDTO, or with status 400 (Bad Request) if the marketcommittee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/marketcommittees",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MarketcommitteeDTO> createMarketcommittee(@Valid @RequestBody MarketcommitteeDTO marketcommitteeDTO) throws URISyntaxException {
        log.debug("REST request to save Marketcommittee : {}", marketcommitteeDTO);
        if (marketcommitteeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("marketcommittee", "idexists", "A new marketcommittee cannot already have an ID")).body(null);
        }
        MarketcommitteeDTO result = marketcommitteeService.save(marketcommitteeDTO);
        return ResponseEntity.created(new URI("/api/marketcommittees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("marketcommittee", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /marketcommittees : Updates an existing marketcommittee.
     *
     * @param marketcommitteeDTO the marketcommitteeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated marketcommitteeDTO,
     * or with status 400 (Bad Request) if the marketcommitteeDTO is not valid,
     * or with status 500 (Internal Server Error) if the marketcommitteeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/marketcommittees",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MarketcommitteeDTO> updateMarketcommittee(@Valid @RequestBody MarketcommitteeDTO marketcommitteeDTO) throws URISyntaxException {
        log.debug("REST request to update Marketcommittee : {}", marketcommitteeDTO);
        if (marketcommitteeDTO.getId() == null) {
            return createMarketcommittee(marketcommitteeDTO);
        }
        MarketcommitteeDTO result = marketcommitteeService.save(marketcommitteeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("marketcommittee", marketcommitteeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /marketcommittees : get all the marketcommittees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of marketcommittees in body
     */
    @RequestMapping(value = "/marketcommittees",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<MarketcommitteeDTO> getAllMarketcommittees() {
        log.debug("REST request to get all Marketcommittees");
        return marketcommitteeService.findAll();
    }

    /**
     * GET  /marketcommittees/:id : get the "id" marketcommittee.
     *
     * @param id the id of the marketcommitteeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the marketcommitteeDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/marketcommittees/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MarketcommitteeDTO> getMarketcommittee(@PathVariable String id) {
        log.debug("REST request to get Marketcommittee : {}", id);
        MarketcommitteeDTO marketcommitteeDTO = marketcommitteeService.findOne(id);
        return Optional.ofNullable(marketcommitteeDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /marketcommittees/:id : delete the "id" marketcommittee.
     *
     * @param id the id of the marketcommitteeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/marketcommittees/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMarketcommittee(@PathVariable String id) {
        log.debug("REST request to delete Marketcommittee : {}", id);
        marketcommitteeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("marketcommittee", id.toString())).build();
    }

}
