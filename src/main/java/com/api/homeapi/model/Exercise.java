package com.api.homeapi.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;

    private String name;

    private long max;
    
    private boolean isWeighted;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public void setIsWeighted(boolean isWeighted) {
        this.isWeighted = isWeighted;
    }

}
