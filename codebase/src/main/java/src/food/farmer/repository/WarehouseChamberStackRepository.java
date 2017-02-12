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
import src.food.farmer.domain.WarehouseChamberStack;

/**
 *
 */
@Repository
public class WarehouseChamberStackRepository {

    @Inject
    private Session session;

    private Mapper<WarehouseChamberStack> mapper;
    private PreparedStatement allChambersStacks;
    private PreparedStatement getOneStack;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(WarehouseChamberStack.class);
        allChambersStacks = session.prepare("SELECT * FROM warehousechamberstack WHERE warehouselicenseno=:warehouselicenseno allow filtering");
        getOneStack = session.prepare("SELECT * FROM warehousechamberstack WHERE stackid=:stackid allow filtering");
    }

    public List<WarehouseChamberStack> getActiveChambersStacks(String warehouselicenseno) {
        BoundStatement stmt = allChambersStacks.bind();

        stmt.setString("warehouselicenseno", warehouselicenseno);

        ResultSet rs = session.execute(stmt);
        List<WarehouseChamberStack> warehouseChamberStackList = new ArrayList<>();
        if (rs.isExhausted()) {
            return new ArrayList<>();
        }
        for (Row row : rs) {
            WarehouseChamberStack warehouseChamberStack = new WarehouseChamberStack();
            warehouseChamberStack.setWarehouselicenseno(row.getString("warehouselicenseno"));
            warehouseChamberStack.setCapacityinbags(row.getDouble("capacityinbags"));
            warehouseChamberStack.setCommoditycode(row.getUUID("commoditycode"));
            warehouseChamberStack.setFromdate(row.getTimestamp("fromdate"));
            warehouseChamberStack.setTodate(row.getTimestamp("todate"));
            warehouseChamberStack.setChamberid(row.getUUID("chamberid"));
            warehouseChamberStack.setStackid(row.getUUID("stackid"));
            warehouseChamberStack.setChambername(row.getString("chambername"));
            warehouseChamberStack.setStackname(row.getString("stackname"));
            warehouseChamberStack.setGodownid(row.getUUID("godownid"));
            warehouseChamberStack.setGodownname(row.getString("godownname"));
            warehouseChamberStack.setStacktype(row.getString("stacktype"));
            warehouseChamberStack.setDepositor(row.getUUID("depositor"));

            warehouseChamberStackList.add(warehouseChamberStack);

        }
        
        return warehouseChamberStackList;
    }

    public void saveChamberStacks(WarehouseChamberStack warehouseChamberStack) {
        mapper.save(warehouseChamberStack);
    }

    public WarehouseChamberStack getStack(UUID stackid) {
        BoundStatement stmt = getOneStack.bind();

        stmt.setUUID("stackid", stackid);

        ResultSet rs = session.execute(stmt);
        WarehouseChamberStack warehouseChamberStack = new WarehouseChamberStack();
        if (rs.isExhausted()) {
            return warehouseChamberStack;
        }
        for (Row row : rs) {

            warehouseChamberStack.setWarehouselicenseno(row.getString("warehouselicenseno"));
            warehouseChamberStack.setCapacityinbags(row.getDouble("capacityinbags"));
            warehouseChamberStack.setCommoditycode(row.getUUID("commoditycode"));
            warehouseChamberStack.setFromdate(row.getTimestamp("fromdate"));
            warehouseChamberStack.setTodate(row.getTimestamp("todate"));
            warehouseChamberStack.setChamberid(row.getUUID("chamberid"));
            warehouseChamberStack.setStackid(row.getUUID("stackid"));
            warehouseChamberStack.setChambername(row.getString("chambername"));
            warehouseChamberStack.setStackname(row.getString("stackname"));
            warehouseChamberStack.setGodownid(row.getUUID("godownid"));
            warehouseChamberStack.setGodownname(row.getString("godownname"));
            warehouseChamberStack.setStacktype(row.getString("stacktype"));
            warehouseChamberStack.setDepositor(row.getUUID("depositor"));

        }
        return warehouseChamberStack;
    }

}
