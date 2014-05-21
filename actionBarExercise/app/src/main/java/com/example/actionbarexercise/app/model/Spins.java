package com.example.actionbarexercise.app.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Spins {
    @JsonProperty("posts")
    private List<Spin> spins;

    public List<Spin> getSpins() {
        return spins;
    }

    public void setSpins(List<Spin> spins) {
        this.spins = spins;
    }

    public void setNewPose(List<Spin> posts) {
        this.spins.addAll(posts);
    }
}
