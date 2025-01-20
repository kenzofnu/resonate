package com.example.resonate.service;

import com.example.resonate.DTO.Playlist.PlaylistDTO;
import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.model.Playlist;
import com.example.resonate.model.Song;
import com.example.resonate.repository.PlaylistRepository;
import com.example.resonate.repository.SongRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;

    private SongRepository songRepository;

    public PlaylistService(PlaylistRepository playlistRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }


    public Page<PlaylistDTO> getAll(Pageable pageable) {

        return playlistRepository.findAll(pageable).map(this::toPlaylistDTO);

    }


    public PlaylistDTO toPlaylistDTO(Playlist playlist) {
        List<SongDTO> songDTO = playlist.getSongList().stream().map(this::toSongDTO).collect(Collectors.toList());
        return new PlaylistDTO(playlist.getId(),playlist.getName(),songDTO);
    }

    public PlaylistDTO addSong(Long id, Long songId) {

            Playlist playlist = playlistRepository.findById(id).orElseThrow(()-> new RuntimeException("Playlist not found"));

            Song song = songRepository.findById(songId).orElseThrow(()-> new RuntimeException("Song not found"));

            if(playlist.getSongList().contains(song)) {
                throw new RuntimeException("Song already exist");
            }

            playlist.getSongList().add(song);

            playlistRepository.save(playlist);

            return toPlaylistDTO(playlist);


    }



    public SongDTO toSongDTO(Song song) {
        return new SongDTO(song.getId(),song.getTitle(),song.getNumber(),song.getDuration());
    }

    public PlaylistDTO removeSong(Long id, Long songId) {

        Playlist playlist = playlistRepository.findById(id).orElseThrow(()-> new RuntimeException("Playlist not found"));

        Song song = songRepository.findById(songId).orElseThrow(()-> new RuntimeException("Song not found"));

        playlist.getSongList().remove(song);

        playlistRepository.save(playlist);

        return toPlaylistDTO(playlist);

    }
}
