package com.example.resonate.service;

import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.DTO.Song.SongRequestDTO;
import com.example.resonate.DTO.Song.SongUpdateDTO;
import com.example.resonate.model.Song;
import com.example.resonate.repository.SongRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    public Page<SongDTO> getAll(Pageable pageable) {

        return songRepository.findAll(pageable).map(this::toSongDTO);
    }


    public Page<SongDTO> getAllSummary(Pageable pageable) {
        return songRepository.findAll(pageable).map(this::toSongDTOSummary);
    }

    public SongDTO addSong(SongRequestDTO songRequestDTO) {


        Song song = new Song();

        song.setTitle(songRequestDTO.getTitle());
        song.setNumber(songRequestDTO.getNumber());
        song.setDuration(songRequestDTO.getDuration());

        songRepository.save(song);


        return toSongDTO(song);

    }

    public void deleteSong(Long id) {

        Song song = songRepository.findById(id).orElseThrow(()-> new RuntimeException("Song not found with id "+id));

        songRepository.delete(song);

    }

    public SongDTO updateSong(Long id, SongUpdateDTO songUpdateDTO) {

        Song updatedSong = songRepository.findById(id).orElseThrow(()-> new RuntimeException("Song not found with id "+id));

        if(songUpdateDTO.getTitle()!=null) {
            updatedSong.setTitle(songUpdateDTO.getTitle());
        }

        if(songUpdateDTO.getDuration()!=null) {
            updatedSong.setDuration(songUpdateDTO.getDuration());
        }

        if(songUpdateDTO.getNumber()!=null) {
            updatedSong.setNumber(songUpdateDTO.getNumber());
        }

        songRepository.save(updatedSong);

        return toSongDTO(updatedSong);
    }

    public SongDTO toSongDTO(Song song) {
        return new SongDTO(song.getId(),song.getTitle(),song.getNumber(),song.getDuration());
    }

    public SongDTO toSongDTOSummary(Song song) {
        return new SongDTO(song.getTitle(),song.getNumber(),song.getDuration());
    }

}
