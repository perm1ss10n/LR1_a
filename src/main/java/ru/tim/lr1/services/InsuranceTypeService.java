package ru.tim.lr1.services;

import ru.tim.lr1.exceptions.InsuranceTypeNotFoundException;
import ru.tim.lr1.models.InsuranceType;
import ru.tim.lr1.models.dto.InsuranceTypeDTO;
import ru.tim.lr1.models.dto.InsuranceTypeResponseDTO;

import java.util.List;
import java.util.Optional;

public interface InsuranceTypeService {
    Optional<List<InsuranceType>> getInsurances();
    Optional<InsuranceType> saveInsurance(InsuranceTypeDTO insuranceTypeDTO);
    Optional<InsuranceType> getInsuranceTypeById(Integer insuranceTypeId) throws InsuranceTypeNotFoundException;
    Optional<InsuranceTypeResponseDTO> updateInsuranceType(InsuranceTypeResponseDTO updateInsuranceType) throws InsuranceTypeNotFoundException;
}
