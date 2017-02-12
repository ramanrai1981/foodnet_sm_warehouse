package src.food.farmer.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


/**
 * A DTO for the Marketcommittee entity.
 */
public class MarketcommitteeDTO implements Serializable {

    private UUID id;

    @NotNull
    @Size(min = 3, max = 154)
    private String market_committee_name;

    @NotNull
    @Size(min = 3, max = 154)
    private String market_committee_address;

    @NotNull
    @Size(min = 3, max = 99)
    private String market_committee_city;

    @NotNull
    @Size(min = 3, max = 99)
    private String market_committee_district;

    @NotNull
    @Size(min = 3, max = 99)
    private String market_committee_state;

    @NotNull
    @Size(min = 1, max = 1)
    private String status;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getMarket_committee_name() {
        return market_committee_name;
    }

    public void setMarket_committee_name(String market_committee_name) {
        this.market_committee_name = market_committee_name;
    }
    public String getMarket_committee_address() {
        return market_committee_address;
    }

    public void setMarket_committee_address(String market_committee_address) {
        this.market_committee_address = market_committee_address;
    }
    public String getMarket_committee_city() {
        return market_committee_city;
    }

    public void setMarket_committee_city(String market_committee_city) {
        this.market_committee_city = market_committee_city;
    }
    public String getMarket_committee_district() {
        return market_committee_district;
    }

    public void setMarket_committee_district(String market_committee_district) {
        this.market_committee_district = market_committee_district;
    }
    public String getMarket_committee_state() {
        return market_committee_state;
    }

    public void setMarket_committee_state(String market_committee_state) {
        this.market_committee_state = market_committee_state;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MarketcommitteeDTO marketcommitteeDTO = (MarketcommitteeDTO) o;

        if ( ! Objects.equals(id, marketcommitteeDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MarketcommitteeDTO{" +
            "id=" + id +
            ", market_committee_name='" + market_committee_name + "'" +
            ", market_committee_address='" + market_committee_address + "'" +
            ", market_committee_city='" + market_committee_city + "'" +
            ", market_committee_district='" + market_committee_district + "'" +
            ", market_committee_state='" + market_committee_state + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
