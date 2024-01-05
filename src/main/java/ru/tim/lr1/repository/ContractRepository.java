package ru.tim.lr1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tim.lr1.models.Branch;
import ru.tim.lr1.models.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Optional<List<Contract>> findAllByIsActiveOrderById(Boolean isActive);
    Optional<List<Contract>> findAllByIsActiveAndAgentIdOrderById(Boolean isActive, Integer agentId);
}
