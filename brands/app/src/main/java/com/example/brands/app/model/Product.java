package com.example.brands.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by shaojin on 23/05/14.
 */
public class Product implements Parcelable {
/*    @DatabaseField(generatedId = true)
    private int id;*/
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

    public static final Parcelable.Creator<Product> CREATOR = new Creator<Product>() {
        public Product createFromParcel(Parcel source) {
            Product product = new Product();
            product.name = source.readString();
            product.article = source.readString();
            product.type = source.readString();
            return product;
        }
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(article);
        parcel.writeString(type);
    }
}
