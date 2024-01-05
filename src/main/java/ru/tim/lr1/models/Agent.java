package ru.tim.lr1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tim.lr1.models.dto.AgentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fio;
    private String address;
    private String phoneNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateEagerInitializer", "hibernateLazyInitializer"})
    private Branch branch;
    private Boolean isActive;

    public Agent(AgentDTO agentDTO, Branch branch){
        this.fio = agentDTO.getFio();
        this.address = agentDTO.getAddress();
        this.phoneNumber = agentDTO.getPhoneNumber();
        this.branch = branch;
        this.isActive = Boolean.TRUE;
    }
}
