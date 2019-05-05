package hu.zeletrik.daily.exercises.application.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ExerciseDetails extends Exercise {

    private final String benefits;
    private final String instruction;
    private final String muscle;

    public ExerciseDetails(Exercise exercise, String benefits, String instruction, String muscle) {
        super(exercise.getWorkout(),
                exercise.getBaseDuration(),
                exercise.getCurrentDuration(),
                exercise.getIcon());
        this.benefits = benefits;
        this.instruction = instruction;
        this.muscle = muscle;
    }

    public String getBenefits() {
        return benefits;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getMuscle() {
        return muscle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ExerciseDetails that = (ExerciseDetails) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(benefits, that.benefits)
                .append(instruction, that.instruction)
                .append(muscle, that.muscle)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(benefits)
                .append(instruction)
                .append(muscle)
                .toHashCode();
    }
}
