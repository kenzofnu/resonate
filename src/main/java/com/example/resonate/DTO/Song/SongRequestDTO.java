package com.example.resonate.DTO.Song;

import jakarta.validation.constraints.*;

public class SongRequestDTO {

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title length must be minimum 1 or maximum 100")
    private String title;

    @NotNull(message = "Number cannot be null")
    @Min(value = 1,message = "Number must be more than 1")
    private int number;

    @NotNull(message = "Duration cannot be null")
    @Min(value = 1, message = "Number must be more than 1")
    private int duration;

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    @Min(value = 1, message = "Number must be more than 1")
    public int getNumber() {
        return number;
    }

    public void setNumber(@Min(value = 1, message = "Number must be more than 1") int number) {
        this.number = number;
    }

    @Min(value = 1, message = "Number must be more than 1")
    public int getDuration() {
        return duration;
    }

    public void setDuration(@Min(value = 1, message = "Number must be more than 1") int duration) {
        this.duration = duration;
    }
}
