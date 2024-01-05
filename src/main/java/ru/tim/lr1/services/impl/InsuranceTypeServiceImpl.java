package ru.tim.lr1.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tim.lr1.exceptions.InsuranceTypeNotFoundException;
import ru.tim.lr1.models.InsuranceType;
import ru.tim.lr1.models.dto.InsuranceTypeDTO;
import ru.tim.lr1.models.dto.InsuranceTypeResponseDTO;
import ru.tim.lr1.repository.InsuranceTypeRepository;
import ru.tim.lr1.services.InsuranceTypeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsuranceTypeServiceImpl implements InsuranceTypeService {
    private final InsuranceTypeRepository insuranceTypeRepository;

    @Override
    public Optional<List<InsuranceType>> getInsurances(){
        return Optional.of(insuranceTypeRepository.findAll());
    }

    @Override
    public Optional<InsuranceType> saveInsurance(InsuranceTypeDTO insuranceTypeDTO){
        InsuranceType insuranceType = new InsuranceType(insuranceTypeDTO);
        insuranceType = insuranceTypeRepository.save(insuranceType);
        return Optional.of(insuranceType);
    }

    @Override //Поиск вида страхования по ID
    public Optional<InsuranceType> getInsuranceTypeById(Integer insuranceTypeId) throws InsuranceTypeNotFoundException {
        Optional<InsuranceType> insuranceType = insuranceTypeRepository.findById(insuranceTypeId);
        if (insuranceType.isEmpty()) throw new InsuranceTypeNotFoundException(insuranceTypeId);
        return insuranceType;
    }

    @Override
    public Optional<InsuranceTypeResponseDTO> updateInsuranceType(InsuranceTypeResponseDTO updateInsuranceType) throws InsuranceTypeNotFoundException{
        Optional<InsuranceType> insuranceType = insuranceTypeRepository.findById(updateInsuranceType.getId());
        if (insuranceType.isEmpty()) throw new InsuranceTypeNotFoundException(updateInsuranceType.getId());
        insuranceTypeRepository.save(new InsuranceType(updateInsuranceType, insuranceType.get()));
        insuranceTypeRepository.flush();
        return Optional.of(new InsuranceTypeResponseDTO(insuranceTypeRepository.findById(updateInsuranceType.getId()).get()));
    }

}
