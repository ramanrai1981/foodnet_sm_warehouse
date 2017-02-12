package src.food.farmer.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


/**
 * A DTO for the Quality entity.
 */
public class QualityDTO implements Serializable {

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

        QualityDTO qualityDTO = (QualityDTO) o;

        if ( ! Objects.equals(id, qualityDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "QualityDTO{" +
            "id=" + id +
            ", commodity_id='" + commodity_id + "'" +
            ", commodity_name='" + commodity_name + "'" +
            ", quality_param='" + quality_param + "'" +
            ", quality_max_value='" + quality_max_value + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
