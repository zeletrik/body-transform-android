package hu.zeletrik.daily.exercises.application.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import hu.zeletrik.daily.exercises.R;
import hu.zeletrik.daily.exercises.application.presenter.fragments.WorkoutFragment;

/**
 * Created by zeletrik on 2018-04-18.
 */
public class WorkoutActivity extends AppCompatActivity {

    private final Fragment workoutFragment = new WorkoutFragment();
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragmentManager.beginTransaction().replace(R.id.frame_container, workoutFragment).commit();
    }


    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            fragmentManager.beginTransaction().remove(workoutFragment).commitAllowingStateLoss();
            finish();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}