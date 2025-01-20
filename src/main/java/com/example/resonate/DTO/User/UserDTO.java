package com.example.resonate.DTO.User;


import com.example.resonate.DTO.Playlist.PlaylistDTO;
import com.example.resonate.model.Playlist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDate dob;

    private List<PlaylistDTO> playlistListDTO = new ArrayList<>();

    public Long getId() {
        return id;
    }



    public UserDTO(Long id, String name, String email, String password, LocalDate dob, List<PlaylistDTO> playlistDTO) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.playlistListDTO = playlistDTO;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PlaylistDTO> getPlaylistListDTO() {
        return playlistListDTO;
    }

    public void setPlaylistListDTO(List<PlaylistDTO> playlistListDTO) {
        this.playlistListDTO = playlistListDTO;
    }


}
