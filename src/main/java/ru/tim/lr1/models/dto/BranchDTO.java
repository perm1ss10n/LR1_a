package ru.tim.lr1.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BranchDTO {
    private String name;
    private String address;
    private String phoneNumber;
}
