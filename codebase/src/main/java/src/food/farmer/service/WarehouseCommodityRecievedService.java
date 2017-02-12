package src.food.farmer.service;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import src.food.farmer.domain.WarehouseCommodityRecieved;
import src.food.farmer.repository.WarehouseCommodityRecievedRepository;
import src.food.farmer.web.rest.dto.WarehouseCommodityRecievedDTO;

/**
 * Service Implementation
 */
@Service
public class WarehouseCommodityRecievedService {

    private final Logger log = LoggerFactory.getLogger(WarehouseCommodityRecievedService.class);

    @Inject
    private WarehouseCommodityRecievedRepository warehouseCommodityRecievedRepository;

    /**
     * Get Personal Detail.
     *
     *
     * @param lotid
     * @return the entity
     */
    public WarehouseCommodityRecievedDTO getByLotID(UUID lotid) {
        log.debug("Request to get WarehouseCommodityRecieved : {}", lotid);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(warehouseCommodityRecievedRepository.getByLotID(lotid), WarehouseCommodityRecievedDTO.class);

    }

    public void save(WarehouseCommodityRecievedDTO warehouseCommodityRecievedDTO) {
        ModelMapper modelMapper = new ModelMapper();
        WarehouseCommodityRecieved warehouseCommodityRecieved = modelMapper.map(warehouseCommodityRecievedDTO, WarehouseCommodityRecieved.class);
        warehouseCommodityRecievedRepository.save(warehouseCommodityRecieved);

    }

    public List<WarehouseCommodityRecieved> getAllLots(String warehouselicenseno) {
        log.debug("Request to get WarehouseCommodityRecieved : {}", warehouselicenseno);
        return warehouseCommodityRecievedRepository.getAllLots(warehouselicenseno);
    }

    public void updateLotStatus(WarehouseCommodityRecievedDTO warehouseCommodityRecievedDTO) {
        warehouseCommodityRecievedRepository.updateLotStatus(warehouseCommodityRecievedDTO);
    }

}
