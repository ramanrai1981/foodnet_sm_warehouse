package src.food.farmer.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import src.food.farmer.domain.WarehouseToken;
import src.food.farmer.web.rest.dto.WarehouseTokenDTO;

/**
 *
 */
@Repository
public class WarehouseTokenRepository {

    @Inject
    private Session session;

    private Mapper<WarehouseToken> mapper;
    private PreparedStatement isWarehouseTokenInUse;
    private PreparedStatement getLotByToken;
    private PreparedStatement deleteToken;
    private PreparedStatement getAllLots;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(WarehouseToken.class);
        isWarehouseTokenInUse = session.prepare("SELECT * FROM warehousetoken WHERE warehouselicenseno=:warehouselicenseno and wtoken=:wtoken  ALLOW FILTERING");
        getLotByToken = session.prepare("SELECT * FROM warehousetoken WHERE  warehouselicenseno=:warehouselicenseno and  wtoken=:wtoken  ALLOW FILTERING");
        deleteToken = session.prepare("delete  FROM warehousetoken WHERE warehouselicenseno=:warehouselicenseno and wtoken=:wtoken");
        getAllLots = session.prepare("SELECT * FROM warehousetoken WHERE  warehouselicenseno=:warehouselicenseno");

    }

    public boolean isTokenInUse(String warehouselicenseno, int wtoken) {
        BoundStatement stmt = isWarehouseTokenInUse.bind();

        stmt.setString("warehouselicenseno", warehouselicenseno);
        stmt.setInt("wtoken", wtoken);
        ResultSet rs = session.execute(stmt);
        boolean result = true;
        if (rs.isExhausted()) {
            result = false;
        }

        return result;

    }

    public WarehouseToken getToken(WarehouseTokenDTO warehouseTokenDTO) {
        BoundStatement stmt = getLotByToken.bind();

        stmt.setString("warehouselicenseno", warehouseTokenDTO.getWarehouselicenseno());
        stmt.setInt("wtoken", warehouseTokenDTO.getWtoken());

        ResultSet rs = session.execute(stmt);

        if (rs.isExhausted()) {
            return new WarehouseToken();
        }
        WarehouseToken warehouseToken = new WarehouseToken();
        for (Row row : rs) {

            warehouseToken.setWarehouselicenseno(row.getString("warehouselicenseno"));
            warehouseToken.setLotid(row.getUUID("lotid"));
            warehouseToken.setWtoken(row.getInt("wtoken"));

        }
        return warehouseToken;
    }

    public void save(WarehouseToken token) {
        mapper.save(token);
    }

    public void delete(WarehouseToken token) {
        BoundStatement stmt = deleteToken.bind();
        stmt.setString("warehouselicenseno", token.getWarehouselicenseno());
        stmt.setInt("wtoken", token.getWtoken());
        ResultSet rs = session.execute(stmt);

    }

    public List<WarehouseToken> getAllTokens(String warehouseLicenseNo) {
        BoundStatement stmt = getAllLots.bind();

        stmt.setString("warehouselicenseno", warehouseLicenseNo);
        ResultSet rs = session.execute(stmt);
        List<WarehouseToken> listWarehouseToken = new ArrayList<>();
        if (rs.isExhausted()) {
            return listWarehouseToken;
        }

        for (Row row : rs) {
            WarehouseToken warehouseToken = new WarehouseToken();
            warehouseToken.setWarehouselicenseno(row.getString("warehouselicenseno"));
            warehouseToken.setLotid(row.getUUID("lotid"));
            warehouseToken.setWtoken(row.getInt("wtoken"));
            listWarehouseToken.add(warehouseToken);
        }
        return listWarehouseToken;
    }

}
