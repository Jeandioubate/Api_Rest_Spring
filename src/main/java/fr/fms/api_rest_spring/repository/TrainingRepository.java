package fr.fms.api_rest_spring.repository;

import fr.fms.api_rest_spring.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long>{
}