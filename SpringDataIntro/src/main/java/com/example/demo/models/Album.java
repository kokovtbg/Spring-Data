package com.example.demo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "background_color")
    private String backgroundColor;
    @Enumerated(EnumType.STRING)
    @Column(name = "album_status")
    private AlbumStatus albumStatus;
    @ManyToMany(mappedBy = "albums", targetEntity = Picture.class)
    private Set<Picture> pictures;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Album() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public AlbumStatus getAlbumStatus() {
        return albumStatus;
    }

    public void setAlbumStatus(AlbumStatus albumStatus) {
        this.albumStatus = albumStatus;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
