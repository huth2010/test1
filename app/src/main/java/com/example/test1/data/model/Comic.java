package com.example.test1.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Comic implements Serializable {

    private String createdAt;
    private String description;
    private String author;
    private String publication;
    private String name;
    private String image;
    private List<String> content=new ArrayList<>();
    private String id;

    public Comic(String createdAt, String description, String author, String publication, String name, String image, String id) {
        this.createdAt = createdAt;
        this.description = description;
        this.author = author;
        this.publication = publication;
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public Comic(String createdAt, String description, String author, String publication, String name, String image, List<String> content, String id) {
        this.createdAt = createdAt;
        this.description = description;
        this.author = author;
        this.publication = publication;
        this.name = name;
        this.image = image;
        this.content = content;
        this.id = id;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
