package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.UUID;
import src.food.farmer.domain.util.DateUtility;
import src.food.farmer.service.WarehouseCommodityRecievedService;
import src.food.farmer.web.rest.dto.WarehouseCommodityRecievedDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/api")
public class WarehouseCommodityRecievedResource {
    
    private final Logger log = LoggerFactory.getLogger(WarehouseCommodityRecievedResource.class);
    
    @Inject
    private WarehouseCommodityRecievedService warehouseCommodityRecievedService;

    /**
     *
     *
     * @param id
     * @return the ResponseEntity with status 200 (OK)
     *
     */
    @RequestMapping(value = "/getwarehouselot/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public WarehouseCommodityRecievedDTO get(@PathVariable UUID id) {
        log.debug("REST request to get WarehouseCommodityRecievedDTO LOT ID{}", id);
        return warehouseCommodityRecievedService.getByLotID(id);
    }
    
    @RequestMapping(value = "/savewarehousecommodity",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public UUID save(@RequestBody WarehouseCommodityRecievedDTO warehouseCommodityRecievedDTO) throws URISyntaxException, ParseException {
        warehouseCommodityRecievedDTO.setLotid(UUID.randomUUID());
        warehouseCommodityRecievedDTO.setOndate(DateUtility.getCutrrentDateTime());
        log.debug("------------------------------REST request to get WarehouseCommodityRecievedDTO {}", warehouseCommodityRecievedDTO.getCommoditycode());
        warehouseCommodityRecievedService.save(warehouseCommodityRecievedDTO);
        return warehouseCommodityRecievedDTO.getLotid();
        
    }
    
    @RequestMapping(value = "/updatelotstatus",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void updateLotStatus(@RequestBody WarehouseCommodityRecievedDTO warehouseCommodityRecievedDTO) throws URISyntaxException {
        warehouseCommodityRecievedService.updateLotStatus(warehouseCommodityRecievedDTO);
        
    }
    
}
