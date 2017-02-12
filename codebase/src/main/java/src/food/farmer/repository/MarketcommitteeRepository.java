package src.food.farmer.repository;

import src.food.farmer.domain.Marketcommittee;

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
 * Cassandra repository for the Marketcommittee entity.
 */
@Repository
public class MarketcommitteeRepository {

    @Inject
    private Session session;

    private Mapper<Marketcommittee> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(Marketcommittee.class);
        findAllStmt = session.prepare("SELECT * FROM marketcommittee");
        truncateStmt = session.prepare("TRUNCATE marketcommittee");
    }

    public List<Marketcommittee> findAll() {
        List<Marketcommittee> marketcommittees = new ArrayList<>();
        BoundStatement stmt =  findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Marketcommittee marketcommittee = new Marketcommittee();
                marketcommittee.setId(row.getUUID("id"));
                marketcommittee.setMarket_committee_name(row.getString("market_committee_name"));
                marketcommittee.setMarket_committee_address(row.getString("market_committee_address"));
                marketcommittee.setMarket_committee_city(row.getString("market_committee_city"));
                marketcommittee.setMarket_committee_district(row.getString("market_committee_district"));
                marketcommittee.setMarket_committee_state(row.getString("market_committee_state"));
                marketcommittee.setStatus(row.getString("status"));
                return marketcommittee;
            }
        ).forEach(marketcommittees::add);
        return marketcommittees;
    }

    public Marketcommittee findOne(UUID id) {
        return mapper.get(id);
    }

    public Marketcommittee save(Marketcommittee marketcommittee) {
        if (marketcommittee.getId() == null) {
            marketcommittee.setId(UUID.randomUUID());
        }
        mapper.save(marketcommittee);
        return marketcommittee;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt =  truncateStmt.bind();
        session.execute(stmt);
    }
}
