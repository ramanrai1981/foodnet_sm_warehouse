package src.food.farmer.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import src.food.farmer.domain.WarehouseCommodityRecieved;
import src.food.farmer.web.rest.dto.WarehouseCommodityRecievedDTO;

/**
 *
 */
@Repository
public class WarehouseCommodityRecievedRepository {

    private final Logger log = LoggerFactory.getLogger(WarehouseCommodityRecievedRepository.class);
    @Inject
    private Session session;

    private Mapper<WarehouseCommodityRecieved> mapper;
    private PreparedStatement byLotID;
    private PreparedStatement byWarehouseID;
    private PreparedStatement updateStatus;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(WarehouseCommodityRecieved.class);
        byLotID = session.prepare("SELECT * FROM warehousecommodityrecieved WHERE lotid=:lotid allow filtering");
        byWarehouseID = session.prepare("SELECT * FROM warehousecommodityrecieved WHERE warehouselicenseno=:warehouselicenseno allow filtering");
        updateStatus = session.prepare("update warehousecommodityrecieved set status=:status  WHERE warehouselicenseno=:warehouselicenseno and lotid=:lotid");

    }

    public WarehouseCommodityRecieved getByLotID(UUID id) {
        BoundStatement stmt = byLotID.bind();

        stmt.setUUID("lotid", id);

        ResultSet rs = session.execute(stmt);
        WarehouseCommodityRecieved warehouseCommodityRecieved = new WarehouseCommodityRecieved();
        if (rs.isExhausted()) {
            return warehouseCommodityRecieved;
        }
        for (Row row : rs) {

            warehouseCommodityRecieved.setBookno(row.getString("bookno"));
            warehouseCommodityRecieved.setCommoditycode(row.getUUID("commoditycode"));
            warehouseCommodityRecieved.setDepositor(row.getUUID("depositor"));
            warehouseCommodityRecieved.setDrivername(row.getString("drivername"));
            warehouseCommodityRecieved.setEnteredby(row.getString("enteredby"));
            warehouseCommodityRecieved.setInwarehouse(row.getString("inwarehouse"));
            warehouseCommodityRecieved.setInmill(row.getString("inmill"));
            warehouseCommodityRecieved.setLiftedbags(row.getInt("liftedbags"));
            warehouseCommodityRecieved.setLiftedgunnyweight(row.getDouble("liftedgunnyweight"));
            warehouseCommodityRecieved.setLiftednetweight(row.getDouble("liftednetweight"));

            warehouseCommodityRecieved.setLiftedweight(row.getDouble("liftedweight"));
            warehouseCommodityRecieved.setLotid(row.getUUID("lotid"));
            warehouseCommodityRecieved.setSourcemandi(row.getUUID("sourcemandi"));
            warehouseCommodityRecieved.setSourcewarehouse(row.getString("sourcewarehouse"));
            warehouseCommodityRecieved.setSourcemill(row.getString("sourcemill"));
            warehouseCommodityRecieved.setSrno(row.getString("srno"));
            warehouseCommodityRecieved.setStatus(row.getString("status"));
            warehouseCommodityRecieved.setVehicleno(row.getString("vehicleno"));
            warehouseCommodityRecieved.setWarehouselicenseno(row.getString("warehouselicenseno"));
            warehouseCommodityRecieved.setWarehousereciept(row.getString("warehousereciept"));
        }
        return warehouseCommodityRecieved;
    }

    public void save(WarehouseCommodityRecieved warehouseCommodityRecieved) {
        log.debug("=======================>Request to save WarehouseCommodityRecieved : {}", warehouseCommodityRecieved.getLotid());
        mapper.save(warehouseCommodityRecieved);
    }

    public List<WarehouseCommodityRecieved> getAllLots(String warehouselicenseno) {
        BoundStatement stmt = byWarehouseID.bind();

        stmt.setString("warehouselicenseno", warehouselicenseno);

        ResultSet rs = session.execute(stmt);
        List<WarehouseCommodityRecieved> listWarehouseCommodityRecieved = new ArrayList<>();
        if (rs.isExhausted()) {
            return listWarehouseCommodityRecieved;
        }
        for (Row row : rs) {
            WarehouseCommodityRecieved warehouseCommodityRecieved = new WarehouseCommodityRecieved();
            warehouseCommodityRecieved.setBookno(row.getString("bookno"));
            warehouseCommodityRecieved.setCommoditycode(row.getUUID("commoditycode"));
            warehouseCommodityRecieved.setDepositor(row.getUUID("depositor"));
            warehouseCommodityRecieved.setDrivername(row.getString("drivername"));
            warehouseCommodityRecieved.setEnteredby(row.getString("enteredby"));
            warehouseCommodityRecieved.setInwarehouse(row.getString("inwarehouse"));
            warehouseCommodityRecieved.setInmill(row.getString("inmill"));
            warehouseCommodityRecieved.setLiftedbags(row.getInt("liftedbags"));
            warehouseCommodityRecieved.setLiftedgunnyweight(row.getDouble("liftedgunnyweight"));
            warehouseCommodityRecieved.setLiftednetweight(row.getDouble("liftednetweight"));

            warehouseCommodityRecieved.setLiftedweight(row.getDouble("liftedweight"));
            warehouseCommodityRecieved.setLotid(row.getUUID("lotid"));
            warehouseCommodityRecieved.setSourcemandi(row.getUUID("sourcemandi"));
            warehouseCommodityRecieved.setSourcewarehouse(row.getString("sourcewarehouse"));
            warehouseCommodityRecieved.setSourcemill(row.getString("sourcemill"));
            warehouseCommodityRecieved.setSrno(row.getString("srno"));
            warehouseCommodityRecieved.setStatus(row.getString("status"));
            warehouseCommodityRecieved.setVehicleno(row.getString("vehicleno"));
            warehouseCommodityRecieved.setWarehouselicenseno(row.getString("warehouselicenseno"));
            warehouseCommodityRecieved.setWarehousereciept(row.getString("warehousereciept"));
            listWarehouseCommodityRecieved.add(warehouseCommodityRecieved);
        }
        return listWarehouseCommodityRecieved;
    }

    public void updateLotStatus(WarehouseCommodityRecievedDTO warehouseCommodityRecievedDTO) {
        BoundStatement stmt = updateStatus.bind();

        stmt.setString("status", warehouseCommodityRecievedDTO.getStatus());
        stmt.setString("warehouselicenseno", warehouseCommodityRecievedDTO.getWarehouselicenseno());
        stmt.setUUID("lotid", warehouseCommodityRecievedDTO.getLotid());

        session.execute(stmt);
    }

}
