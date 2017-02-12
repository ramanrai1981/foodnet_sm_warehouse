package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import src.food.farmer.domain.WarehouseCommodityRecieved;
import src.food.farmer.domain.WarehouseLotStack;
import src.food.farmer.domain.util.DateUtility;
import src.food.farmer.service.WarehouseChamberStackService;
import src.food.farmer.service.WarehouseCommodityRecievedService;
import src.food.farmer.service.WarehouseLotStackService;
import src.food.farmer.web.rest.dto.WarehouseChamberStackDTO;
import src.food.farmer.web.rest.dto.WarehouseLotStackDTO;
import src.food.farmer.web.rest.dto.WarehouseStockInStackDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/api")
public class WarehouseLotStackResource {
    
    private final Logger log = LoggerFactory.getLogger(WarehouseLotStackResource.class);
    
    @Inject
    private WarehouseLotStackService warehouseLotStackService;
    
    @Inject
    private WarehouseCommodityRecievedService warehouseCommodityRecievedService;
    
    @Inject
    private WarehouseChamberStackService warehouseChamberStackService;

    /**
     *
     *
     *
     * @param warehouseLotStackDTO
     * @return the ResponseEntity with status 200 (OK)
     * @throws java.net.URISyntaxException
     *
     */
    @RequestMapping(value = "/savelotstack",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public boolean save(@RequestBody WarehouseLotStackDTO warehouseLotStackDTO) throws URISyntaxException {
        
        log.debug("#####################REST request to update Lot Stack against Lot : {}", warehouseLotStackDTO.getLotid());
        warehouseLotStackDTO.setId(UUID.randomUUID());
        warehouseLotStackDTO.setOndate(DateUtility.getCutrrentDateTime());
        warehouseLotStackService.saveLotStack(warehouseLotStackDTO);
        
        return true;
        
    }
    
    @RequestMapping(value = "/getlotstack/{lotid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<WarehouseLotStackDTO> getLotStack(@PathVariable UUID lotid) throws URISyntaxException {
        
        log.debug("#####################REST request to get WarehouseWeighment of Lot  : {}", lotid);
        List<WarehouseLotStack> listWarehouseLotStack = warehouseLotStackService.getLotStack(lotid);
        List<WarehouseLotStackDTO> listWarehouseLotStackDTO = new ArrayList<>();
        for (int i = 0; i < listWarehouseLotStack.size(); i++) {
            WarehouseLotStackDTO warehouseLotStackDTO = new WarehouseLotStackDTO();
            WarehouseChamberStackDTO warehouseChamberStackDTO = warehouseChamberStackService.getStack(listWarehouseLotStack.get(i).getStackid());
            warehouseLotStackDTO.setId(listWarehouseLotStack.get(i).getId());
            warehouseLotStackDTO.setLotid(listWarehouseLotStack.get(i).getLotid());
            warehouseLotStackDTO.setStackid(listWarehouseLotStack.get(i).getStackid());
            warehouseLotStackDTO.setBags(listWarehouseLotStack.get(i).getBags());
            warehouseLotStackDTO.setOndate(listWarehouseLotStack.get(i).getOndate());
            warehouseLotStackDTO.setByuser(listWarehouseLotStack.get(i).getByuser());
            warehouseLotStackDTO.setGodownname(warehouseChamberStackDTO.getGodownname());
            warehouseLotStackDTO.setChambername(warehouseChamberStackDTO.getChambername());
            warehouseLotStackDTO.setStackname(warehouseChamberStackDTO.getStackname());
            listWarehouseLotStackDTO.add(warehouseLotStackDTO);
        }
        return listWarehouseLotStackDTO;
        
    }
    
    @RequestMapping(value = "/getstockinstack",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public int getStockInStack(@RequestBody WarehouseStockInStackDTO warehouseStockInStackDTO) throws URISyntaxException {
        int stock = 0;
        List<WarehouseCommodityRecieved> listLots = warehouseCommodityRecievedService.getAllLots(warehouseStockInStackDTO.getWarehouselicenseno());
        for (int i = 0; i < listLots.size(); i++) {
            List<WarehouseLotStack> listLotStack = warehouseLotStackService.getStockinLotStack(listLots.get(i).getLotid(), warehouseStockInStackDTO.getStackid());
            for (int j = 0; j < listLotStack.size(); j++) {
                stock = stock + listLotStack.get(j).getBags();
            }
        }
        
        return stock;
    }
    
}
