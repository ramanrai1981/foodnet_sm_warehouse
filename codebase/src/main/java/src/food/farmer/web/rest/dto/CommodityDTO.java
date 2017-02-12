package src.food.farmer.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


/**
 * A DTO for the Commodity entity.
 */
public class CommodityDTO implements Serializable {

    private UUID id;

    @NotNull
    @Size(min = 3, max = 154)
    private String commodity_name;

    @NotNull
    @Size(min = 1, max = 1)
    private String commodity_status;

    @NotNull
    private Float minimum_sell_price;

    @NotNull
    private Integer year;

    @NotNull
    @Size(min = 3, max = 128)
    private String season;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }
    public String getCommodity_status() {
        return commodity_status;
    }

    public void setCommodity_status(String commodity_status) {
        this.commodity_status = commodity_status;
    }
    public Float getMinimum_sell_price() {
        return minimum_sell_price;
    }

    public void setMinimum_sell_price(Float minimum_sell_price) {
        this.minimum_sell_price = minimum_sell_price;
    }
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommodityDTO commodityDTO = (CommodityDTO) o;

        if ( ! Objects.equals(id, commodityDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CommodityDTO{" +
            "id=" + id +
            ", commodity_name='" + commodity_name + "'" +
            ", commodity_status='" + commodity_status + "'" +
            ", minimum_sell_price='" + minimum_sell_price + "'" +
            ", year='" + year + "'" +
            ", season='" + season + "'" +
            '}';
    }
}
