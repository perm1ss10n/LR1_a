package ru.tim.lr1.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientDTO {
    private String fio;
    private String address;
    private String phoneNumber;
}
