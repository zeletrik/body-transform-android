package hu.zeletrik.daily.exercises.application.domain;

public enum Workout {
    REST(15000L, "Rest"),
    WARM_UP(30000L, "Warm up"),
    HEISMAN(90000L, "Heisman"),
    BURPEE(90000L, "Burpee"),
    JUMP_JACK(60000L, "Jumping-Jack"),
    MOUNTAIN_CLIMBER(60000L, "Mountain Climbers"),
    LYING_HIP_RISE(90000L, "Lying Hip Rise"),
    RUN_IN_PLACE(60000L, "Run in place");

    private final long duration;
    private final String name;

    public long getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    Workout(long duration, String name) {
        this.duration = duration;
        this.name = name;
    }

}
