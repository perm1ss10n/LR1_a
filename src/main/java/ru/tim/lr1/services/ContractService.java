package ru.tim.lr1.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.tim.lr1.exceptions.ContractNotFoundException;
import ru.tim.lr1.models.Contract;
import ru.tim.lr1.models.dto.ContractDTO;

import java.util.List;
import java.util.Optional;


public interface ContractService {
    Optional<List<Contract>> getContracts();
    Optional<Contract> saveContract(ContractDTO contractDTO);
    HttpStatus deleteContract(Integer contractId) throws ContractNotFoundException;
    Optional<Contract> getContractById(Integer contractId) throws ContractNotFoundException;
}
