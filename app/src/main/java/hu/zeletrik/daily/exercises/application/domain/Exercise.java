package hu.zeletrik.daily.exercises.application.domain;

import android.graphics.drawable.Drawable;

/**
 * Created by zelena.patrik2 on 2018-04-18.
 */

public class Exercise {

    private Workout exercise;
    private Long time;
    private Drawable icon;

    public Workout getExercise() {
        return exercise;
    }

    public Long getTime() {
        return time;
    }

    public Drawable getIcon() {
        return icon;
    }

    public Exercise(Workout exercise, Long time, Drawable icon) {
        this.exercise = exercise;
        this.time = time;
        this.icon = icon;
    }
}
