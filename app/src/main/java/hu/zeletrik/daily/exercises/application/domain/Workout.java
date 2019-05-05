package hu.zeletrik.daily.exercises.application.domain;

import hu.zeletrik.daily.exercises.R;

public enum Workout {
    REST(15000L, R.string.rest_name),
    WARM_UP(30000L, R.string.warm_up_name),
    HEISMAN(90000L, R.string.heisman_name),
    BURPEE(90000L, R.string.burpee_name),
    JUMP_JACK(60000L, R.string.jumping_jack_name),
    MOUNTAIN_CLIMBER(60000L, R.string.mountain_climber_name),
    LYING_HIP_RISE(90000L, R.string.lying_hip_rise_name),
    RUN_IN_PLACE(60000L,R.string.run_in_place_name),
    OTHER(0L, R.string.not_found_name);

    private final long duration;
    private final int name;

    public long getDuration() {
        return duration;
    }

    public int getName() {
        return name;
    }

    Workout(long duration, int name) {
        this.duration = duration;
        this.name = name;
    }

}
