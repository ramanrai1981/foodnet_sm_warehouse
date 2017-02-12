package src.food.farmer.web.rest.dto;

/**
 * A DTO representing a user, with his authorities.
 */
public class MspCurrentYearDTO {

    private String id;
    private String commodityname;

    private int year;
    private String msprate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityname;
    }

    public void setCommodityName(String commodityname) {
        this.commodityname = commodityname;
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
