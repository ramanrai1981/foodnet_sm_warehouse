package src.food.farmer.service;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import src.food.farmer.domain.WarehouseLotStack;
import src.food.farmer.repository.WarehouseLotStackRepository;
import src.food.farmer.web.rest.dto.WarehouseLotStackDTO;

/**
 * Service Implementation
 */
@Service
public class WarehouseLotStackService {

    private final Logger log = LoggerFactory.getLogger(WarehouseLotStackService.class);

    @Inject
    private WarehouseLotStackRepository warehouseLotStackRepository;

    public void saveLotStack(WarehouseLotStackDTO warehouseLotStackDTO) {
        ModelMapper modelMapper = new ModelMapper();
        WarehouseLotStack warehouseLotStack = modelMapper.map(warehouseLotStackDTO, WarehouseLotStack.class);
        warehouseLotStackRepository.saveLotStack(warehouseLotStack);
    }

    public List<WarehouseLotStack> getLotStack(UUID lotid) {
        return warehouseLotStackRepository.getLotStack(lotid);

    }

     public List<WarehouseLotStack> getStockinLotStack(UUID lotid,UUID stackid) {
         return warehouseLotStackRepository.getStockinLotStack(lotid,stackid);
     }
    
}
