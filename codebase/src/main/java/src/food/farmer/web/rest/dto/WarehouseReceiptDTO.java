package src.food.farmer.web.rest.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WarehouseReceiptDTO {

    private String whr;
    private List<UUID> lotid;
    private List<Integer> wtoken;
    private Date ondate;

    public List<UUID> getLotid() {
        return lotid;
    }

    public void setLotid(List<UUID> lotid) {
        this.lotid = lotid;
    }

    public String getWhr() {
        return whr;
    }

    public void setWhr(String whr) {
        this.whr = whr;
    }

    public List<Integer> getWtoken() {
        return wtoken;
    }

    public void setWtoken(List<Integer> wtoken) {
        this.wtoken = wtoken;
    }

    public Date getOndate() {
        return ondate;
    }

    public void setOndate(Date ondate) {
        this.ondate = ondate;
    }
}
