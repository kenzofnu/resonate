package com.example.resonate.DTO.Song;

public class SongDTO {

    private Long id;

    private String title;

    private int number;

    private int duration;

    public SongDTO(Long id, String title, int number, int duration) {
        this.id = id;
        this.title = title;
        this.number = number;
        this.duration = duration;
    }

    public SongDTO(String title, int number, int duration) {
        this.title = title;
        this.number = number;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
