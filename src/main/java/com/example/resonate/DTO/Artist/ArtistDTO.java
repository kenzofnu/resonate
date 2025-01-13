package com.example.resonate.DTO.Artist;

import com.example.resonate.DTO.Album.AlbumDTO;

import java.util.List;

public class ArtistDTO {

    private Long id;

    private String name;

    private String description;

    private List<AlbumDTO> albumDTOList;

    public List<AlbumDTO> getAlbumDTOList() {
        return albumDTOList;
    }

    public void setAlbumDTOList(List<AlbumDTO> albumDTOList) {
        this.albumDTOList = albumDTOList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArtistDTO(Long id, String name, String description, List<AlbumDTO> albumDTOList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.albumDTOList= albumDTOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
