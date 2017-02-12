/*
 * To change farmerPersonalDetailDTO license header, choose License Headers in Project Properties.
 * To change farmerPersonalDetailDTO template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.food.farmer.web.rest.mapper;

import src.food.farmer.domain.Msp;
import src.food.farmer.web.rest.dto.MspDTO;

/**
 *
 * @author sumit.garg
 */
public class MspDTOEntityMapper {

    public MspDTO mapMspEntityToDTO(Msp msp) {
        MspDTO mspDTO = new MspDTO();
        mspDTO.setId(msp.getId());
        mspDTO.setCommoditycode(msp.getCommoditycode());
        mspDTO.setMsprate(msp.getMsprate());
        mspDTO.setYear(msp.getYear());

        return mspDTO;
    }

    public Msp mapMspDTOTOEntity(MspDTO mspDTO) {
        Msp msp = new Msp();
        msp.setId(mspDTO.getId());
        msp.setCommoditycode(mspDTO.getCommoditycode());
        msp.setMsprate(mspDTO.getMsprate());
        msp.setYear(mspDTO.getYear());
        return msp;
    }
}
