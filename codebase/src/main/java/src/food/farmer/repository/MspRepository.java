package src.food.farmer.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import java.util.ArrayList;

import com.datastax.driver.mapping.MappingManager;
import java.util.List;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import src.food.farmer.domain.Msp;

/**
 * Cassandra repository for the Farmer's Personal Details entity.
 */
@Repository
public class MspRepository {

    @Inject
    private Session session;

    private Mapper<Msp> mapper;
    private PreparedStatement findByMobileStmt;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(Msp.class);
        findByMobileStmt = session.prepare("SELECT * FROM msp WHERE year=:year ALLOW FILTERING");
    }

    public Msp getMspById(String id) {

        return mapper.get(id);
    }

    public List<Msp> getMspYearWise(int year) {
        BoundStatement stmt = findByMobileStmt.bind();
        stmt.setInt("year", year);
        ResultSet rs = session.execute(stmt);
        List<Msp> mspList = new ArrayList<Msp>();
         if (rs.isExhausted()) {
            return mspList;
        }
        for (Row row : rs) {
            Msp msp = new Msp();
            msp.setCommoditycode(row.getString("commoditycode"));
            msp.setId(row.getString("id"));
            msp.setMsprate(row.getString("msprate"));
            msp.setYear(row.getInt("year"));
            mspList.add(msp);
        }
        return mspList;
    }

   

}
