package ru.tim.lr1.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tim.lr1.models.Agent;
import ru.tim.lr1.models.Branch;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AgentDTO {
    private String fio;
    private String address;
    private String phoneNumber;
    private Integer branchId;

}
