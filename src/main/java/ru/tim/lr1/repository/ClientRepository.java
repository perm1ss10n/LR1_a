package ru.tim.lr1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tim.lr1.models.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByPhoneNumber(String phoneNumber);
}
