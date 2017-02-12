package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import src.food.farmer.service.MspService;
import src.food.farmer.web.rest.dto.MspCurrentYearDTO;
import src.food.farmer.web.rest.dto.MspDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/farmer")
public class MspResource {

    private final Logger log = LoggerFactory.getLogger(MspResource.class);

    @Inject
    private MspService mspService;

    @Inject
    private CommodityResource commodityResource;

    @RequestMapping(value = "/mspbyid/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public MspDTO getMspById(@PathVariable String id) {
        log.debug("REST request to get Msp by ID {}", id);
        return mspService.getMspById(id);
    }

    @RequestMapping(value = "/mspcurrentyear",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<MspCurrentYearDTO> getMspYearWise() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        log.debug("REST request to get all Msps in the current year {}", year);
        List<MspCurrentYearDTO> listMspCurrentYearDTO = new ArrayList<>();
//        List<MspDTO> listMspDTO = mspService.getMspYearWise(year);
//
//        for (int i = 0; i < listMspDTO.size(); i++) {
//            CommodityDTO commodityDTO = commodityResource.getCommodity(listMspDTO.get(i).getCommoditycode());
//            MspCurrentYearDTO mspCurrentYearDTO = new MspCurrentYearDTO();
//            mspCurrentYearDTO.setId(listMspDTO.get(i).getId());
//            mspCurrentYearDTO.setMsprate(listMspDTO.get(i).getMsprate());
//            mspCurrentYearDTO.setYear(listMspDTO.get(i).getYear());
//            mspCurrentYearDTO.setCommodityName(commodityDTO.getCommodityname());
//            listMspCurrentYearDTO.add(mspCurrentYearDTO);
//        }
        return listMspCurrentYearDTO;

    }

}
