package src.food.farmer.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import src.food.farmer.domain.WarehouseLotStack;

/**
 *
 */
@Repository
public class WarehouseLotStackRepository {

    @Inject
    private Session session;

    private Mapper<WarehouseLotStack> mapper;
    private PreparedStatement getLotStack;
    private PreparedStatement getStockinLotStack;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(WarehouseLotStack.class);
        getLotStack = session.prepare("SELECT * FROM warehouselotstack WHERE lotid=:lotid allow filtering");
        getStockinLotStack = session.prepare("SELECT * FROM warehouselotstack WHERE lotid=:lotid and stackid=:stackid allow filtering");
    }

    public void saveLotStack(WarehouseLotStack warehouseLotStack) {
        mapper.save(warehouseLotStack);
    }

    public List<WarehouseLotStack> getLotStack(UUID lotid) {
        BoundStatement stmt = getLotStack.bind();

        stmt.setUUID("lotid", lotid);

        ResultSet rs = session.execute(stmt);
        List<WarehouseLotStack> warehouseLotStackList = new ArrayList<>();
        if (rs.isExhausted()) {
            return new ArrayList<>();
        }
        for (Row row : rs) {
            WarehouseLotStack warehouseLotStack = new WarehouseLotStack();
            warehouseLotStack.setId(row.getUUID("id"));
            warehouseLotStack.setLotid(row.getUUID("lotid"));
            warehouseLotStack.setStackid(row.getUUID("stackid"));
            warehouseLotStack.setBags(row.getInt("bags"));
            warehouseLotStack.setOndate(row.getTimestamp("ondate"));
            warehouseLotStack.setByuser(row.getString("byuser"));
            warehouseLotStackList.add(warehouseLotStack);
        }
        return warehouseLotStackList;
    }
    
    public List<WarehouseLotStack> getStockinLotStack(UUID lotid,UUID stackid) {
        BoundStatement stmt = getStockinLotStack.bind();

        stmt.setUUID("lotid", lotid);
        stmt.setUUID("stackid", stackid);

        ResultSet rs = session.execute(stmt);
        List<WarehouseLotStack> warehouseLotStackList = new ArrayList<>();
        if (rs.isExhausted()) {
            return new ArrayList<>();
        }
        for (Row row : rs) {
            WarehouseLotStack warehouseLotStack = new WarehouseLotStack();
            warehouseLotStack.setId(row.getUUID("id"));
            warehouseLotStack.setLotid(row.getUUID("lotid"));
            warehouseLotStack.setStackid(row.getUUID("stackid"));
            warehouseLotStack.setBags(row.getInt("bags"));
            warehouseLotStack.setOndate(row.getTimestamp("ondate"));
            warehouseLotStack.setByuser(row.getString("byuser"));
            warehouseLotStackList.add(warehouseLotStack);
        }
        return warehouseLotStackList;
    }

}
