package hu.zeletrik.daily.exercises.application.presenter.fragments;

/**
 * Created by zeletrik on 2018-04-19.
 */

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import hu.zeletrik.daily.exercises.R;
import hu.zeletrik.daily.exercises.application.domain.Exercise;
import hu.zeletrik.daily.exercises.application.domain.Exercises;

import static java.util.Objects.nonNull;

public class WorkoutFragment extends Fragment {

    private final List<Exercise> exercises = ImmutableList.of(
            new Exercise(Exercises.WARM_UP, Exercises.WARM_UP.getDuration()),
            new Exercise(Exercises.HEISMAN, Exercises.HEISMAN.getDuration()),
            new Exercise(Exercises.REST, Exercises.REST.getDuration()),
            new Exercise(Exercises.BURPEE, Exercises.BURPEE.getDuration()),
            new Exercise(Exercises.REST, Exercises.REST.getDuration()),
            new Exercise(Exercises.JUMP_JACK, Exercises.JUMP_JACK.getDuration()),
            new Exercise(Exercises.REST, Exercises.REST.getDuration()),
            new Exercise(Exercises.MOUNTAIN_CLIMBER, Exercises.MOUNTAIN_CLIMBER.getDuration()),
            new Exercise(Exercises.REST, Exercises.REST.getDuration()),
            new Exercise(Exercises.LYING_HIP_RISE, Exercises.LYING_HIP_RISE.getDuration()),
            new Exercise(Exercises.REST, Exercises.REST.getDuration()),
            new Exercise(Exercises.RUN_IN_PLACE, Exercises.RUN_IN_PLACE.getDuration())
    );

    private MediaPlayer mp;
    private TextView progressInfo;
    private TextView currentExerciseText;
    private TextView nextExerciseText;
    private TextView nextExerciseTime;
    private ProgressBar progressBar;
    private ImageView exerciseImage;
    private CountDownTimer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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

        mp = MediaPlayer.create(getActivity(), R.raw.alarm);

        if (exerciseIndex <= exercises.size() - 1) {
            int currentOrientation = getResources().getConfiguration().orientation;
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            } else {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            }

            progressBar.setProgress(0);
            exerciseImage.setImageDrawable(getDrawable(exercises.get(exerciseIndex)));
            currentExerciseText.setText(exercises.get(exerciseIndex).getExercise().getName());

            if (exercises.get(exerciseIndex).getExercise() != Exercises.REST && exercises.get(exerciseIndex).getExercise() != Exercises.RUN_IN_PLACE) {
                nextExerciseText.setText(exercises.get(exerciseIndex + 2).getExercise().getName());
                nextExerciseTime.setText(calculateTimeText(exercises.get(exerciseIndex + 2).getExercise().getDuration()));
            } else if (exercises.get(exerciseIndex).getExercise() == Exercises.REST) {
                nextExerciseText.setText(exercises.get(exerciseIndex + 1).getExercise().getName());
                nextExerciseTime.setText(calculateTimeText(exercises.get(exerciseIndex + 1).getExercise().getDuration()));
            } else if (exercises.get(exerciseIndex).getExercise() == Exercises.RUN_IN_PLACE) {
                nextExerciseText.setText("DONE");
                nextExerciseTime.setText(StringUtils.EMPTY);

            }

            startCountDown(exercises.get(exerciseIndex).getTime(), exerciseIndex);
        }

    }

    private Drawable getDrawable(Exercise exercise) {
        return getResources().getDrawable(R.drawable.heisman_circle_one, null);
    }

    private void setBG(Exercises exercises) {

        if (getResources().getConfiguration().orientation == 1) {
            switch (exercises) {
            }

        } else {
            switch (exercises) {

            }
        }
    }

    public void startCountDown(long time, int exerciseIndex) {

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
            p3String = "0" + String.valueOf(p3);
        }
        if (p1 < 10) {
            p1String = "0" + String.valueOf(p1);
        }

        return p3String + ":" + p1String;
    }
}
