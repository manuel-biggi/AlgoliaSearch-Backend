package com.webclient.algoliasearch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Product {

    // Algolia identifies and updates records using the objectID field.
    // Otherwise, I'll get this error "Missing objectID".
    // com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "objectID".

    @JsonProperty("objectID")
    @JsonAlias("id")

    private String objectID;

    private String name;
    private String description;
    private String brand;
    private List<String> categories;
    private Map<String, String> hierarchicalCategories;
    private String type;
    private Double price;
    private String price_range;
    private String image;
    private String url;
    private Boolean free_shipping;
    private Integer popularity;
    private Integer rating;


    // Getters y Setters
    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Map<String, String> getHierarchicalCategories() {
        return hierarchicalCategories;
    }

    public void setHierarchicalCategories(Map<String, String> hierarchicalCategories) {
        this.hierarchicalCategories = hierarchicalCategories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getFree_shipping() {
        return free_shipping;
    }

    public void setFree_shipping(Boolean free_shipping) {
        this.free_shipping = free_shipping;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
