package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import src.food.farmer.domain.WarehouseToken;
import src.food.farmer.service.WarehouseTokenService;
import src.food.farmer.web.rest.dto.WarehouseTokenDTO;

/**
 * REST controller for managing WarehouseTokenResource.
 */
@RestController
@RequestMapping("/api")
public class WarehouseTokenResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseTokenResource.class);
    @Inject
    private WarehouseTokenService warehouseTokenService;

    /**
     *
     * @param warehouseTokenDTO
     * @return
     */
    @RequestMapping(value = "/gettoken",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public WarehouseTokenDTO getToken(@RequestBody WarehouseTokenDTO warehouseTokenDTO) {
        log.debug("REST request to get Token{}", warehouseTokenDTO.getLotid());
        log.debug("REST request to get Token{}", warehouseTokenDTO.getWarehouselicenseno());
        return warehouseTokenService.getToken(warehouseTokenDTO);
    }

    /**
     *
     * @param warehouseTokenDTO
     */
    @RequestMapping(value = "/deletetoken",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void deleteToken(@RequestBody WarehouseTokenDTO warehouseTokenDTO) {
        log.debug("REST request to get Warehouse Chamber Stack{}", warehouseTokenDTO);
        warehouseTokenService.delete(warehouseTokenDTO);
    }

    @RequestMapping(value = "/generatewarehousetoken",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public WarehouseTokenDTO generateWarehouseToken(@RequestBody WarehouseTokenDTO warehouseTokenDTO) {
        log.debug("REST request to generate WarehouseToken {}", warehouseTokenDTO);
        if (warehouseTokenDTO.getWarehouselicenseno() != null && warehouseTokenDTO.getLotid() != null) {
            return warehouseTokenService.generateWarehouseToken(warehouseTokenDTO.getWarehouselicenseno(), warehouseTokenDTO.getLotid());
        } else {
            return new WarehouseTokenDTO();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getalltokens/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<WarehouseToken> getAllTokens(@PathVariable String id) {
        return warehouseTokenService.getAllTokens(id);
    }

}
