package com.example.resonate.controller;

import com.example.resonate.DTO.Playlist.PlaylistDTO;
import com.example.resonate.model.Playlist;
import com.example.resonate.service.PlaylistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resonate/playlist")
public class PlaylistController {

    private PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<Page<PlaylistDTO>> getAll(Pageable pageable) {

        return ResponseEntity.ok().body(playlistService.getAll(pageable));

    }


}
