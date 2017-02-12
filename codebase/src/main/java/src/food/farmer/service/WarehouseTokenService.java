package src.food.farmer.service;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import src.food.farmer.domain.WarehouseToken;
import src.food.farmer.repository.WarehouseTokenRepository;
import src.food.farmer.web.rest.dto.WarehouseTokenDTO;

/**
 * Service Implementation
 */
@Service
public class WarehouseTokenService {

    private final Logger log = LoggerFactory.getLogger(WarehouseTokenService.class);

    @Inject
    private WarehouseTokenRepository warehouseTokenRepository;

    /**
     * Get Personal Detail.
     *
     *
     *
     * @param warehouselicenseno
     * @param lotId
     * @return the entity
     */
    public WarehouseTokenDTO generateWarehouseToken(String warehouselicenseno, UUID lotId) {
        log.debug("Request to get WarehouseToken for Warehouse : {}", warehouselicenseno);
        int token = generateToken();
        if (warehouseTokenRepository.isTokenInUse(warehouselicenseno, token)) {
            generateWarehouseToken(warehouselicenseno, lotId);
        }

        return this.save(warehouselicenseno, lotId, token);
    }

    public WarehouseTokenDTO getToken(WarehouseTokenDTO warehouseTokenDTO) {
        log.debug("Request to get WarehouseToken for Token : {}", warehouseTokenDTO);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(warehouseTokenRepository.getToken(warehouseTokenDTO), WarehouseTokenDTO.class);
    }

    private int generateToken() {
        return (int) (Math.random() * 9000) + 1000;
    }

    public WarehouseTokenDTO save(String warehouselicenseno, UUID lotId, int wtoken) {
        WarehouseToken token = new WarehouseToken();
        token.setWarehouselicenseno(warehouselicenseno);
        token.setLotid(lotId);
        token.setWtoken(wtoken);
        warehouseTokenRepository.save(token);
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(token, WarehouseTokenDTO.class);
    }

    public void delete(WarehouseTokenDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        warehouseTokenRepository.delete(modelMapper.map(dto, WarehouseToken.class));
    }

    public List<WarehouseToken> getAllTokens(String warehouseLicenseNo) {
        return warehouseTokenRepository.getAllTokens(warehouseLicenseNo);
    }

}
