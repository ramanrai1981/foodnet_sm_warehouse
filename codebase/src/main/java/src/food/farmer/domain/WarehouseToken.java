package src.food.farmer.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * A Warehouse Entry
 */
@Table(name = "warehousetoken")
public class WarehouseToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private String warehouselicenseno;
    private UUID lotid;
    private int wtoken;

    public String getWarehouselicenseno() {
        return warehouselicenseno;
    }

    public void setWarehouselicenseno(String warehouselicenseno) {
        this.warehouselicenseno = warehouselicenseno;
    }

  

    public UUID getLotid() {
        return lotid;
    }

    public void setLotid(UUID lotid) {
        this.lotid = lotid;
    }

    public int getWtoken() {
        return wtoken;
    }

    public void setWtoken(int wtoken) {
        this.wtoken = wtoken;
    }

}
