package ru.tim.lr1.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tim.lr1.exceptions.BranchNotFoundException;
import ru.tim.lr1.exceptions.ClientNotFoundException;
import ru.tim.lr1.models.Branch;
import ru.tim.lr1.models.Client;
import ru.tim.lr1.models.dto.ClientDTO;
import ru.tim.lr1.repository.ClientRepository;
import ru.tim.lr1.services.ClientService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override //Получение всех клиентов
    public Optional<List<Client>> getClients(){
        return Optional.of(clientRepository.findAll());
    }

    @Override //Сохранение нового клиента
    public Optional<Client> saveClient(ClientDTO clientDTO){
        Client client = new Client(clientDTO);
        client = clientRepository.save(client);
        return Optional.of(client);
    }
    @Override //Поиск клиента по номеру телефона
    public Optional<Client> findByPhoneNumber(String phoneNumber){
        return Optional.of(clientRepository.findByPhoneNumber(phoneNumber));
    }
    @Override //Поиск клиента по айдишнику
    public Optional<Client> getClientById(Integer clientId) throws ClientNotFoundException {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty()) throw new ClientNotFoundException(clientId);
        return client;
    }

}
