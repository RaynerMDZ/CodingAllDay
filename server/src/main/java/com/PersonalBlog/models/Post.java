package com.PersonalBlog.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="post")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "date")
    private Date date;

    @Column(name = "featurePhoto")
    private String featurePhoto;

    @OneToMany(mappedBy = "post", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFeaturePhoto() {
        return featurePhoto;
    }

    public void setFeaturePhoto(String featurePhoto) {
        this.featurePhoto = featurePhoto;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
