package src.food.farmer.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import src.food.farmer.domain.FarmerPersonalDetail;

/**
 * Cassandra repository for the Farmer's Personal Details entity.
 */
@Repository
public class FarmerPersonalDetailRepository {

    @Inject
    private Session session;

    private Mapper<FarmerPersonalDetail> mapper;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(FarmerPersonalDetail.class);

    }

    public FarmerPersonalDetail getFarmerPersonalDetail(String id) {

        return mapper.get(id);
    }
    
     public boolean update(FarmerPersonalDetail farmerPersonalDetail) {

        mapper.save(farmerPersonalDetail);
        return true;
    }

}
