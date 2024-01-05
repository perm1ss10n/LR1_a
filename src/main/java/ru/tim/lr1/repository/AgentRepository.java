package ru.tim.lr1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tim.lr1.models.Agent;
import ru.tim.lr1.models.Client;

import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Integer>{
    Optional<List<Agent>> findAllByIsActiveOrderById(Boolean isActive);
    Agent findByPhoneNumber(String phoneNumber);
}
