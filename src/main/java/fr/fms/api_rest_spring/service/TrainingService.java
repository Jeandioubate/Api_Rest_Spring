package fr.fms.api_rest_spring.service;

import fr.fms.api_rest_spring.entities.Training;
import fr.fms.api_rest_spring.repository.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    public Optional<Training> findById(Long id) {
        return trainingRepository.findById(id);
    }

    @Transactional
    public Training create(Training training) {
        return trainingRepository.save(training);
    }

    @Transactional
    public Optional<Training> update(Long id, Training updatedTraining) {
        return trainingRepository.findById(id)
                .map(currentTraining -> {
                    currentTraining.setName(updatedTraining.getName());
                    currentTraining.setDescription(updatedTraining.getDescription());
                    currentTraining.setPrice(updatedTraining.getPrice());
                    currentTraining.setQuantity(updatedTraining.getQuantity());

                    return trainingRepository.save(currentTraining);
                });
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
            return true;
        }
        else return false;
    }


}