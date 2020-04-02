package com.xiandabai.moviewebsite.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class MovieGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Must have a group name")
    @Size(max = 20, message = "Can not have more than 20 characters")
    private String groupName;

    @NotBlank(message = "Must have a group id")
    @Size(min = 4, max = 8)
    @Column(updatable = false, unique = true)
    private String groupID;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "movieGroup")
    @JsonIgnore
    private List<MovieList> movieLists = new ArrayList<>();

    private String moderator;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public MovieGroup() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MovieList> getMovieLists() {
        return movieLists;
    }

    public void setMovieLists(List<MovieList> movieLists) {
        this.movieLists = movieLists;
    }
}
