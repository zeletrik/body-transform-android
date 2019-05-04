package hu.zeletrik.daily.exercises.application.service;

import java.util.List;

import hu.zeletrik.daily.exercises.application.domain.Exercise;

public interface WorkoutService {

    List<Exercise> getExercises();

}
