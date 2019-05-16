package hu.zeletrik.daily.exercises.application.domain;

import android.graphics.drawable.Drawable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by zelena.patrik2 on 2018-04-18.
 */
@Builder
@Getter
@EqualsAndHashCode
public class Exercise {

    private final Workout workout;
    private final Long baseDuration;
    private final Long currentDuration;
    private final Drawable icon;
}
