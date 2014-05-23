package com.example.brands.app.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by shaojin on 23/05/14.
 */
public class Product {
    @DatabaseField
    private String name;

    @DatabaseField
    private String article;

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
