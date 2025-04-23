package com.example.FitnessTracker.TrainingPlan;

import com.example.FitnessTracker.Exercise.Exercise;
import com.example.FitnessTracker.Exercise.ExerciseResponseDTO;
import com.example.FitnessTracker.TrainingPlanExercise.TrainingPlanExercise;
import com.example.FitnessTracker.User.User;
import com.example.FitnessTracker.User.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingPlanService {

    private final TrainingPlanRepository trainingPlanRepository;
    private final TrainingPlanMapper trainingPlanMapper;
    private final UserRepository userRepository;

    public TrainingPlanService(TrainingPlanRepository trainingPlanRepository, TrainingPlanMapper trainingPlanMapper, UserRepository userRepository) {
        this.trainingPlanRepository = trainingPlanRepository;
        this.trainingPlanMapper = trainingPlanMapper;
        this.userRepository = userRepository;
    }

    public List<TrainingPlanResponseDTO> findAllTrainingPlans(){
        List<TrainingPlan> trainingPlans = trainingPlanRepository.findAll();

        if(trainingPlans.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no training plans in the database.");
        }

        return trainingPlans.stream().map(trainingPlanMapper::toTrainingPlanResponseDTO).toList();
    }

    public TrainingPlanResponseDTO findTrainingPlanById(Long trainingPlanId){
        TrainingPlan trainingPlan = trainingPlanRepository.findById(trainingPlanId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no existing training plan with ID: " + trainingPlanId));

        return trainingPlanMapper.toTrainingPlanResponseDTO(trainingPlan);
    }

    //    fetch all exercises for the given training plans ID
    public List<ExerciseResponseDTO> findExercisesByTrainingPlanId(Long trainingPlanId){
        TrainingPlan trainingPlan = trainingPlanRepository.findById(trainingPlanId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training plan with ID: " + trainingPlanId + " does not exist."));

        List<TrainingPlanExercise> trainingPlanExercises = trainingPlan.getExercises();

        return trainingPlanExercises.stream()
                .map(trainingPlanExercise -> {
                    Exercise exercise = trainingPlanExercise.getExercise();
                    return new ExerciseResponseDTO(
                            exercise.getName(),
                            exercise.getCategory(),
                            exercise.getDescription(),
                            exercise.getEquipment()
                    );
                })
                .collect(Collectors.toList());
    }

    public TrainingPlanResponseDTO createTrainingPlan(TrainingPlanDTO trainingPlanDTO){
        User user = userRepository.findById(trainingPlanDTO.userId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        TrainingPlan trainingPlan = trainingPlanMapper.toEntity(trainingPlanDTO,user);

        trainingPlanRepository.save(trainingPlan);

        return trainingPlanMapper.toTrainingPlanResponseDTO(trainingPlan);
    }

    public String deleteTrainingPlanById(Long trainingPlanId){
        TrainingPlan trainingPlan = trainingPlanRepository.findById(trainingPlanId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training plan with ID: " + trainingPlanId + " does not exist."));

        trainingPlan.getExercises().clear();

        trainingPlanRepository.deleteById(trainingPlanId);
        return "Training plan with ID: " + trainingPlanId + " was successfully deleted.";

    }

    public TrainingPlanResponseDTO updateTrainingPlanById(Long trainingPlanId, TrainingPlanDTO trainingPlanDTO){
        User user = userRepository.findById(trainingPlanDTO.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID: " + trainingPlanDTO.userId() + " does not exist."));

        TrainingPlan existingTrainingPlan = trainingPlanRepository.findById(trainingPlanId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training plan with ID: " + trainingPlanId + " does not exist."));

        if(trainingPlanDTO.name() != null) existingTrainingPlan.setName(trainingPlanDTO.name());
        if(trainingPlanDTO.description() != null) existingTrainingPlan.setDescription(trainingPlanDTO.description());
        if(trainingPlanDTO.durationWeeks() != null) existingTrainingPlan.setDurationWeeks(trainingPlanDTO.durationWeeks());
        if(trainingPlanDTO.userId()!= null) existingTrainingPlan.setUser(user);

        trainingPlanRepository.save(existingTrainingPlan);

        return trainingPlanMapper.toTrainingPlanResponseDTO(existingTrainingPlan);
    }
}
