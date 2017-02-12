package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import src.food.farmer.domain.WarehouseCommodityRecievedQuality;
import src.food.farmer.domain.util.DateUtility;
import src.food.farmer.service.WarehouseCommodityRecievedQualityService;
import src.food.farmer.web.rest.dto.WarehouseCommodityRecievedQualityDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/api")
public class WarehouseCommodityRecievedQualityResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseCommodityRecievedQualityResource.class);

    @Inject
    private WarehouseCommodityRecievedQualityService warehouseCommodityRecievedQualityService;

    @RequestMapping(value = "/savecommodityquality",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void save(@RequestBody List<WarehouseCommodityRecievedQualityDTO> listWarehouseCommodityRecievedQualityDTO) throws URISyntaxException {

        for (int i = 1; i < listWarehouseCommodityRecievedQualityDTO.size(); i++) {
            log.debug("#####################REST request to update WarehouseCommodityRecievedQuality : {}", listWarehouseCommodityRecievedQualityDTO.get(i).getLotid());
            warehouseCommodityRecievedQualityService.saveWarehouseCommodityRecievedQuality(listWarehouseCommodityRecievedQualityDTO.get(i));

        }

    }

    @RequestMapping(value = "/getlotquality/{lotid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<WarehouseCommodityRecievedQuality> getLotQuality(@PathVariable UUID lotid) {
        log.debug("REST request to get WarehouseCommodityRecievedDTO {}", lotid);
        return warehouseCommodityRecievedQualityService.getLotQuality(lotid);
    }

}
