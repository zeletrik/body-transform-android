package hu.zeletrik.daily.exercises.application.domain;

import android.graphics.drawable.Drawable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by zelena.patrik2 on 2018-04-18.
 */

public class Exercise {

    private final Workout workout;
    private final Long baseDuration;
    private final Long currentDuration;
    private final Drawable icon;

    public Exercise(Workout workout, Long baseDuration, Long currentDuration, Drawable icon) {
        this.workout = workout;
        this.baseDuration = baseDuration;
        this.currentDuration = currentDuration;
        this.icon = icon;
    }

    public Workout getWorkout() {
        return workout;
    }

    public Long getBaseDuration() {
        return baseDuration;
    }

    public Long getCurrentDuration() {
        return currentDuration;
    }

    public Drawable getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise1 = (Exercise) o;

        return new EqualsBuilder()
                .append(workout, exercise1.workout)
                .append(baseDuration, exercise1.baseDuration)
                .append(currentDuration, exercise1.currentDuration)
                .append(icon, exercise1.icon)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(workout)
                .append(baseDuration)
                .append(currentDuration)
                .append(icon)
                .toHashCode();
    }
}
