package hu.zeletrik.daily.exercises.application.service;

import java.util.List;

import hu.zeletrik.daily.exercises.application.domain.Exercise;
import hu.zeletrik.daily.exercises.application.domain.ExerciseDetails;
import hu.zeletrik.daily.exercises.application.domain.Workout;

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


    /**
     * Get the detailed exercise info based on the workout.
     *
     * @param workout enum value
     * @return the expected {@link ExerciseDetails}
     */
    ExerciseDetails getExercisesDetailsFor(Workout workout);

}
