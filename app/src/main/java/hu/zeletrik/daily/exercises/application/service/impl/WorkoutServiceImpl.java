package hu.zeletrik.daily.exercises.application.service.impl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.google.common.collect.ImmutableList;

import java.util.List;

import hu.zeletrik.daily.exercises.R;
import hu.zeletrik.daily.exercises.application.domain.Exercise;
import hu.zeletrik.daily.exercises.application.domain.Workout;
import hu.zeletrik.daily.exercises.application.service.WorkoutService;

public class WorkoutServiceImpl implements WorkoutService {

    private final Context context;
    private final Resources.Theme theme = null;


    public WorkoutServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<Exercise> getWorkoutExercises() {
        return ImmutableList.of(
                createExercisesFrom(Workout.WARM_UP),
                createExercisesFrom(Workout.HEISMAN),
                createExercisesFrom(Workout.REST),
                createExercisesFrom(Workout.BURPEE),
                createExercisesFrom(Workout.REST),
                createExercisesFrom(Workout.JUMP_JACK),
                createExercisesFrom(Workout.REST),
                createExercisesFrom(Workout.MOUNTAIN_CLIMBER),
                createExercisesFrom(Workout.REST),
                createExercisesFrom(Workout.LYING_HIP_RISE),
                createExercisesFrom(Workout.REST),
                createExercisesFrom(Workout.RUN_IN_PLACE)
        );
    }
    @Override
    public List<Exercise> getBaseExercises() {
        return ImmutableList.of(
                createExercisesFrom(Workout.REST),
                createExercisesFrom(Workout.WARM_UP),
                createExercisesFrom(Workout.HEISMAN),
                createExercisesFrom(Workout.BURPEE),
                createExercisesFrom(Workout.JUMP_JACK),
                createExercisesFrom(Workout.MOUNTAIN_CLIMBER),
                createExercisesFrom(Workout.LYING_HIP_RISE),
                createExercisesFrom(Workout.RUN_IN_PLACE)
        );
    }

    private Exercise createExercisesFrom(Workout workout) {
        return new Exercise(workout, workout.getDuration(), getDrawableFor(workout));
    }

    private Drawable getDrawableFor(Workout workout) {
        Drawable result;
        switch (workout) {
            case WARM_UP:
            case REST:
            case BURPEE:
            case HEISMAN:
                result = context.getResources().getDrawable(R.drawable.wo_heisman, theme);
                break;
            case JUMP_JACK:
            case MOUNTAIN_CLIMBER:
            case LYING_HIP_RISE:
            case RUN_IN_PLACE:
            default:
                result = context.getResources().getDrawable(R.drawable.wo_heisman, theme);
                break;
        }
        return result;
    }
}
