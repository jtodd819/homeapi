package com.api.homeapi.controller;
import com.api.homeapi.model.Exercise;
import com.api.homeapi.repository.ExerciseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path="/exercises")
public class ExerciseController {
    @Autowired private ExerciseRepository exerciseRepository;

    @GetMapping(value="/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long exerciseId) throws ResponseStatusException {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Not Found:: " + exerciseId));
        return new ResponseEntity<Exercise>(exercise, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Exercise>> getExercisesByUserId(@RequestParam(value="userId") Long userId) {
        List<Exercise> exercises = exerciseRepository.findByUserId(userId);
        return new ResponseEntity<List<Exercise>>(exercises, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createExercise(@RequestBody Exercise exercise) {
        exerciseRepository.save(exercise);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping(value="/{exerciseId}")
    public ResponseEntity<Void> updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise) throws ResponseStatusException {
        Exercise currentExercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Not Found:: " + exerciseId));
        currentExercise.setName(exercise.getName());
        currentExercise.setMax(exercise.getMax());
        currentExercise.setIsWeighted(exercise.getIsWeighted());
        exerciseRepository.save(exercise);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping(value="/{exerciseId}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long exerciseId) throws ResponseStatusException {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Not Found:: " + exerciseId));
        exerciseRepository.delete(exercise);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
