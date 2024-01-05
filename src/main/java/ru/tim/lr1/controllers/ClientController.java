package ru.tim.lr1.controllers;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tim.lr1.exceptions.AgentNotFoundException;
import ru.tim.lr1.exceptions.ClientNotFoundException;
import ru.tim.lr1.models.Agent;
import ru.tim.lr1.models.Client;
import ru.tim.lr1.models.dto.ClientDTO;
import ru.tim.lr1.services.ClientService;

import java.util.List;

@RequestMapping("/api/client")
@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping //Получение списка всех клиентов
    public ResponseEntity<List<Client>> getClients(){
        return clientService.getClients()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping //Добавление нового агента
    public ResponseEntity<Client> saveClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/phone") //Поиск клиента по номеру телефона
    public ResponseEntity<Client> getClientByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber){
        return clientService.findByPhoneNumber(phoneNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/{clientId}") //Поиск клиента по ID
    public ResponseEntity<Client> getClientById(@PathVariable Integer clientId) throws ClientNotFoundException {
        return clientService.getClientById(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleJsonMappingException(Exception ex) {
        JSONObject errorResponse = new JSONObject();
        String[] name = ex.getClass().getName().split("\\.");
        errorResponse.put("error", name[name.length-1]);
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse.toString());
    }
}
