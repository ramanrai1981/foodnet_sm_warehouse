package src.food.farmer.web.rest.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO representing a user, with his authorities.
 */
public class MspDTO {

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
