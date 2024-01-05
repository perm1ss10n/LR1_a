package ru.tim.lr1.controllers;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tim.lr1.exceptions.AgentNotFoundException;
import ru.tim.lr1.exceptions.BranchNotFoundException;
import ru.tim.lr1.models.Agent;
import ru.tim.lr1.models.Branch;
import ru.tim.lr1.models.dto.BranchDTO;
import ru.tim.lr1.services.BranchService;

import java.util.List;

@RequestMapping("/api/branch")
@RestController
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping //Получение списка всех филиалов
    public ResponseEntity<List<Branch>> getBranches(){
        return branchService.getBranches()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping() //Добавление нового филиала
    public ResponseEntity<Branch> saveBranch(@RequestBody BranchDTO branchDTO) {
        return branchService.saveBranch(branchDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{branchId}") //Удаление филиала (скрытие просто)
    public ResponseEntity<Void> deleteBranch(@PathVariable Integer branchId) throws BranchNotFoundException {
        return ResponseEntity.status(branchService.deleteBranch(branchId)).build();
    }

    @GetMapping("/{branchId}") //Поиск филиала по ID
    public ResponseEntity<Branch> getBranchById(@PathVariable Integer branchId) throws BranchNotFoundException {
        return branchService.getBranchById(branchId)
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
