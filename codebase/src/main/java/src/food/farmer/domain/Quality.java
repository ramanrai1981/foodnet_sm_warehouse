package src.food.farmer.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Quality.
 */

@Table(name = "quality")
public class Quality implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private String commodity_id;

    @NotNull
    private String commodity_name;

    @NotNull
    private String quality_param;

    
    private String quality_max_value;

    @NotNull
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getQuality_param() {
        return quality_param;
    }

    public void setQuality_param(String quality_param) {
        this.quality_param = quality_param;
    }

    public String getQuality_max_value() {
        return quality_max_value;
    }

    public void setQuality_max_value(String quality_max_value) {
        this.quality_max_value = quality_max_value;
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
        Quality quality = (Quality) o;
        if(quality.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, quality.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Quality{" +
            "id=" + id +
            ", commodity_id='" + commodity_id + "'" +
            ", commodity_name='" + commodity_name + "'" +
            ", quality_param='" + quality_param + "'" +
            ", quality_max_value='" + quality_max_value + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
