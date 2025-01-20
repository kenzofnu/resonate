package com.example.resonate.DTO.Playlist;

import com.example.resonate.model.Song;
import com.example.resonate.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PlaylistRequestDTO {

    @NotNull
    @Size(min=0)
    private String name;


    public @NotNull @Size(min = 0) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 0) String name) {
        this.name = name;
    }
}
