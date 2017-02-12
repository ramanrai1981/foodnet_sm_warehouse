package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import src.food.farmer.domain.util.DateUtility;
import src.food.farmer.service.WarehouseReceiptService;
import src.food.farmer.web.rest.dto.WarehouseReceiptDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/api")
public class WarehouseReceiptResource {
    
    private final Logger log = LoggerFactory.getLogger(WarehouseReceiptResource.class);
    
    @Inject
    private WarehouseReceiptService warehouseReceiptService;

    /**
     *
     *
     * @param warehouseReceiptDTO
     * @return the ResponseEntity with status 200 (OK)
     *
     */
    @RequestMapping(value = "/savewarehousereceipt",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public boolean save(@RequestBody WarehouseReceiptDTO warehouseReceiptDTO) {
        log.debug("REST request to get Quality Parameters {}", warehouseReceiptDTO);
        warehouseReceiptDTO.setOndate(DateUtility.getCutrrentDateTime());
        warehouseReceiptService.save(warehouseReceiptDTO);
        return true;
    }
    
}
