package com.example.resonate.DTO.Album;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AlbumRequestDTO {

    @NotNull
    @Size(min=1,max = 100, message = "Title must be between min length of 1 and max length of 100")
    private String title;

    @NotNull
    @Min(1)
    private int releaseYear;

    public @NotNull @Size(min = 1, max = 100, message = "Title must be between min length of 1 and max length of 100") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @Size(min = 1, max = 100, message = "Title must be between min length of 1 and max length of 100") String title) {
        this.title = title;
    }

    @NotNull
    @Min(1)
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(@NotNull @Min(1) int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
