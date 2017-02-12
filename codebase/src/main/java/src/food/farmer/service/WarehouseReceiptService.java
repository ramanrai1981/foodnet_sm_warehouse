package src.food.farmer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import src.food.farmer.domain.WarehouseReceipt;
import src.food.farmer.repository.WarehouseReceiptRepository;
import src.food.farmer.web.rest.dto.WarehouseReceiptDTO;

/**
 * Service Implementation
 */
@Service
public class WarehouseReceiptService {

    private final Logger log = LoggerFactory.getLogger(WarehouseReceiptService.class);

    @Inject
    private WarehouseReceiptRepository warehouseReceiptRepository;

    public void save(WarehouseReceiptDTO warehouseReceiptDTO) {
        ModelMapper modelMapper = new ModelMapper();
        warehouseReceiptRepository.save(modelMapper.map(warehouseReceiptDTO, WarehouseReceipt.class));
    }

}
