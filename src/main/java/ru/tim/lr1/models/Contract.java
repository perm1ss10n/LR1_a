package ru.tim.lr1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tim.lr1.models.dto.AgentDTO;
import ru.tim.lr1.models.dto.ContractDTO;

import java.time.ZonedDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private ZonedDateTime date;
    private Float amount;
    private Float payment; //Страховой платёж
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_type_id", nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateEagerInitializer", "hibernateLazyInitializer"})
    private InsuranceType insuranceType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_id", nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateEagerInitializer", "hibernateLazyInitializer"})
    private Agent agent;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateEagerInitializer", "hibernateLazyInitializer"})
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateEagerInitializer", "hibernateLazyInitializer"})
    private Branch branch;
    private Boolean isActive;

    public Contract(ContractDTO contractDTO, InsuranceType insuranceType, Agent agent, Client client){
        this.client = client;
        this.agent = agent;
        this.amount = contractDTO.getAmount();
        this.insuranceType = insuranceType;
        this.payment = contractDTO.getAmount() * insuranceType.getPercent();
        this.branch = agent.getBranch();
        this.date = ZonedDateTime.now();
        this.isActive = Boolean.TRUE;
    }

}
