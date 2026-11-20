package fr.fms.api_rest_spring.config;

import fr.fms.api_rest_spring.entities.Training;
import fr.fms.api_rest_spring.service.TrainingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TrainingService trainingService;

    public DataInitializer(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Override
    public void run(String ... args) throws Exception {

        if(trainingService.findAll().isEmpty()){

            trainingService.create(new Training(null,"Java", "Java Standard Edition", 3500, 10));
            trainingService.create(new Training(null,"Spring", "Création API REST", 3000, 12));
            trainingService.create(new Training(null, "Angular", "Devenez pro du front end", 4000, 20));
            trainingService.create(new Training(null,"Python", "Initiation au dev avec Python", 2500, 16));
        }
    }
}