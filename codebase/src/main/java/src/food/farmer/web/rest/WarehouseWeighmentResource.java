package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.UUID;
import src.food.farmer.domain.util.DateUtility;
import src.food.farmer.service.WarehouseTokenService;
import src.food.farmer.service.WarehouseWeighmentService;
import src.food.farmer.web.rest.dto.WarehouseWeighmentDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/api")
public class WarehouseWeighmentResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseWeighmentResource.class);

    @Inject
    private WarehouseWeighmentService warehouseWeighmentService;
   

    /**
     *
     *
     *
     * @param warehouseWeighmentDTO
     * @return the ResponseEntity with status 200 (OK)
     * @throws java.net.URISyntaxException
     *
     */
    @RequestMapping(value = "/saveweighment",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public boolean save(@RequestBody WarehouseWeighmentDTO warehouseWeighmentDTO) throws URISyntaxException {

        log.debug("#####################REST request to update WarehouseWeighment against LotID : {}", warehouseWeighmentDTO.toString());

        if (warehouseWeighmentDTO.getGrossweight() != null && warehouseWeighmentDTO.getGrossweight() > 0 && warehouseWeighmentDTO.getGrossweightdate() == null) {
            warehouseWeighmentDTO.setGrossweightdate(DateUtility.getCutrrentDateTime());
        }
        if (warehouseWeighmentDTO.getTareweight() != null && warehouseWeighmentDTO.getTareweight() > 0 && warehouseWeighmentDTO.getTareweightdate() == null) {
            warehouseWeighmentDTO.setTareweightdate(DateUtility.getCutrrentDateTime());
        }
        if (warehouseWeighmentDTO.getNetweight() != null && warehouseWeighmentDTO.getNetweight() > 0 && warehouseWeighmentDTO.getNetweightdate() == null) {
            warehouseWeighmentDTO.setNetweightdate(DateUtility.getCutrrentDateTime());
        }
        warehouseWeighmentService.saveWeighment(warehouseWeighmentDTO);

        return true;

    }

    @RequestMapping(value = "/getweighment/{lotid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public WarehouseWeighmentDTO getWeighment(@PathVariable UUID lotid) throws URISyntaxException {

        log.debug("REST request to GET WarehouseWeighment against LOTID : {}", lotid);

        return warehouseWeighmentService.getWeighment(lotid);

    }
}
