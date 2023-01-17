package com.example.movieservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @Column(length = 2048)
    private String description;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Session> sessions = new HashSet<>();

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;

        for (Session b : sessions) {
            b.setMovie(this);
        }
    }
}
