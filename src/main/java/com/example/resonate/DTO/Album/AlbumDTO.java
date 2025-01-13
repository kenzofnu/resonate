package com.example.resonate.DTO.Album;

import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.model.Song;

import java.util.List;

public class AlbumDTO {

    private Long ID;

    private String title;

    private int releaseYear;

    private int duration;

    private List<SongDTO> songListDTO;

    public List<SongDTO> getSongList() {
        return songListDTO;
    }

    public void setSongList(List<SongDTO> songList) {
        this.songListDTO = songListDTO;
    }

    public AlbumDTO(Long ID, String title, int releaseYear, int duration, List<SongDTO> songListDTO) {
        this.ID = ID;
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.songListDTO = songListDTO;
    }


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
