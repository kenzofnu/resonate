package com.example.resonate.service;

import com.example.resonate.DTO.Playlist.PlaylistDTO;
import com.example.resonate.model.Playlist;
import com.example.resonate.repository.PlaylistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }


    public Page<PlaylistDTO> getAll(Pageable pageable) {

        return playlistRepository.findAll(pageable).map(this::toPlaylistDTO);

    }


    public PlaylistDTO toPlaylistDTO(Playlist playlist) {
        return new PlaylistDTO(playlist.getId(),playlist.getName(),playlist.getSongList());
    }
}
