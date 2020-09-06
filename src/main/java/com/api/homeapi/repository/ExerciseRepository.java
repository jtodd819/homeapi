package com.api.homeapi.repository;
import java.util.List;
import com.api.homeapi.model.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long>{
    List<Exercise> findByUserId(long userId);
}
