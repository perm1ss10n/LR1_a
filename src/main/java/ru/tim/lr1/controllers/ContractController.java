package ru.tim.lr1.controllers;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tim.lr1.exceptions.AgentNotFoundException;
import ru.tim.lr1.exceptions.ContractNotFoundException;
import ru.tim.lr1.models.Contract;
import ru.tim.lr1.models.dto.ContractDTO;
import ru.tim.lr1.services.ContractService;

import java.util.List;

@RequestMapping("/api/contract")
@RestController
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping //Вывод всех контрактов
    public ResponseEntity<List<Contract>> getContracts(){
        return contractService.getContracts()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping() //Добавление нового контракта
    public ResponseEntity<Contract> saveContract(@RequestBody ContractDTO contractDTO) {
        return contractService.saveContract(contractDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{contractId}") //Поиск контракта по ID
    public ResponseEntity<Contract> getContractById(@PathVariable Integer contractId) throws ContractNotFoundException {
        return contractService.getContractById(contractId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{contractId}") //Удаление (скрытие на самом деле) контракта
    public ResponseEntity<Void> deleteContract(@PathVariable Integer contractId) throws ContractNotFoundException {
        return ResponseEntity.status(contractService.deleteContract(contractId)).build();
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
