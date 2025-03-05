package com.example.resonate.DTO.Playlist;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class PlaylistUpdateDTO {

    @Size(min=0)
    private String name;

    private String description;

    public @Size(min = 0) String getName() {
        return name;
    }

    public void setName(@Size(min = 0) String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
