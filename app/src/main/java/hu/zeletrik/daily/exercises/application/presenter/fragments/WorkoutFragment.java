package hu.zeletrik.daily.exercises.application.presenter.fragments;

/**
 * Created by zeletrik on 2018-04-19.
 */

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import hu.zeletrik.daily.exercises.R;
import hu.zeletrik.daily.exercises.application.domain.Exercise;
import hu.zeletrik.daily.exercises.application.domain.Workout;
import hu.zeletrik.daily.exercises.application.service.WorkoutService;
import hu.zeletrik.daily.exercises.application.service.impl.WorkoutServiceImpl;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class WorkoutFragment extends Fragment {


    private WorkoutService workoutService;
    private List<Exercise> exercises;
    private MediaPlayer mp;
    private TextView progressInfo;
    private TextView currentExerciseText;
    private TextView nextExerciseText;
    private TextView nextExerciseTime;
    private ProgressBar progressBar;
    private ImageView exerciseImage;
    private CountDownTimer timer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutService = new WorkoutServiceImpl(getContext());

        exercises = workoutService.getWorkoutExercises();

        View rootView = inflater.inflate(R.layout.fragment_workout, container, false);

        progressInfo = rootView.findViewById(R.id.txtProgress);
        progressBar = rootView.findViewById(R.id.progressBar);
        currentExerciseText = rootView.findViewById(R.id.currentExercise);
        nextExerciseText = rootView.findViewById(R.id.nextExercise);
        nextExerciseTime = rootView.findViewById(R.id.nextExerciseTime);
        exerciseImage = rootView.findViewById(R.id.exerciseImage);

        startWorkout(0);

        return rootView;

    }

    @Override
    public void onDestroy() {
        mp.stop();
        if (nonNull(timer)) {
            timer.cancel();
        }
        super.onDestroy();
    }


    private void startWorkout(int exerciseIndex) {
        fixOrientation();
        mp = MediaPlayer.create(getActivity(), R.raw.alarm);

        if (exerciseIndex <= exercises.size() - 1) {
            progressBar.setProgress(0);
            exerciseImage.setImageDrawable(exercises.get(exerciseIndex).getIcon());
            currentExerciseText.setText(exercises.get(exerciseIndex).getExercise().getName());
            setNextExercises(exerciseIndex);

            startCountDown(exercises.get(exerciseIndex).getTime(), exerciseIndex);
        }

    }

    private void fixOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else {
            requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
    }

    private void setNextExercises(int exerciseIndex) {
        if (exercises.get(exerciseIndex).getExercise() != Workout.REST && exercises.get(exerciseIndex).getExercise() != Workout.RUN_IN_PLACE) {
            nextExerciseText.setText(exercises.get(exerciseIndex + 2).getExercise().getName());
            nextExerciseTime.setText(calculateTimeText(exercises.get(exerciseIndex + 2).getExercise().getDuration()));
        } else if (exercises.get(exerciseIndex).getExercise() == Workout.REST) {
            nextExerciseText.setText(exercises.get(exerciseIndex + 1).getExercise().getName());
            nextExerciseTime.setText(calculateTimeText(exercises.get(exerciseIndex + 1).getExercise().getDuration()));
        } else if (exercises.get(exerciseIndex).getExercise() == Workout.RUN_IN_PLACE) {
            nextExerciseText.setText("DONE");
            nextExerciseTime.setText(StringUtils.EMPTY);
        }
    }

    private void startCountDown(long time, int exerciseIndex) {

        mp.setOnCompletionListener(mp -> startWorkout(exerciseIndex + 1));

        progressBar.setMax((int) time);
        timer = new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {
                progressInfo.setText(calculateTimeText(millisUntilFinished));
                progressBar.setProgress(progressBar.getMax() - (int) millisUntilFinished);
            }

            public void onFinish() {
                mp.start();
                progressBar.setProgress(0);
                progressInfo.setText("GO ON!");
            }
        }.start();
    }

    private String calculateTimeText(Long time) {

        long progress = time / 1000;
        long p1 = progress % 60;
        long p2 = progress / 60;
        long p3 = p2 % 60;

        String p3String = String.valueOf(p3);
        String p1String = String.valueOf(p1);

        if (p3 < 10) {
            p3String = "0" + p3;
        }
        if (p1 < 10) {
            p1String = "0" + p1;
        }

        return p3String + ":" + p1String;
    }
}
