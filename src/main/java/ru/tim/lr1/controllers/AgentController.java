package ru.tim.lr1.controllers;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tim.lr1.exceptions.AgentNotFoundException;
import ru.tim.lr1.models.Agent;
import ru.tim.lr1.models.Contract;
import ru.tim.lr1.models.dto.AgentDTO;
import ru.tim.lr1.services.AgentService;

import java.util.List;

@RequestMapping("/api/agent")
@RestController
@RequiredArgsConstructor
public class AgentController {
    private final AgentService agentService;

    @GetMapping //Получение всех агентов
    public ResponseEntity<List<Agent>> getAgents(){
        return agentService.getAgents()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping //Добавление нового агента
    public ResponseEntity<Agent> saveAgent(@RequestBody AgentDTO agentDTO) {
        return agentService.saveAgent(agentDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{agentId}") //Удаление (скрытие на самом деле) агента
    public ResponseEntity<Void> deleteAgent(@PathVariable Integer agentId) throws AgentNotFoundException {
        return ResponseEntity.status(agentService.deleteAgent(agentId)).build();
    }

    @GetMapping("/phone") //Поиск агента по номеру телефона
    public ResponseEntity<Agent> getAgentByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber){
        return agentService.findByPhoneNumber(phoneNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/{agentId}") //Поиск агента по ID
    public ResponseEntity<Agent> getAgentById(@PathVariable Integer agentId) throws AgentNotFoundException {
        return agentService.getAgentById(agentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/salary")
    public ResponseEntity<Float> calculatingSalaryForAgent(@RequestParam(name = "agentId") Integer agentId) throws AgentNotFoundException{
        return agentService.calculatingSalaryForAgent(agentId)
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
