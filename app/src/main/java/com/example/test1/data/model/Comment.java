package com.example.test1.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Comment {
    private Date createdAt;
    @SerializedName("id_comic")
    private String idComic;
    @SerializedName("id_user")
    private String idUser;
    private String content;
    private String id;

    public Comment(Date createdAt, String idComic, String idUser, String content, String id) {
        this.createdAt = createdAt;
        this.idComic = idComic;
        this.idUser = idUser;
        this.content = content;
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getIdComic() {
        return idComic;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }
}
