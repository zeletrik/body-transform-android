package hu.zeletrik.daily.exercises.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ExerciseDetails{

    private final Exercise exercise;
    private final String benefits;
    private final String instruction;
    private final String muscle;

}
