package ru.tim.lr1.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tim.lr1.models.InsuranceType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InsuranceTypeResponseDTO {
    private Integer id;
    private String name;
    private Float rate;
    private Float percent;
    private Boolean isActive;

    public InsuranceTypeResponseDTO(InsuranceType insuranceType) {
        this.id = insuranceType.getId();
        this.name = insuranceType.getName();
        this.rate = insuranceType.getRate();
        this.percent = insuranceType.getPercent();
        this.isActive = insuranceType.getIsActive();
    }
}
