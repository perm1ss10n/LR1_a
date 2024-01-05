package ru.tim.lr1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tim.lr1.models.dto.BranchDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private Boolean isActive;

    public Branch(BranchDTO branchDTO){
        this.name = branchDTO.getName();
        this.address = branchDTO.getAddress();
        this.phoneNumber = branchDTO.getPhoneNumber();
        this.isActive = Boolean.TRUE;
    }

}
