package com.dipl.neuralphotos.model;

import javax.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "picture_url",nullable = false)
    private String pictureUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "likes")
    private long likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Picture() {
    }

    public Picture(String name, String pictureUrl, String description, long likes, User user) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.likes = likes;
        this.user = user;
    }

    public Picture(String name, String pictureUrl, User user) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
