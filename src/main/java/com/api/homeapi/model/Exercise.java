package com.api.homeapi.model;

public class Exercise {
    private final long id;
    private final long userId;
    private String name;
    private long max;
    private boolean isWeighted;

    public Exercise(long id, long userId, String name, long max, boolean isWeighted) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.max = max;
        this.isWeighted = isWeighted;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
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
