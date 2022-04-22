package com.example.demo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String caption;
    private String path;
    @ManyToMany
    @JoinTable(name = "pictures_albums",
    joinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"))
    private Set<Album> albums;

    public Picture() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
