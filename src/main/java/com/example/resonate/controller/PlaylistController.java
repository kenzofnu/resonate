package com.example.resonate.controller;

import com.example.resonate.DTO.Playlist.PlaylistDTO;
import com.example.resonate.DTO.Playlist.PlaylistRequestDTO;
import com.example.resonate.DTO.Playlist.PlaylistUpdateDTO;
import com.example.resonate.model.Playlist;
import com.example.resonate.service.PlaylistService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<PlaylistDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(playlistService.findById(id));
    }


    @PutMapping("/{id}/add/{songId}")
    public ResponseEntity<PlaylistDTO> addSongs(@PathVariable Long id, @PathVariable Long songId) {

        return ResponseEntity.ok(playlistService.addSong(id,songId));


    }
    
    @PutMapping("/{id}/remove/{songId}")
    public ResponseEntity<PlaylistDTO> removeSong(@PathVariable Long id, @PathVariable Long songId) {

        return ResponseEntity.ok(playlistService.removeSong(id,songId));

    }


    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updatePlaylist (@PathVariable Long id, @Valid @RequestBody PlaylistUpdateDTO playlistUpdateDTO) {

        playlistService.updatePlaylist(id, playlistUpdateDTO);
        return ResponseEntity.ok().build();


    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {

        playlistService.deletePlaylist(id);

        return ResponseEntity.noContent().build();



    }



}
