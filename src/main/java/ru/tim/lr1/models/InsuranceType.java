package ru.tim.lr1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tim.lr1.models.dto.InsuranceTypeDTO;
import ru.tim.lr1.models.dto.InsuranceTypeResponseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="insurance_type")
public class InsuranceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Float rate; //Тарифная ставка для расчёта платёжки клиента
    private Float percent; //Процент для расчёта зарплаты агента
    private Boolean isActive;

    public InsuranceType(InsuranceTypeDTO insuranceTypeDTO){
        this.name = insuranceTypeDTO.getName();
        this.rate = insuranceTypeDTO.getRate();
        this.percent = insuranceTypeDTO.getPercent();
        this.isActive = Boolean.TRUE;
    }

    public InsuranceType(InsuranceTypeResponseDTO dto, InsuranceType insuranceType){
        this.id = dto.getId();
        this.name = dto.getName() == null ? insuranceType.getName() : dto.getName();
        this.percent = dto.getPercent() == null ? insuranceType.getPercent() : dto.getPercent();
        this.rate = dto.getRate() == null ? insuranceType.getRate() : dto.getRate();
        this.isActive = insuranceType.isActive == Boolean.FALSE ? insuranceType.getIsActive() : dto.getIsActive();
    }

}
