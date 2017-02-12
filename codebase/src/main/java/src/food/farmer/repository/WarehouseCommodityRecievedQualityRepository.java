package src.food.farmer.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import src.food.farmer.domain.WarehouseCommodityRecievedQuality;

/**
 *
 */
@Repository
public class WarehouseCommodityRecievedQualityRepository {

    @Inject
    private Session session;

    private Mapper<WarehouseCommodityRecievedQuality> mapper;
    private PreparedStatement getLotQualityPS;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(WarehouseCommodityRecievedQuality.class);
        getLotQualityPS = session.prepare("SELECT * FROM warehousecommodityquality WHERE lotid=:lotid  ALLOW FILTERING");
    }

    public void saveWarehouseCommodityRecievedQuality(WarehouseCommodityRecievedQuality warehouseCommodityRecievedQuality) {
        mapper.save(warehouseCommodityRecievedQuality);
    }

    public List<WarehouseCommodityRecievedQuality> getLotQuality(UUID lotid) {
        BoundStatement stmt = getLotQualityPS.bind();
        stmt.setUUID("lotid", lotid);
        ResultSet rs = session.execute(stmt);
        List<WarehouseCommodityRecievedQuality> listLotQuality = new ArrayList<>();
        if (rs.isExhausted()) {
            return listLotQuality;
        }

        for (Row row : rs) {
            WarehouseCommodityRecievedQuality lotQuality = new WarehouseCommodityRecievedQuality();
            lotQuality.setLotid(row.getUUID("lotid"));
            lotQuality.setQualityparam(row.getString("qualityparam"));
            lotQuality.setQualityvalue(row.getString("qualityvalue"));
            lotQuality.setByuser(row.getString("byuser"));
            lotQuality.setOndate(row.getTimestamp("ondate"));
            listLotQuality.add(lotQuality);
        }
        return listLotQuality;
    }

}
