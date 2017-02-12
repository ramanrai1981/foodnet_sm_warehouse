package src.food.farmer.service;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import src.food.farmer.domain.WarehouseWeighment;
import src.food.farmer.repository.WarehouseWeighmentRepository;
import src.food.farmer.web.rest.dto.WarehouseWeighmentDTO;

/**
 * Service Implementation
 */
@Service
public class WarehouseWeighmentService {

    private final Logger log = LoggerFactory.getLogger(WarehouseWeighmentService.class);

    @Inject
    private WarehouseWeighmentRepository warehouseWeighmentRepository;

    public void saveWeighment(WarehouseWeighmentDTO warehouseWeighmentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        WarehouseWeighment warehouseWeighment = modelMapper.map(warehouseWeighmentDTO, WarehouseWeighment.class);
        warehouseWeighmentRepository.saveWeighment(warehouseWeighment);

    }

    public WarehouseWeighmentDTO getWeighment(UUID lotid) {
        ModelMapper modelMapper = new ModelMapper();
        WarehouseWeighment warehouseWeighment = warehouseWeighmentRepository.getWeighment(lotid);
        if (warehouseWeighment == null) {
            return new WarehouseWeighmentDTO();
        } 
            return modelMapper.map(warehouseWeighment, WarehouseWeighmentDTO.class);
      
    }

}
