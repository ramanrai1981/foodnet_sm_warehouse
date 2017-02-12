package src.food.farmer.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import src.food.farmer.domain.WarehouseReceipt;

/**
 *
 */
@Repository
public class WarehouseReceiptRepository {

    @Inject
    private Session session;

    private Mapper<WarehouseReceipt> mapper;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(WarehouseReceipt.class);

    }

    public void save(WarehouseReceipt warehouseReceipt) {
        mapper.save(warehouseReceipt);
    }

}
