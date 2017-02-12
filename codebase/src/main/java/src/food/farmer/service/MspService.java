package src.food.farmer.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import src.food.farmer.domain.Msp;
import src.food.farmer.repository.MspRepository;
import src.food.farmer.web.rest.dto.MspDTO;
import src.food.farmer.web.rest.mapper.MspDTOEntityMapper;

/**
 * Service Implementation for managing Farmer's Personal Detail.
 */
@Service
public class MspService {

    private final Logger log = LoggerFactory.getLogger(MspService.class);

    @Inject
    private MspRepository mspRepository;

    /**
     * Get Personal Detail.
     *
     *
     * @return the entity
     */
    public MspDTO getMspById(String id) {
        log.debug("Request to get Msp : {}", id);
        return (new MspDTOEntityMapper()).mapMspEntityToDTO(mspRepository.getMspById(id));
    }

    public List<MspDTO> getMspYearWise(int year) {
        log.debug("Request to get Msp : {}", year);
        List<Msp> listMsp = mspRepository.getMspYearWise(year);
        List<MspDTO> listMspDTO = new ArrayList<MspDTO>();
        for (int i = 0; i < listMsp.size(); i++) {
            listMspDTO.add((new MspDTOEntityMapper()).mapMspEntityToDTO(listMsp.get(i)));
        }
        return listMspDTO;
    }

}
