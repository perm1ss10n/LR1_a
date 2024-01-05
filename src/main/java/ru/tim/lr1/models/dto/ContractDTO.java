package ru.tim.lr1.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tim.lr1.models.Agent;
import ru.tim.lr1.models.Client;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContractDTO {
    private Integer clientId;
    private Integer agentId;
    private Float amount;
    private Float payment;
    private Integer insuranceTypeId;
}
