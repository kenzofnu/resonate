package com.example.resonate.DTO.Album;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class AlbumUpdateDTO {

    @Size(min=1,max = 100, message = "Title must be between min length of 1 and max length of 100")
    private String title;

    @Min(1)
    private Integer releaseYear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
