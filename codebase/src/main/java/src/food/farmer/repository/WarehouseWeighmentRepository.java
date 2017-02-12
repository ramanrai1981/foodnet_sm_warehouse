package src.food.farmer.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import src.food.farmer.domain.WarehouseWeighment;

/**
 *
 */
@Repository
public class WarehouseWeighmentRepository {

    @Inject
    private Session session;

    private Mapper<WarehouseWeighment> mapper;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(WarehouseWeighment.class);
    }

    public void saveWeighment(WarehouseWeighment warehouseWeighment) {
        mapper.save(warehouseWeighment);
    }

    public WarehouseWeighment getWeighment(UUID lotid) {
        return mapper.get(lotid);
    }

}
