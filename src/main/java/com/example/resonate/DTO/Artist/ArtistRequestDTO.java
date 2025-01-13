package com.example.resonate.DTO.Artist;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ArtistRequestDTO {

    @NotNull
    @Size(min=1,max=1000)
    private String name;

    private String description;

    public @NotNull @Size(min = 0, max = 1000) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 0, max = 1000) String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
