package hu.zeletrik.daily.exercises.application.service;

import java.util.List;

import hu.zeletrik.daily.exercises.application.domain.Exercise;

public interface WorkoutService {

    /**
     * Get the exercises for workout.
     *
     * @return {@link Exercise} list
     */
    List<Exercise> getWorkoutExercises();

    /**
     * Get the exercises without duplication.
     *
     * @return {@link Exercise} list
     */
    List<Exercise> getBaseExercises();

}
