package com.example.brands.app.model;

import com.j256.ormlite.field.DatabaseField;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by shaojin on 23/05/14.
 */
public class Product {
    @JsonProperty("name")
    @DatabaseField
    private String name;
    @JsonProperty("article")
    @DatabaseField
    private String article;
    @JsonProperty("type")
    @DatabaseField
    private String type;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getArticle() {
        return article;
    }

    public Product(String name,String type, String article) {
        this.type = type;
        this.article = article;
        this.name = name;
    }

    public Product() {
    }
}
