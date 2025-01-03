package com.example.resonate.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="artist")
public class Artist {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<Album> albumList;


}
