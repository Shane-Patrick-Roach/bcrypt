package com.bcrypt.bcrypt.model;


import javax.persistence.*;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String text;

    @ManyToOne
    SiteUser postsOfUser;


    public Posts(){
    }

    public Posts(String text) {
        this.text = text;
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

    public SiteUser getPostsOfUser() {
        return postsOfUser;
    }

    public void setPostsOfUser(SiteUser postsOfUser) {
        this.postsOfUser = postsOfUser;
    }
}
