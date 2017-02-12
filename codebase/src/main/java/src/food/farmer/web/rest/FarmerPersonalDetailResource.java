package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.net.URISyntaxException;
import src.food.farmer.service.FarmerPersonalDetailService;
import src.food.farmer.web.rest.dto.FarmerPersonalDetailDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/farmer")
public class FarmerPersonalDetailResource {

    private final Logger log = LoggerFactory.getLogger(FarmerPersonalDetailResource.class);

    @Inject
    private FarmerPersonalDetailService farmerPersonalDetailService;

    /**
     * GET Personal Detail of Farmer.
     *
     * @param id
     * @return the ResponseEntity with status 200 (OK)
     *
     */
    @RequestMapping(value = "/personaldetails/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public FarmerPersonalDetailDTO getFarmerPersonalDetail(@PathVariable String id) {
        log.debug("REST request to get all Msps", id);
        return farmerPersonalDetailService.getFarmerPersonalDetail(id);
    }

    @RequestMapping(value = "/updatepersonaldetails",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public boolean updateFarmerPersonalDetail(@RequestBody FarmerPersonalDetailDTO farmerPersonalDetailDTO) throws URISyntaxException {
        log.debug("REST request to update Msp : {}", farmerPersonalDetailDTO);
        return farmerPersonalDetailService.update(farmerPersonalDetailDTO);

    }

}
