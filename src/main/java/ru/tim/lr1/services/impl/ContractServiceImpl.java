package ru.tim.lr1.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.tim.lr1.exceptions.BranchNotFoundException;
import ru.tim.lr1.exceptions.ClientNotFoundException;
import ru.tim.lr1.exceptions.ContractNotFoundException;
import ru.tim.lr1.models.*;
import ru.tim.lr1.models.dto.ContractDTO;
import ru.tim.lr1.repository.*;
import ru.tim.lr1.services.ContractService;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final AgentRepository agentRepository;
    private final InsuranceTypeRepository insuranceTypeRepository;
    private final ClientRepository clientRepository;

    @Override //Получение списка всех контрактов
    public Optional<List<Contract>> getContracts(){
        return contractRepository.findAllByIsActiveOrderById(Boolean.TRUE);
    }

    @Override //Сохранение нового контракта
    public Optional<Contract> saveContract(ContractDTO contractDTO){
        Agent agent = agentRepository.getReferenceById(contractDTO.getAgentId());
        InsuranceType insuranceType = insuranceTypeRepository.getReferenceById(contractDTO.getInsuranceTypeId());
        Client client = clientRepository.getReferenceById(contractDTO.getClientId());
        Contract contract = new Contract(contractDTO, insuranceType, agent, client);
        contract = contractRepository.save(contract);
        return Optional.of(contract);
    }

    @Override
    public HttpStatus deleteContract(Integer contractId) throws ContractNotFoundException {
        Optional<Contract> contract = contractRepository.findById(contractId);
        if(contract.isPresent()){
            contract.get().setIsActive(Boolean.FALSE);
            contractRepository.save(contract.get());
            return HttpStatus.OK;
        }
        throw new ContractNotFoundException(contractId);
    }

    @Override //Поиск контракта по ID
    public Optional<Contract> getContractById(Integer contractId) throws ContractNotFoundException {
        Optional<Contract> contract = contractRepository.findById(contractId);
        if (contract.isEmpty()) throw new ContractNotFoundException(contractId);
        return contract;
    }
}
