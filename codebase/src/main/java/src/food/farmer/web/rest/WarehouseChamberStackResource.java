package src.food.farmer.web.rest;

import com.codahale.metrics.annotation.Timed;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import src.food.farmer.domain.WarehouseChamberStack;
import src.food.farmer.service.WarehouseChamberStackService;
import src.food.farmer.web.rest.dto.WarehouseChamberStackDTO;
import src.food.farmer.web.rest.dto.WarehouseStockInStackDTO;

/**
 * REST controller for managing Farmer.
 */
@RestController
@RequestMapping("/api")
public class WarehouseChamberStackResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseChamberStackResource.class);

    @Inject
    private WarehouseChamberStackService warehouseChamberStackService;
    @Inject
    private WarehouseLotStackResource warehouseLotStackResource;

    /**
     *
     *
     * @param id
     * @return the ResponseEntity with status 200 (OK)
     *
     */
    @RequestMapping(value = "/getchamberstack/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<WarehouseChamberStackDTO> getActiveChambersStacks(@PathVariable String id) {
        log.debug("REST request to get Warehouse Chamber Stack{}", id);
        List<WarehouseChamberStack> listChamberStacks = warehouseChamberStackService.getActiveChambersStacks(id);
        List<WarehouseChamberStackDTO> listChamberStacksDTO = new ArrayList<>();

        for (int i = 0; i < listChamberStacks.size(); i++) {
            try {
                ModelMapper modelMapper = new ModelMapper();
                WarehouseChamberStackDTO warehouseChamberStackDTO = modelMapper.map(listChamberStacks.get(i), WarehouseChamberStackDTO.class);
                WarehouseStockInStackDTO warehouseStockInStackDTO = new WarehouseStockInStackDTO();
                warehouseStockInStackDTO.setWarehouselicenseno(listChamberStacks.get(i).getWarehouselicenseno());
                warehouseStockInStackDTO.setStackid(listChamberStacks.get(i).getStackid());
                warehouseChamberStackDTO.setStock(warehouseLotStackResource.getStockInStack(warehouseStockInStackDTO));
                listChamberStacksDTO.add(warehouseChamberStackDTO);
            } catch (URISyntaxException ex) {
                java.util.logging.Logger.getLogger(WarehouseChamberStackResource.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return listChamberStacksDTO;

    }

    /**
     *
     *
     *
     * @param warehouseChamberStackDTO
     */
    @RequestMapping(value = "/savechamberstack",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void saveChamberStacks(@RequestBody WarehouseChamberStackDTO warehouseChamberStackDTO) {
        warehouseChamberStackService.saveChamberStacks(warehouseChamberStackDTO);
    }

    /**
     *
     *
     * @param id
     * @return the ResponseEntity with status 200 (OK)
     *
     */
    @RequestMapping(value = "/getstack/{stackid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public WarehouseChamberStackDTO getStack(@PathVariable UUID stackid) {
        log.debug("REST request to get Warehouse Chamber Stack{}", stackid);
        return warehouseChamberStackService.getStack(stackid);

    }
}
