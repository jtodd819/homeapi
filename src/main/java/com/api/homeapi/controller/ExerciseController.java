package com.api.homeapi.controller;

import com.api.homeapi.model.Exercise;
import com.api.homeapi.repository.ExerciseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Exercise getExerciseById(@PathVariable Long exerciseId) throws ResponseStatusException {
        return exerciseRepository.findById(exerciseId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Not Found:: " + exerciseId));
    }

    @GetMapping()
    public List<Exercise> getExercisesByUserId(@RequestParam(value="userId") Long userId) {
        return exerciseRepository.findByUserId(userId);
    }

    @PutMapping(value="/{exerciseId}")
    public void updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise) throws ResponseStatusException {
        exerciseRepository.updateById(exerciseId, exercise);
    }

    @PostMapping()
    public void createExercise(@RequestBody Exercise exercise) {
        exerciseRepository.save(exercise);
    }

}
