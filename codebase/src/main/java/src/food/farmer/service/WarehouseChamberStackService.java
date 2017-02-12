package src.food.farmer.service;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import src.food.farmer.domain.WarehouseChamberStack;
import src.food.farmer.domain.util.DateUtility;
import src.food.farmer.repository.WarehouseChamberStackRepository;
import src.food.farmer.web.rest.dto.WarehouseChamberStackDTO;

/**
 * Service Implementation
 */
@Service
public class WarehouseChamberStackService {

    private final Logger log = LoggerFactory.getLogger(WarehouseChamberStackService.class);

    @Inject
    private WarehouseChamberStackRepository warehouseChamberStackRepository;

    public List<WarehouseChamberStack> getActiveChambersStacks(String warehouselicenseno) {
        return warehouseChamberStackRepository.getActiveChambersStacks(warehouselicenseno);
    }

    public void autoGenerateStacks(WarehouseChamberStackDTO warehouseChamberStackDTO) {
        ModelMapper modelMapper = new ModelMapper();
        warehouseChamberStackDTO.setFromdate(DateUtility.getCutrrentDateTime());
        warehouseChamberStackRepository.saveChamberStacks(modelMapper.map(warehouseChamberStackDTO, WarehouseChamberStack.class));
    }

    public void saveChamberStacks(WarehouseChamberStackDTO warehouseChamberStackDTO) {
        ModelMapper modelMapper = new ModelMapper();
        warehouseChamberStackRepository.saveChamberStacks(modelMapper.map(warehouseChamberStackDTO, WarehouseChamberStack.class));

    }

    public WarehouseChamberStackDTO getStack(UUID stackid) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(warehouseChamberStackRepository.getStack(stackid), WarehouseChamberStackDTO.class);
    }
}
