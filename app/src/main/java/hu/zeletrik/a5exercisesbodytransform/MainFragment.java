package hu.zeletrik.a5exercisesbodytransform;

/**
 * Created by zeletrik on 2018-04-19.
 */

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainFragment extends Fragment {

    private DrawerInterface drawerInterface;

    private TextView txtProgress;
    private TextView txtCurrent;
    private TextView txtNext;
    private FloatingActionButton startFab;
    private ProgressBar progressBar;
    private ImageButton skipButton;
    private RelativeLayout relativeLayout;
    private int exerciseNum = -1;
    private int pStatus = 0;
    private int workoutNum = 0;
    private Spinner spinner;
    private  MediaPlayer mp;
    private Boolean isSkipped = false;
    private Boolean isCancelable = false;

    private Exercise[] workOut1  = {
            new Exercise("Plank", 60000L),
            new Exercise("Push-ups", 60000L),
            new Exercise("Squats", 120000L),
            new Exercise("Bird-dog", 60000L),
            new Exercise("Lying hip raises", 60000L),
            new Exercise("Plank", 60000L),
            new Exercise("Push-ups", 60000L),
            new Exercise("Squats", 120000L)
    };
    private Exercise[] workOut2  = {
            new Exercise("Plank", 180000L),
            new Exercise("Bird-dog", 180000L),
            new Exercise("Lying hip raises", 180000L),
            new Exercise("Push-ups", 60000L),
    };

    public MainFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            drawerInterface = (DrawerInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement DrawerInterface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        txtProgress = (TextView) rootView.findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        relativeLayout = (RelativeLayout) rootView.findViewById(R.id.backgroundLayout);
        spinner = (Spinner) rootView.findViewById(R.id.workouts_spinner);
        txtCurrent = (TextView) rootView.findViewById(R.id.currentExercise);
        txtNext = (TextView) rootView.findViewById(R.id.nextExercise);
        startFab = rootView.findViewById(R.id.fab);
        skipButton = rootView.findViewById(R.id.skipButton);

        startFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(!isCancelable)  {
                exerciseNum = -1;
                String workout = String.valueOf(spinner.getSelectedItem());
                workoutNum = Integer.valueOf(workout.substring(workout.length()-1, workout.length()));

                Snackbar.make(view, workout+ " Started!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startWorkout();
                } else {
                   isSkipped = true;
                   isCancelable = false;
                   exerciseNum = 100;
               }
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSkipped = true;
            }
        });

        return rootView;
    }


    public void startWorkout() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }
        else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
        drawerInterface.lockDrawer();
        spinner.setVisibility(View.INVISIBLE);
        skipButton.setVisibility(View.VISIBLE);
        isCancelable = true;
        startFab.setImageDrawable(getResources().getDrawable(R.drawable.x));

        switch (workoutNum) {
            case 1 :
                if(exerciseNum >= workOut1.length-1) {
                    progressBar.setProgress(0);
                    txtProgress.setText("DONE!");
                    txtCurrent.setText("COMPLETED!");
                    relativeLayout.setBackgroundResource(0);
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    spinner.setVisibility(View.VISIBLE);
                    skipButton.setVisibility(View.INVISIBLE);
                    drawerInterface.unlockDrawer();
                    startFab.setImageDrawable(getResources().getDrawable(R.drawable.run));
                    isCancelable = false;
                    break;
                } else {
                    exerciseNum++;
                    if (exerciseNum >= workOut1.length-1){
                        txtNext.setText("DONE !");
                    } else {
                        txtNext.setText("Next: " + workOut1[exerciseNum+1].getExercise());
                    }
                    setBG(workOut1[exerciseNum].getExercise());
                    txtCurrent.setText(workOut1[exerciseNum].getExercise());
                    startCountDown(workOut1[exerciseNum].getTime());
                }
                break;

            case 2 :
                if(exerciseNum >= workOut2.length-1) {
                    progressBar.setProgress(0);
                    txtProgress.setText("DONE!");
                    txtCurrent.setText("COMPLETED!");
                    relativeLayout.setBackgroundResource(0);
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    spinner.setVisibility(View.VISIBLE);
                    skipButton.setVisibility(View.INVISIBLE);
                    drawerInterface.unlockDrawer();
                    startFab.setImageDrawable(getResources().getDrawable(R.drawable.run));
                    isCancelable = false;
                    break;
                } else {
                    exerciseNum++;
                    if (exerciseNum >= workOut2.length-1){
                        txtNext.setText("DONE !");
                    } else {
                        txtNext.setText("Next: " + workOut2[exerciseNum+1].getExercise());
                    }
                    setBG(workOut2[exerciseNum].getExercise());
                    txtCurrent.setText(workOut2[exerciseNum].getExercise());
                    startCountDown(workOut2[exerciseNum].getTime());
                }
                break;
            default :
                relativeLayout.setBackgroundResource(0);
                break;
        }

    }

    public void setBG(String workoutName) {

     if(getResources().getConfiguration().orientation == 1)    {
         switch (workoutName) {
             case "Plank" :
                 relativeLayout.setBackgroundResource(R.drawable.plank_portrait);
                 break;
             case "Push-ups" :
                 relativeLayout.setBackgroundResource(R.drawable.pushup_portrait);
                 break;
             case "Squats" :
                 relativeLayout.setBackgroundResource(R.drawable.squat_portrait);
                 break;
             case "Bird-dog" :
                 relativeLayout.setBackgroundResource(R.drawable.birddog_portrait);
                 break;
             case "Lying hip raises" :
                 relativeLayout.setBackgroundResource(R.drawable.hiprise_portrait);
                 break;
             default :
                 relativeLayout.setBackgroundResource(0);
                 break;
         }

     } else {
         switch (workoutName) {
             case "Plank" :
                 relativeLayout.setBackgroundResource(R.drawable.plank_landscape);
                 break;
             case "Push-ups" :
                 relativeLayout.setBackgroundResource(R.drawable.pushup_landscape);
                 break;
             case "Squats" :
                 relativeLayout.setBackgroundResource(R.drawable.squat_landscape);
                 break;
             case "Bird-dog" :
                 relativeLayout.setBackgroundResource(R.drawable.birddog_landscape);
                 break;
             case "Lying hip raises" :
                 relativeLayout.setBackgroundResource(R.drawable.hiprise_landscape);
                 break;
             default :
                 relativeLayout.setBackgroundResource(0);
                 break;
         }
     }
    }

    public void startCountDown(long time){

        mp = MediaPlayer.create(getActivity(), R.raw.alarm);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startWorkout();
            }
        });

        progressBar.setMax((int) time);
        new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {

                if(isSkipped) {
                    isSkipped = false;
                    cancel();
                    progressBar.setProgress(0);
                    startWorkout();
                } else {
                long progress =  millisUntilFinished / 1000;
                long p1 = progress % 60;
                long p2 = progress / 60;
                long p3 = p2 % 60;

                String p3String = String.valueOf(p3);
                String p1String = String.valueOf(p1);

                if(p3 < 10) { p3String = "0" + String.valueOf(p3); }
                if(p1 < 10) { p1String = "0" + String.valueOf(p1); }

                String remaining =  p3String + ":" + p1String;
                txtProgress.setText(remaining);
                progressBar.setProgress(progressBar.getMax() - (int) millisUntilFinished);
            }
            }
            public void onFinish() {
                mp.start();
                progressBar.setProgress(0);
                txtProgress.setText("KEEP UP!");
            }
        }.start();
    }
}
