package src.food.farmer.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import src.food.farmer.domain.Warehouse;

/**
 *
 */
@Repository
public class WarehouseRepository {

    @Inject
    private Session session;

    private Mapper<Warehouse> mapper;
    private PreparedStatement allActiveWarehouses;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(Warehouse.class);
        allActiveWarehouses = session.prepare("SELECT * FROM warehouse WHERE active=:active ALLOW FILTERING");
    }

    public Warehouse get(String warehouselicenseno) {
        return mapper.get(warehouselicenseno);
    }

    public void saveWarehouse(Warehouse warehouse) {
        mapper.save(warehouse);
    }

    public List<Warehouse> getActiveWarehouses() {
        BoundStatement stmt = allActiveWarehouses.bind();
        List<Warehouse> listWarehouse = new ArrayList<>();
        stmt.setInt("active", 1);
        ResultSet rs = session.execute(stmt);

        if (rs.isExhausted()) {
            return listWarehouse;
        }

        for (Row row : rs) {
            Warehouse warehouse = new Warehouse();
            warehouse.setWarehouselicenseno(row.getString("warehouselicenseno"));
            warehouse.setWarehousename(row.getString("warehousename"));
            listWarehouse.add(warehouse);
        }
        return listWarehouse;
    }

}
