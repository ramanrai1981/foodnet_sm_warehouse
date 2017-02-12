package src.food.farmer.service;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import src.food.farmer.domain.Warehouse;
import src.food.farmer.domain.util.DateUtility;
import src.food.farmer.repository.WarehouseRepository;
import src.food.farmer.web.rest.dto.WarehouseChamberStackDTO;
import src.food.farmer.web.rest.dto.WarehouseDTO;

/**
 * Service Implementation
 */
@Service
public class WarehouseService {
    
    private final Logger log = LoggerFactory.getLogger(WarehouseService.class);
    
    @Inject
    private WarehouseRepository warehouseRepository;
    @Inject
    private WarehouseChamberStackService warehouseChamberStackService;
    
    public WarehouseDTO getWarehouseDetail(String warehouselicenseno) {
        ModelMapper modelMapper = new ModelMapper();
        Warehouse warehouse = warehouseRepository.get(warehouselicenseno);
        if (warehouse != null) {
            return modelMapper.map(warehouse, WarehouseDTO.class);
        } else {
            return new WarehouseDTO();
        }
    }
    
    public void saveWarehouse(WarehouseDTO warehouseDTO) {
        String godownName = "Godown ";
        String chamberName = "Chamber ";
        int noOfGodowns = 0;
        int noOfChambers = 0;
        int noOfStacks = 0;
        ModelMapper modelMapper = new ModelMapper();
        if (getWarehouseDetail(warehouseDTO.getWarehouselicenseno()).getWarehouselicenseno() == null) {
            
            if (warehouseDTO.getGodowns() == 0) {
                godownName = warehouseDTO.getWarehousename();
                noOfGodowns = 1;
                
            } else {
                noOfGodowns = warehouseDTO.getGodowns();
            }
            if (warehouseDTO.getChambers() == 0) {
                noOfChambers = 1;
            } else {
                noOfChambers = warehouseDTO.getChambers();
            }
            if (warehouseDTO.getStacks() == 0) {
                noOfStacks = 1;
            } else {
                noOfStacks = warehouseDTO.getStacks();
            }
            
            if (warehouseDTO.getWarehousetype().equals("open")) {
                chamberName = "Platform ";
            }
            
            int global_godown = 1;
            int global_chamber = 1;
            int global_stack = 1;
            for (int godown = 0; godown < noOfGodowns; godown++) {
                String _godownname = "";
                UUID godownid = UUID.randomUUID();
                if (!godownName.equals(warehouseDTO.getWarehousename())) {
                    _godownname = godownName + global_godown++;
                } else {
                    _godownname = godownName;
                }
                for (int chamber = 0; chamber < noOfChambers; chamber++) {
                    UUID chamberid = UUID.randomUUID();
                    String chambername = chamberName + global_chamber++;
                    for (int stack = 0; stack < noOfStacks; stack++) {
                        WarehouseChamberStackDTO warehouseChamberStackDTO = new WarehouseChamberStackDTO();
                        warehouseChamberStackDTO.setWarehouselicenseno(warehouseDTO.getWarehouselicenseno());
                        warehouseChamberStackDTO.setGodownid(godownid);
                        warehouseChamberStackDTO.setGodownname(_godownname);
                        warehouseChamberStackDTO.setChamberid(chamberid);
                        warehouseChamberStackDTO.setChambername(chambername);
                        warehouseChamberStackDTO.setCapacityinbags(warehouseDTO.getCapacityinbags());
                        warehouseChamberStackDTO.setStackid(UUID.randomUUID());
                        warehouseChamberStackDTO.setStackname("Stack " + global_stack++);
                        warehouseChamberStackDTO.setCapacityinbags(warehouseDTO.getCapacityinbags());
                        warehouseChamberStackDTO.setFromdate(DateUtility.getCutrrentDateTime());
                        warehouseChamberStackDTO.setStacktype(warehouseDTO.getStacktype());
                        warehouseChamberStackService.autoGenerateStacks(warehouseChamberStackDTO);
                    }
                }
            }
        }
        warehouseDTO.setActive(1);
        warehouseRepository.saveWarehouse(modelMapper.map(warehouseDTO, Warehouse.class));
        
    }
    
    
     public List<Warehouse> getActiveWarehouses() {
         return warehouseRepository.getActiveWarehouses();
     }
}
