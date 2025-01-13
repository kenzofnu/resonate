package com.example.resonate.controller;

import com.example.resonate.DTO.Album.AlbumDTO;
import com.example.resonate.DTO.Album.AlbumRequestDTO;
import com.example.resonate.DTO.Album.AlbumUpdateDTO;
import com.example.resonate.DTO.Song.SongRequestDTO;
import com.example.resonate.model.Album;
import com.example.resonate.model.Song;
import com.example.resonate.service.AlbumService;
import com.example.resonate.service.SongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resonate/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public Page<AlbumDTO> getAlbums(Pageable pageable) {

        return albumService.getAll(pageable);

    }

    @PostMapping
    public ResponseEntity<AlbumDTO> addAlbum(@Valid @RequestBody AlbumRequestDTO albumRequestDTO) {

        return ResponseEntity.ok(albumService.addAlbum(albumRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Long id, @Valid @RequestBody AlbumUpdateDTO albumUpdateDTO) {


        return ResponseEntity.ok(albumService.updateAlbum(id,albumUpdateDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/songs")
    public ResponseEntity<Void> addSongsToAlbum(@PathVariable Long id, @Valid @RequestBody List<SongRequestDTO> songs) {

        albumService.addSongsToAlbum(id, songs);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/remove/song/{songid}")
    public ResponseEntity<AlbumDTO> removeSongFromAlbum(@PathVariable Long id, @PathVariable Long songid) {

        return ResponseEntity.ok(albumService.removeSongFromAlbum(id,songid));

    }

    @PostMapping("/{id}/add/song/{songid}")
    public ResponseEntity<AlbumDTO> addExistingSong(@PathVariable Long id, @PathVariable Long songid) {

        return ResponseEntity.ok(albumService.addExistingSong(id,songid));
    }



}
