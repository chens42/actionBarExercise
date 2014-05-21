package com.example.actionbarexercise.app.model;


import com.j256.ormlite.field.DatabaseField;

import org.codehaus.jackson.annotate.JsonProperty;

public class Spin {
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    @DatabaseField(id = true)
    public Long id;

    @JsonProperty("photo-url-1280")
    @DatabaseField
    private String photoUrl1280;

    public Spin(Long id) {
        this.id = id;
    }

    public Spin() {
    }
}
