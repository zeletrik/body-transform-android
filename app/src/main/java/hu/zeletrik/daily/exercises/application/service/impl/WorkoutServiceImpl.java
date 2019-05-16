package hu.zeletrik.daily.exercises.application.service.impl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.google.common.collect.ImmutableList;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import hu.zeletrik.daily.exercises.R;
import hu.zeletrik.daily.exercises.application.domain.Exercise;
import hu.zeletrik.daily.exercises.application.domain.ExerciseDetails;
import hu.zeletrik.daily.exercises.application.domain.Workout;
import hu.zeletrik.daily.exercises.application.service.WorkoutService;

import static java.util.Objects.nonNull;

public class WorkoutServiceImpl implements WorkoutService {

    private final Context context;
    private final Resources.Theme theme = null;


    public WorkoutServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<Exercise> getWorkoutExercises() {
        return ImmutableList.of(
                buildExercisesFrom(Workout.WARM_UP),
                buildExercisesFrom(Workout.HEISMAN),
                buildExercisesFrom(Workout.REST),
                buildExercisesFrom(Workout.BURPEE),
                buildExercisesFrom(Workout.REST),
                buildExercisesFrom(Workout.JUMP_JACK),
                buildExercisesFrom(Workout.REST),
                buildExercisesFrom(Workout.MOUNTAIN_CLIMBER),
                buildExercisesFrom(Workout.REST),
                buildExercisesFrom(Workout.LYING_HIP_RISE),
                buildExercisesFrom(Workout.REST),
                buildExercisesFrom(Workout.RUN_IN_PLACE)
        );
    }

    @Override
    public List<Exercise> getBaseExercises() {
        return ImmutableList.of(
                buildExercisesFrom(Workout.REST),
                buildExercisesFrom(Workout.WARM_UP),
                buildExercisesFrom(Workout.HEISMAN),
                buildExercisesFrom(Workout.BURPEE),
                buildExercisesFrom(Workout.JUMP_JACK),
                buildExercisesFrom(Workout.MOUNTAIN_CLIMBER),
                buildExercisesFrom(Workout.LYING_HIP_RISE),
                buildExercisesFrom(Workout.RUN_IN_PLACE)
        );
    }

    @Override
    public ExerciseDetails getExercisesDetailsFor(Workout workout) {
        ExerciseDetails.ExerciseDetailsBuilder resultBuilder = ExerciseDetails.builder();
        Exercise exercise;
        if (nonNull(workout)) {
            exercise = buildExercisesFrom(workout);
        } else {
            exercise = buildExercisesFrom(Workout.OTHER);
        }
        resultBuilder.exercise(exercise);
        switch (workout) {
            case WARM_UP:
                resultBuilder
                        .benefits(context.getResources().getString(R.string.warm_up__benefit))
                        .instruction(context.getResources().getString(R.string.warm_up__instruction))
                        .muscle(context.getResources().getString(R.string.warm_up__muscle));
                break;
            case REST:
                resultBuilder
                        .benefits(context.getResources().getString(R.string.rest_benefit))
                        .instruction(context.getResources().getString(R.string.rest_instruction))
                        .muscle(context.getResources().getString(R.string.rest_muscle));
                break;
            case BURPEE:
                resultBuilder
                        .benefits(context.getResources().getString(R.string.burpee_benefit))
                        .instruction(context.getResources().getString(R.string.burpee_instruction))
                        .muscle(context.getResources().getString(R.string.burpee_muscle));
                break;
            case HEISMAN:
                resultBuilder
                        .benefits(context.getResources().getString(R.string.heisman_benefit))
                        .instruction(context.getResources().getString(R.string.heisman_instruction))
                        .muscle(context.getResources().getString(R.string.heisman_muscle));
                break;
            case JUMP_JACK:
                resultBuilder
                        .benefits(context.getResources().getString(R.string.jumping_jack_benefit))
                        .instruction(context.getResources().getString(R.string.jumping_jack_instruction))
                        .muscle(context.getResources().getString(R.string.jumping_jack_muscle));
                break;
            case MOUNTAIN_CLIMBER:
                resultBuilder
                        .benefits(context.getResources().getString(R.string.mountain_climber_benefit))
                        .instruction(context.getResources().getString(R.string.mountain_climber_instruction))
                        .muscle(context.getResources().getString(R.string.mountain_climber_muscle));
                break;
            case LYING_HIP_RISE:
                resultBuilder
                        .benefits(context.getResources().getString(R.string.lying_hip_rise_benefit))
                        .instruction(context.getResources().getString(R.string.lying_hip_rise_instruction))
                        .muscle(context.getResources().getString(R.string.lying_hip_rise_muscle));
                break;
            case RUN_IN_PLACE:
                resultBuilder
                        .benefits(context.getResources().getString(R.string.run_in_place_benefit))
                        .instruction(context.getResources().getString(R.string.run_in_place_instruction))
                        .muscle(context.getResources().getString(R.string.run_in_place_muscle));
                break;
            default:
                resultBuilder
                        .benefits(StringUtils.EMPTY)
                        .instruction(StringUtils.EMPTY)
                        .muscle(StringUtils.EMPTY);
                break;
        }
        return resultBuilder.build();
    }

    private Exercise buildExercisesFrom(Workout workout) {
        return Exercise.builder()
                .workout(workout)
                .baseDuration(workout.getDuration())
                .currentDuration(workout.getDuration()) // TODO: Current duration based on shared pref details
                .icon(getDrawableFor(workout))
                .build();
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
