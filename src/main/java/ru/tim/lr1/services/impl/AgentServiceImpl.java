package ru.tim.lr1.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.tim.lr1.exceptions.AgentNotFoundException;
import ru.tim.lr1.models.*;
import ru.tim.lr1.models.dto.AgentDTO;
import ru.tim.lr1.repository.AgentRepository;
import ru.tim.lr1.repository.BranchRepository;
import ru.tim.lr1.repository.ContractRepository;
import ru.tim.lr1.repository.InsuranceTypeRepository;
import ru.tim.lr1.services.AgentService;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final BranchRepository branchRepository;
    private final ContractRepository contractRepository;
    private final InsuranceTypeRepository insuranceTypeRepository;

    @Override
    public Optional<List<Agent>> getAgents(){
        return agentRepository.findAllByIsActiveOrderById(Boolean.TRUE);
    }

    @Override //adding new agent
    public Optional<Agent> saveAgent(AgentDTO agentDTO){
        Branch branch = branchRepository.getReferenceById(agentDTO.getBranchId());
        Agent agent = new Agent(agentDTO, branch);
        agent = agentRepository.save(agent);
        return Optional.of(agent);
    }

    @Override
    public HttpStatus deleteAgent(Integer agentId) throws AgentNotFoundException{
        Optional<Agent> agent = agentRepository.findById(agentId);
        if(agent.isPresent()){
            agent.get().setIsActive(Boolean.FALSE);
            agentRepository.save(agent.get());
            return HttpStatus.OK;
        }
        throw new AgentNotFoundException(agentId);
    }

    @Override //Поиск клиента по номеру телефона
    public Optional<Agent> findByPhoneNumber(String phoneNumber){
        return Optional.of(agentRepository.findByPhoneNumber(phoneNumber));
    }

    @Override
    public Optional<Agent> getAgentById(Integer agentId) throws AgentNotFoundException{
        Optional<Agent> agent = agentRepository.findById(agentId);
        if (agent.isEmpty()) throw new AgentNotFoundException(agentId);
        return agent;
    }

    @Override
    public Optional<Float> calculatingSalaryForAgent(Integer agentId) throws AgentNotFoundException{
        Optional<Agent> agent = agentRepository.findById(agentId);
        if (agent.isEmpty()) throw new AgentNotFoundException(agentId);
        Optional<List<Contract>> contract = contractRepository.findAllByIsActiveAndAgentIdOrderById(Boolean.TRUE, agentId);
        if (contract.isPresent()){
            return Optional.of(contract.get().stream().map(c->{return c.getPayment()*c.getInsuranceType().getPercent();}).reduce(0f, Float::sum));
        }
        return Optional.empty();
    }


    //TODO: Апдейт данных агента
}
