package src.food.farmer.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A Warehouse Weighment Entry
 */
@Table(name = "warehouseweighment")
public class WarehouseWeighment implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID lotid;

    private Double grossweight;
    private Double tareweight;
    private Double netweight;
    private Date grossweightdate;
    private Date tareweightdate;
    private Date netweightdate;
    private String byuser;

    public UUID getLotid() {
        return lotid;
    }

    public void setLotid(UUID lotid) {
        this.lotid = lotid;
    }

    public Double getGrossweight() {
        return grossweight;
    }

    public void setGrossweight(Double grossweight) {
        this.grossweight = grossweight;
    }

    public Double getTareweight() {
        return tareweight;
    }

    public void setTareweight(Double tareweight) {
        this.tareweight = tareweight;
    }

    public Double getNetweight() {
        return netweight;
    }

    public void setNetweight(Double netweight) {
        this.netweight = netweight;
    }

    public Date getGrossweightdate() {
        return grossweightdate;
    }

    public void setGrossweightdate(Date grossweightdate) {
        this.grossweightdate = grossweightdate;
    }

    public Date getTareweightdate() {
        return tareweightdate;
    }

    public void setTareweightdate(Date tareweightdate) {
        this.tareweightdate = tareweightdate;
    }

    public Date getNetweightdate() {
        return netweightdate;
    }

    public void setNetweightdate(Date netweightdate) {
        this.netweightdate = netweightdate;
    }

    public String getByuser() {
        return byuser;
    }

    public void setByuser(String byuser) {
        this.byuser = byuser;
    }

}
