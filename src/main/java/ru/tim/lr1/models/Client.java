package ru.tim.lr1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tim.lr1.models.dto.ClientDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; //Можно UUID использовать
    private String fio;
    private String address;
    private String phoneNumber;

    public Client(ClientDTO clientDTO){
        this.fio = clientDTO.getFio();
        this.address = clientDTO.getAddress();
        this.phoneNumber = clientDTO.getPhoneNumber();
    }

}
