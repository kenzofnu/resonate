package com.example.resonate.DTO.Playlist;

import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.model.Song;
import com.example.resonate.model.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {

    private Long id;

    private String name;

    private List<SongDTO> songList = new ArrayList<>();

    public PlaylistDTO() {
    }

    public PlaylistDTO(Long id, String name, List<SongDTO> songList) {
        this.id = id;
        this.name = name;
        this.songList = songList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<SongDTO> getSongList() {
        return songList;
    }

    public void setSongList(List<SongDTO> songList) {
        this.songList = songList;
    }
}
