package ru.tim.lr1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tim.lr1.models.InsuranceType;

public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Integer> {
}
