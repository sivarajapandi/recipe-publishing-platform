package com.siva.RecipepublishingPlatform.Demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String summary;
    private String ingredients;
    private String steps;
    private String labels;
    private String images;
    private LocalDate publishedDate;

    @ManyToOne
    //why many to one ?
    //Many recipes → One chef (@ManyToOne)
    //It allows you to query all recipes by a chef and know who authored a recipe
    @JoinColumn(name = "chef_id")
    private Users chef;


    public Recipes(Long id, String title, String summary, String ingredients, String steps, String labels, String images) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.ingredients = ingredients;
        this.steps = steps;
        this.labels = labels;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
}
