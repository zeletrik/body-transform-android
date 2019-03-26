package hu.zeletrik.daily.exercises.application.domain;

import hu.zeletrik.daily.exercises.application.domain.Exercises;

/**
 * Created by zelena.patrik2 on 2018-04-18.
 */

public class Exercise {

    private Exercises exercise;
    private Long time;

    public Exercises getExercise() {
        return exercise;
    }

    public void setExercise(Exercises exercise) {
        this.exercise = exercise;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Exercise(Exercises exercise, Long time) {
        this.exercise = exercise;
        this.time = time;
    }
}
