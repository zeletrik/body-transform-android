package hu.zeletrik.a5exercisesbodytransform;

/**
 * Created by zelena.patrik2 on 2018-04-18.
 */

public class Exercise {

    private String exercise;
    private Long time;

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Exercise(String exercise, Long time) {
        this.exercise = exercise;
        this.time = time;
    }
}
