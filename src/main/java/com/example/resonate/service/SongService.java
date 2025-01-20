package com.example.resonate.service;

import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.DTO.Song.SongRequestDTO;
import com.example.resonate.DTO.Song.SongUpdateDTO;
import com.example.resonate.model.Album;
import com.example.resonate.model.Song;
import com.example.resonate.repository.AlbumRepository;
import com.example.resonate.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SongService {

    private SongRepository songRepository;

    private AlbumRepository albumRepository;

    public SongService(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
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

    @Transactional
    public void deleteSong(Long id) {

        Song song = songRepository.findById(id).orElseThrow(()-> new RuntimeException("Song not found with id "+id));

        if(song.getAlbum()!=null) {
            Album album = song.getAlbum();

            album.getSongList().remove(song);

            int duration= album.getSongList().stream().mapToInt(Song::getDuration).sum();

            album.setDuration(duration);

            albumRepository.save(album);
        }


        songRepository.delete(song);

    }

    @Transactional
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

        Album album = updatedSong.getAlbum();

        int duration= album.getSongList().stream().mapToInt(Song::getDuration).sum();

        album.setDuration(duration);

        albumRepository.save(album);

        return toSongDTO(updatedSong);
    }

    public SongDTO toSongDTO(Song song) {
        return new SongDTO(song.getId(),song.getTitle(),song.getNumber(),song.getDuration());
    }

    public SongDTO toSongDTOSummary(Song song) {
        return new SongDTO(song.getTitle(),song.getNumber(),song.getDuration());
    }

    public Page<SongDTO> searchByTitle(String title, Pageable pageable) {

        return songRepository.searchByTitle(title,pageable).map(this::toSongDTO);




    }
}
