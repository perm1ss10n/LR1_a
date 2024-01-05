package ru.tim.lr1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tim.lr1.models.Agent;
import ru.tim.lr1.models.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Optional<List<Branch>> findAllByIsActiveOrderById(Boolean isActive);
}
