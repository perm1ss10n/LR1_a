package ru.tim.lr1.services;

import org.springframework.stereotype.Service;
import ru.tim.lr1.exceptions.ClientNotFoundException;
import ru.tim.lr1.models.Client;
import ru.tim.lr1.models.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<List<Client>> getClients();
    Optional<Client> saveClient(ClientDTO clientDTO);
    Optional<Client> findByPhoneNumber(String phoneNumber);
    Optional<Client> getClientById(Integer clientId) throws ClientNotFoundException;
}
