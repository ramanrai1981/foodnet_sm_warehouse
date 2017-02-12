package src.food.farmer.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A Warehouse Entry
 */
@Table(name = "warehouselotstack")
public class WarehouseLotStack implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;
    private UUID lotid;
    private UUID stackid;
    private int bags;
    private Date ondate;
    private String byuser;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getLotid() {
        return lotid;
    }

    public void setLotid(UUID lotid) {
        this.lotid = lotid;
    }

    public UUID getStackid() {
        return stackid;
    }

    public void setStackid(UUID stackid) {
        this.stackid = stackid;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }

    public Date getOndate() {
        return ondate;
    }

    public void setOndate(Date ondate) {
        this.ondate = ondate;
    }

    public String getByuser() {
        return byuser;
    }

    public void setByuser(String byuser) {
        this.byuser = byuser;
    }

   

}
