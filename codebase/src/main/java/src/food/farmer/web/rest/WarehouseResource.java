package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import src.food.farmer.domain.Warehouse;
import src.food.farmer.service.WarehouseService;
import src.food.farmer.web.rest.dto.WarehouseDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/api")
public class WarehouseResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseResource.class);

    @Inject
    private WarehouseService warehouseService;

    /**
     *
     *
     * @param id
     * @return the ResponseEntity with status 200 (OK)
     *
     */
    @RequestMapping(value = "/getwarehouse/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public WarehouseDTO get(@PathVariable String id) {
        log.debug("REST request to get Warehouse {}", id);
        WarehouseDTO warehouseDTO = warehouseService.getWarehouseDetail(id);
        if (warehouseDTO.getWarehouselicenseno() == null) {
            warehouseDTO.setWarehouselicenseno(id);
        }
        return warehouseDTO;
    }

    /**
     *
     *
     * @param warehouseDTO
     * @return
     *
     */
    @RequestMapping(value = "/savewarehouse",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public boolean saveWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        boolean response = true;
        log.debug("REST request to save Warehouse {}", warehouseDTO);

        warehouseService.saveWarehouse(warehouseDTO);

        return response;
    }

    /**
     *
     *
     *
     * @return
     *
     */
    @RequestMapping(value = "/getactivewarehouses",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Warehouse> getActiveWarehouses() {
        return warehouseService.getActiveWarehouses();
    }

}
