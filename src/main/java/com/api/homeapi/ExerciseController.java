package com.api.homeapi;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ExerciseController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/exercise")
    public Exercise exercise(@RequestParam(value= "name") String name, @RequestParam(value="max") long max,
    @RequestParam(value="isWeighted") boolean isWeighted) {
        return new Exercise(counter.incrementAndGet(), name, max, isWeighted);
    }
}
