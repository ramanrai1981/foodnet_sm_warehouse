package src.food.farmer.repository;

import src.food.farmer.domain.Commodity;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Commodity entity.
 */
@Repository
public class CommodityRepository {

    @Inject
    private Session session;

    private Mapper<Commodity> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(Commodity.class);
        findAllStmt = session.prepare("SELECT * FROM commodity");
        truncateStmt = session.prepare("TRUNCATE commodity");
    }

    public List<Commodity> findAll() {
        List<Commodity> commodities = new ArrayList<>();
        BoundStatement stmt =  findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Commodity commodity = new Commodity();
                commodity.setId(row.getUUID("id"));
                commodity.setCommodity_name(row.getString("commodity_name"));
                commodity.setCommodity_status(row.getString("commodity_status"));
                commodity.setMinimum_sell_price(row.getFloat("minimum_sell_price"));
                commodity.setYear(row.getInt("year"));
                commodity.setSeason(row.getString("season"));
                return commodity;
            }
        ).forEach(commodities::add);
        return commodities;
    }

    public Commodity findOne(UUID id) {
        return mapper.get(id);
    }

    public Commodity save(Commodity commodity) {
        if (commodity.getId() == null) {
            commodity.setId(UUID.randomUUID());
        }
        mapper.save(commodity);
        return commodity;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt =  truncateStmt.bind();
        session.execute(stmt);
    }
}
