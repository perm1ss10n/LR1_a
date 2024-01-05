package ru.tim.lr1.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.tim.lr1.exceptions.BranchNotFoundException;
import ru.tim.lr1.models.Branch;
import ru.tim.lr1.models.dto.BranchDTO;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    Optional<List<Branch>> getBranches();
    Optional<Branch> saveBranch(BranchDTO branchDTO);
    HttpStatus deleteBranch(Integer branchId) throws BranchNotFoundException;
    Optional<Branch> getBranchById(Integer branchId) throws BranchNotFoundException;
}
