package ru.tim.lr1.services;

import org.springframework.http.HttpStatus;
import ru.tim.lr1.exceptions.AgentNotFoundException;
import ru.tim.lr1.models.Agent;
import ru.tim.lr1.models.dto.AgentDTO;

import java.util.List;
import java.util.Optional;

public interface AgentService {
    Optional<List<Agent>> getAgents();
    Optional<Agent> saveAgent(AgentDTO agentDTO);
    HttpStatus deleteAgent(Integer agentId) throws AgentNotFoundException;
    Optional<Agent> findByPhoneNumber(String phoneNumber);
    Optional<Float> calculatingSalaryForAgent(Integer agentId) throws AgentNotFoundException;
    Optional<Agent> getAgentById(Integer agentId) throws AgentNotFoundException;
}
