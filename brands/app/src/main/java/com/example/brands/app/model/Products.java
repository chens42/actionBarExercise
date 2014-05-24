package com.example.brands.app.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Products {
    @JsonProperty("product")
    private List<Product> products;
    public List<Product> getProducts() {
        return products;
    }

    public void setSpins(List<Product> products) {
        this.products = products;
    }

/*    public void setNewPose(List<Product> posts) {
        this.spins.addAll(posts);
    }*/
}
