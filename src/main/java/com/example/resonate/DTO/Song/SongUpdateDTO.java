package com.example.resonate.DTO.Song;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class SongUpdateDTO {

    @Size(min = 1, max = 100, message = "Min size is 1 char, max size is 100")
    private String title;

    @Min(1)
    private Integer number;

    @Min(1)
    private Integer duration;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
