package src.food.farmer.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import src.food.farmer.domain.Location;

/**
 * Cassandra repository for the Farmer's Personal Details entity.
 */
@Repository
public class FarmerLocationDetailRepository {

    @Inject
    private Session session;

    private Mapper<Location> mapper;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(Location.class);

    }

    public Location getFarmerLocationDetail(String id) {

        return mapper.get(id);
    }

    public boolean update(Location location) {
        mapper.save(location);
        return true;
    }

}
