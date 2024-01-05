package ru.tim.lr1.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ru.tim.lr1.exceptions.AgentNotFoundException;
import ru.tim.lr1.exceptions.BranchNotFoundException;
import ru.tim.lr1.models.Agent;
import ru.tim.lr1.models.Branch;
import ru.tim.lr1.models.InsuranceType;
import ru.tim.lr1.models.dto.BranchDTO;
import ru.tim.lr1.models.dto.InsuranceTypeDTO;
import ru.tim.lr1.repository.BranchRepository;
import ru.tim.lr1.services.BranchService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;

    @Override
    public Optional<List<Branch>> getBranches(){
        return branchRepository.findAllByIsActiveOrderById(Boolean.TRUE);
    }

    @Override
    public Optional<Branch> saveBranch(BranchDTO branchDTO){
        Branch branch = new Branch(branchDTO);
        branch = branchRepository.save(branch);
        return Optional.of(branch);
    }

    @Override
    public HttpStatus deleteBranch(Integer branchId) throws BranchNotFoundException {
        Optional<Branch> branch = branchRepository.findById(branchId);
        if(branch.isPresent()){
            branch.get().setIsActive(Boolean.FALSE);
            branchRepository.save(branch.get());
            return HttpStatus.OK;
        }
        throw new BranchNotFoundException(branchId);
    }

    @Override
    public Optional<Branch> getBranchById(Integer branchId) throws BranchNotFoundException{
        Optional<Branch> branch = branchRepository.findById(branchId);
        if (branch.isEmpty()) throw new BranchNotFoundException(branchId);
        return branch;
    }

    //TODO: Обновление данных филиала

}
