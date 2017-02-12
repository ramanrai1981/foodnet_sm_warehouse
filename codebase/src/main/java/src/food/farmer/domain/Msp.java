package src.food.farmer.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;

/**
 * A Farmer's Personal Detail.
 */
@Table(name = "msp")
public class Msp implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private String id;

    private String commoditycode;

    private int year;

    private String msprate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommoditycode() {
        return commoditycode;
    }

    public void setCommoditycode(String commoditycode) {
        this.commoditycode = commoditycode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMsprate() {
        return msprate;
    }

    public void setMsprate(String msprate) {
        this.msprate = msprate;
    }

}
