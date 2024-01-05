package ru.tim.lr1.controllers;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tim.lr1.exceptions.InsuranceTypeNotFoundException;
import ru.tim.lr1.models.InsuranceType;
import ru.tim.lr1.models.dto.InsuranceTypeDTO;
import ru.tim.lr1.models.dto.InsuranceTypeResponseDTO;
import ru.tim.lr1.services.InsuranceTypeService;

import java.util.List;

@RequestMapping("/api/insurance_type")
@RestController
@RequiredArgsConstructor
public class InsuranceTypeController {
    private final InsuranceTypeService insuranceTypeService;

    @GetMapping //Вывод всех видов страхования
    public ResponseEntity<List<InsuranceType>> getInsurances(){
        return insuranceTypeService.getInsurances()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping //Добавление нового вида страхования
    public ResponseEntity<InsuranceType> saveInsuranceType(@RequestBody InsuranceTypeDTO insuranceTypeDTO) {
        return insuranceTypeService.saveInsurance(insuranceTypeDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{insuranceTypeId}") //Поиск вида страхования по ID
    public ResponseEntity<InsuranceType> getInsuranceTypeById(@PathVariable Integer insuranceTypeId) throws InsuranceTypeNotFoundException {
        return insuranceTypeService.getInsuranceTypeById(insuranceTypeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PatchMapping
    public ResponseEntity<InsuranceTypeResponseDTO> updateInsuranceType(@RequestBody InsuranceTypeResponseDTO insuranceTypeResponseDTO) throws InsuranceTypeNotFoundException {
        return insuranceTypeService.updateInsuranceType(insuranceTypeResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
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
