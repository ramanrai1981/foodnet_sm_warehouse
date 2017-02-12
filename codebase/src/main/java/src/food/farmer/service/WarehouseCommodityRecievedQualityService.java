package src.food.farmer.service;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import src.food.farmer.domain.WarehouseCommodityRecievedQuality;
import src.food.farmer.domain.util.DateUtility;
import src.food.farmer.repository.WarehouseCommodityRecievedQualityRepository;
import src.food.farmer.web.rest.dto.WarehouseCommodityRecievedQualityDTO;

/**
 * Service Implementation
 */
@Service
public class WarehouseCommodityRecievedQualityService {

    private final Logger log = LoggerFactory.getLogger(WarehouseCommodityRecievedQualityService.class);

    @Inject
    private WarehouseCommodityRecievedQualityRepository warehouseCommodityRecievedQualityRepository;

    public void saveWarehouseCommodityRecievedQuality(WarehouseCommodityRecievedQualityDTO warehouseCommodityRecievedQualityDTO) {
        ModelMapper modelMapper = new ModelMapper();
        if (warehouseCommodityRecievedQualityDTO == null) {
            warehouseCommodityRecievedQualityDTO.setOndate(DateUtility.getCutrrentDateTime());
        }
        warehouseCommodityRecievedQualityRepository.saveWarehouseCommodityRecievedQuality(modelMapper.map(warehouseCommodityRecievedQualityDTO, WarehouseCommodityRecievedQuality.class));
    }

    public List<WarehouseCommodityRecievedQuality> getLotQuality(UUID lotid) {
        return warehouseCommodityRecievedQualityRepository.getLotQuality(lotid);
    }

}
