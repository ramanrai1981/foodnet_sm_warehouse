package src.food.farmer.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A Warehouse Entry
 */
@Table(name = "warehousecommodityquality")
public class WarehouseCommodityRecievedQuality implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID lotid;

    private String qualityparam;
    private String qualityvalue;
    private String byuser;
    private Date ondate;

    public UUID getLotid() {
        return lotid;
    }

    public void setLotid(UUID lotid) {
        this.lotid = lotid;
    }

    public String getQualityparam() {
        return qualityparam;
    }

    public void setQualityparam(String qualityparam) {
        this.qualityparam = qualityparam;
    }

    public String getQualityvalue() {
        return qualityvalue;
    }

    public void setQualityvalue(String qualityvalue) {
        this.qualityvalue = qualityvalue;
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
