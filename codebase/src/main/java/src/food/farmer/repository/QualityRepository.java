package src.food.farmer.repository;

import src.food.farmer.domain.Quality;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Cassandra repository for the Quality entity.
 */
@Repository
public class QualityRepository {

    @Inject
    private Session session;

    private Mapper<Quality> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;
    
    private PreparedStatement findQualityByCommodityIdStmt;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(Quality.class);
        findAllStmt = session.prepare("SELECT * FROM quality");
        truncateStmt = session.prepare("TRUNCATE quality");
        findQualityByCommodityIdStmt = session.prepare("SELECT * FROM quality "
				+ "WHERE commodity_id=:commodity_id allow filtering");
    }
    
        
    public List<Quality> findQuality(String commodity_id) {
    	List<Quality> qualities = new ArrayList<>();
    	BoundStatement stmt = findQualityByCommodityIdStmt.bind();
        stmt.setString("commodity_id", commodity_id);
        //BoundStatement stmt =  findQualityByCommodityIdStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Quality quality = new Quality();
                quality.setId(row.getUUID("id"));
                quality.setCommodity_id(row.getString("commodity_id"));
                quality.setCommodity_name(row.getString("commodity_name"));
                quality.setQuality_param(row.getString("quality_param"));
                quality.setQuality_max_value(row.getString("quality_max_value"));
                quality.setStatus(row.getString("status"));
                return quality;
            }
        ).forEach(qualities::add);
        return qualities;
    }
    

    public List<Quality> findAll() {
        List<Quality> qualities = new ArrayList<>();
        BoundStatement stmt =  findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Quality quality = new Quality();
                quality.setId(row.getUUID("id"));
                quality.setCommodity_id(row.getString("commodity_id"));
                quality.setCommodity_name(row.getString("commodity_name"));
                quality.setQuality_param(row.getString("quality_param"));
                quality.setQuality_max_value(row.getString("quality_max_value"));
                quality.setStatus(row.getString("status"));
                return quality;
            }
        ).forEach(qualities::add);
        return qualities;
    }

    public Quality findOne(UUID id) {
        return mapper.get(id);
    }

    public Quality save(Quality quality) {
        if (quality.getId() == null) {
            quality.setId(UUID.randomUUID());
        }
        mapper.save(quality);
        return quality;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt =  truncateStmt.bind();
        session.execute(stmt);
    }
}
