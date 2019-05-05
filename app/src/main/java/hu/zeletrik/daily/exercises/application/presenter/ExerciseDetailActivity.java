package hu.zeletrik.daily.exercises.application.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Optional;

import hu.zeletrik.daily.exercises.R;
import hu.zeletrik.daily.exercises.application.domain.ExerciseDetails;
import hu.zeletrik.daily.exercises.application.domain.Workout;
import hu.zeletrik.daily.exercises.application.service.WorkoutService;
import hu.zeletrik.daily.exercises.application.service.impl.WorkoutServiceImpl;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class ExerciseDetailActivity extends AppCompatActivity {

    public static final String WORKOUT = "workout";

    private WorkoutService workoutService;

    private TextView mDurationCurrent;
    private TextView mDurationBasic;
    private ImageView mImage;

    private TextView mBenefits;
    private TextView mInstruction;
    private TextView mMuscle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        workoutService = new WorkoutServiceImpl(this);
        setContentView(R.layout.activity_exercises_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        mDurationBasic = findViewById(R.id.exerciseDetailsBaseDuration);
        mDurationCurrent = findViewById(R.id.exerciseDetailsCurrentDuration);
        mImage = findViewById(R.id.exerciseDetailsImage);
        mBenefits = findViewById(R.id.exerciseDetailsBenefits);
        mInstruction = findViewById(R.id.exerciseDetailsInstruction);
        mMuscle = findViewById(R.id.exerciseDetailsMuscles);


        Optional<Bundle> mainBundle = Optional.ofNullable(getIntent().getExtras());
        Workout workout = null;
        if (mainBundle.isPresent()) {
            workout = (Workout) mainBundle.get().get(WORKOUT);
        }

        ExerciseDetails exerciseDetails = workoutService.getExercisesDetailsFor(workout);

        getSupportActionBar().setTitle(exerciseDetails.getWorkout().getName());
        mDurationBasic.setText(String.valueOf(exerciseDetails.getBaseDuration()));
        mDurationCurrent.setText(String.valueOf(exerciseDetails.getCurrentDuration()));
        mImage.setImageDrawable(exerciseDetails.getIcon());
        mBenefits.setText(exerciseDetails.getBenefits());
        mInstruction.setText(exerciseDetails.getInstruction());
        mMuscle.setText(exerciseDetails.getMuscle());


        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
