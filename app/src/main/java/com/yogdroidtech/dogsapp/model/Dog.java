
package com.yogdroidtech.dogsapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "dog_table")
public class Dog {
    @ColumnInfo(name="bred_for")
    @SerializedName("bred_for")
    @Expose
    private String bredFor;

    @ColumnInfo(name="breed_group")
    @SerializedName("breed_group")
    @Expose
    private String breedGroup;

//    @ColumnInfo(name="height")
//    @SerializedName("height")
//    @Expose
//    private Height height;

    @ColumnInfo(name="id")
    @SerializedName("id")
    @Expose
    private Integer id;

    @ColumnInfo(name="life_span")
    @SerializedName("life_span")
    @Expose
    private String lifeSpan;

    @ColumnInfo(name="name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name="origin")
    @SerializedName("origin")
    @Expose
    private String origin;

    @ColumnInfo(name="temperament")
    @SerializedName("temperament")
    @Expose
    private String temperament;

//    @ColumnInfo(name="weight")
//    @SerializedName("weight")
//    @Expose
//    private Weight weight;

    @ColumnInfo(name="url")
    @SerializedName("url")
    @Expose
    private String url;

    @PrimaryKey(autoGenerate = true)
    public int idAuto;

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

//    public Height getHeight() {
//        return height;
//    }
//
//    public void setHeight(Height height) {
//        this.height = height;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

//    public Weight getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Weight weight) {
//        this.weight = weight;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
