package src.food.farmer.web.rest.dto;

import java.util.UUID;

/**
 *
 * @author sumit.garg
 */
public class WarehouseTokenDTO {

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
