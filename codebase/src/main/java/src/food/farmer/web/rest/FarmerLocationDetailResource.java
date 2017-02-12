package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.net.URISyntaxException;
import src.food.farmer.service.FarmerLocationlDetailService;
import src.food.farmer.web.rest.dto.LocationDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/farmer")
public class FarmerLocationDetailResource {

    private final Logger log = LoggerFactory.getLogger(FarmerLocationDetailResource.class);

    @Inject
    private FarmerLocationlDetailService farmerLocationlDetailService;

    /**
     * GET Personal Detail of Farmer.
     *
     * @param id
     * @return the ResponseEntity with status 200 (OK)
     *
     */
    @RequestMapping(value = "/locationdetails/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public LocationDTO getFarmerPersonalDetail(@PathVariable String id) {
        log.debug("REST request to get Location for farmer ID {}", id);
        return farmerLocationlDetailService.getFarmerLocationDetail(id);
    }

    @RequestMapping(value = "/updatelocationdetails",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public boolean updateFarmerLocationDetail(@RequestBody LocationDTO locationDTO) throws URISyntaxException {
        log.debug("REST request to update Location of Farmer : {}", locationDTO);
        return farmerLocationlDetailService.update(locationDTO);

    }

}
