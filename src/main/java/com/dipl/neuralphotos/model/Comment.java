package com.dipl.neuralphotos.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "picture_id",table = "picture",nullable = false)
    private Picture picture;

    @ManyToOne
    @JoinColumn(name = "user_id",table = "user",nullable = false)
    private User user;

    public Comment() {
    }

    public Comment(String text, Picture picture, User user) {
        this.text = text;
        this.picture = picture;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
