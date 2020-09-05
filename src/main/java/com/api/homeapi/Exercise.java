package com.api.homeapi;

public class Exercise {
    private final long id;
    private String name;
    private long max;
    private boolean isWeighted;

    public Exercise(long id, String name, long max, boolean isWeighted) {
        this.id = id;
        this.name = name;
        this.max = max;
        this.isWeighted = isWeighted;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getMax() {
        return max;
    }

    public boolean getIsWeighted() {
        return isWeighted;
    }

}
